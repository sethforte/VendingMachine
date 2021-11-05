package com.techelevator;

import com.techelevator.exceptions.InsufficientFundsException;
import com.techelevator.exceptions.InvalidChoiceException;
import com.techelevator.exceptions.OutOfStockException;
import com.techelevator.view.Menu;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE ,MAIN_MENU_OPTION_EXIT};
	private VendingMachine vendingMachine;
	private Scanner scanner = new Scanner(System.in);
	private Menu menu;
	private Logger auditLogger;
	//private Map<VendingMachineItem, Integer> countSales;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
		this.vendingMachine = new VendingMachine();
		this.auditLogger = new Logger("Log.txt");

	}

	public void run() {

		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS, true);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				displayPurchaseItems();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				purchasingProcessMenu();
			}
			else if(choice.equals(MAIN_MENU_OPTION_EXIT)) {
				try {
					auditLogger.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.exit(0);
			} else if (choice.equals("Hidden Menu")) {
				printHiddenMenu();
			}
		}
	}

	public void displayPurchaseItems(){
		VendingMachineItem[] items = vendingMachine.getInventory().toArray(new VendingMachineItem[0]);
		for(int i = 0;i<items.length;i++ ){
			System.out.println(items[i].getId() +" "+items[i].getName() +" QTY: "+(items[i].isSoldOut()?"SOLD OUT":items[i].getStock()));
		}
	}

	public void purchasingProcessMenu() {
		final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
		final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
		final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";

		String[] purchaseMenuOptions = {PURCHASE_MENU_OPTION_FEED_MONEY,
				PURCHASE_MENU_OPTION_SELECT_PRODUCT,
				PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

		boolean shouldDisplay = true;
		while(shouldDisplay) {
			String choice = (String) menu.getChoiceFromOptions(purchaseMenuOptions);

			System.out.println("\nCurrent Money Provided: $" + vendingMachine.getBalance());


			switch (choice) {
				case PURCHASE_MENU_OPTION_FEED_MONEY:
					feedMoney();
					break;
				case PURCHASE_MENU_OPTION_SELECT_PRODUCT:
					try {
						selectProduct();
					} catch (InvalidChoiceException | OutOfStockException | InsufficientFundsException e) {
						System.out.println(e.getMessage());
					}
					break;
				case PURCHASE_MENU_OPTION_FINISH_TRANSACTION:
					finishTransaction();
					shouldDisplay = false;
			}
		}
	}

	private void feedMoney() {
		String[] validAmounts = {"$1", "$5", "$10", "$20", "Cancel"};
		boolean askAgain = true;

		while (askAgain) {
			System.out.println("How much money would you like to pay? Whole dollar amounts only");
			String choice = (String) menu.getChoiceFromOptions(validAmounts);
			if(choice.equals("Cancel")) {
				break;
			}
			choice = choice.substring(1);
			BigDecimal moneyInput = new BigDecimal(choice);
			vendingMachine.addMoney(moneyInput);
			System.out.println("\nCurrent Money Provided: $" + vendingMachine.getBalance());
			auditLogger.logWithDateStamp("FEED MONEY: $" + moneyInput.setScale(2) + " $" + vendingMachine.getBalance());

			System.out.println("Would you like to add more money?");
			String[] options = {"Yes", "No"};
			String response = (String) menu.getChoiceFromOptions(options);

			if (response.equals("No")) {
				askAgain = false;
			}
		}

	}
	private void selectProduct() throws OutOfStockException, InvalidChoiceException, InsufficientFundsException {
		//System.out.println("What item would you like to purchase?");
		displayPurchaseItems();
		System.out.print("Choose the snack you would like to purchase >>> ");
		String idChoice = scanner.nextLine();
		VendingMachineItem itemToVend = null;
		List<VendingMachineItem> allItems = vendingMachine.getInventory();
		for (VendingMachineItem item: allItems) {
			if (idChoice.toUpperCase().equals(item.getId().toUpperCase())) {
				itemToVend = item;
				break;
			}
		}
		// If valid product has not been chosen, tell user
		if(itemToVend == null) {
			throw new InvalidChoiceException();
		}

		// If item is out of stock, inform user
		if(itemToVend.isSoldOut()) {
			throw new OutOfStockException();
		}

		if(vendingMachine.balance.compareTo(itemToVend.getPrice())< 0) {
			throw new InsufficientFundsException();
		}

		// If valid item is chosen, dispense it to customer
		dispenseProduct(itemToVend);
	}

	public void dispenseProduct(VendingMachineItem itemToVend) {
			BigDecimal previousBalance = vendingMachine.getBalance();
			itemToVend.reduceStock();

			vendingMachine.subtractMoney(itemToVend.getPrice());
			System.out.println("\nDispensing " + itemToVend.getName()+" $"+itemToVend.getPrice());
			System.out.println("Balance Remaining: $"+ vendingMachine.getBalance());
			System.out.println(itemToVend.getSound());

			auditLogger.logWithDateStamp(itemToVend.getName() + " " + itemToVend.getId() + " $" + previousBalance + " $" + vendingMachine.getBalance());

	}

	public void finishTransaction() {
		BigDecimal previousBalance = vendingMachine.getBalance();
		System.out.println(vendingMachine.dispenseChange());
		auditLogger.logWithDateStamp("GIVE CHANGE: $" + previousBalance + " $" + vendingMachine.getBalance());
	}



	private void printHiddenMenu() {
		final int MAX_STOCK = 5;
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy_HH-mm-ss");
		String dateTime = LocalDateTime.now().format(format);

		String fileName = "SalesReport_" + dateTime + ".txt";
		Logger totalSalesLogger = new Logger(fileName);

		BigDecimal totalSales = new BigDecimal("0.0");

		for(VendingMachineItem item : vendingMachine.getInventory()) {
			int quantity =  MAX_STOCK -  item.getStock();
			BigDecimal costForItem = new BigDecimal(quantity).multiply(item.getPrice());
			totalSales = totalSales.add(costForItem);
			totalSalesLogger.log(item.getName() + "|" + quantity);
		}

		totalSalesLogger.log("TOTAL SALES: $" + totalSales);
		totalSalesLogger.flush();
		try {
			totalSalesLogger.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
