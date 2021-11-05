package com.techelevator.exceptions;

public class OutOfStockException extends Exception {

    public OutOfStockException() {
        super("Sorry, that item is out of stock!");
    }

}
