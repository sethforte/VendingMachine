package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class VendingMachineTest {

    @Test
    public void Stock_Vending_Machine_inventory_is_greater_than_zero() {
        VendingMachine vm = new VendingMachine();
        Assert.assertTrue(vm.getInventory().size() > 0);
    }

    @Test
    public void add_Money_Test_5_dollars_increases_balance_by_5_dollars() {
        VendingMachine vm = new VendingMachine();
        BigDecimal expected = new BigDecimal("5.00");
        BigDecimal actual = vm.addMoney(new BigDecimal("5.00"));
        Assert.assertEquals(expected.compareTo(actual), 0);
    }

    @Test
    public void subtract_Money_Test_5_dollars_lowers_balance_by_5_dollars() {
        VendingMachine vm = new VendingMachine();
        BigDecimal expected = new BigDecimal("-5.00");
        BigDecimal actual = vm.subtractMoney(new BigDecimal("5.00"));
        Assert.assertEquals(expected.compareTo(actual), 0);
    }

    @Test
    public void testGetBalance_zero_balance_returns_zero_balance() {
        VendingMachine vm = new VendingMachine();
        BigDecimal expected = new BigDecimal("0.00");
        BigDecimal actual = vm.getBalance();
        Assert.assertEquals(expected.compareTo(actual), 0);
    }

    @Test
    public void testDispenseChange_0_balance_returns_0_quarters_0_dimes_0_nickles() {
        VendingMachine vm = new VendingMachine();
        String expected = "0 quarters, 0 dimes, 0 nickels is your change.";
        String actual = vm.dispenseChange();
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testDispenseChange_40_cents_balance_returns_1_quarters_1_dimes_1_nickles() {
        VendingMachine vm = new VendingMachine();

        vm.addMoney(new BigDecimal("0.40"));
        String actual = vm.dispenseChange();
        String expected = "1 quarters, 1 dimes, 1 nickels is your change.";

        Assert.assertEquals(expected, actual);

    }

}