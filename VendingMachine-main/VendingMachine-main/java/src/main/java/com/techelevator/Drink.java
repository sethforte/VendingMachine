package com.techelevator;

import java.math.BigDecimal;

public class Drink extends VendingMachineItem{
    public Drink(String name, BigDecimal price, String id) {
        super(name, price, id);
    }

    @Override
    public String getSound() {
        return "Glug Glug, Yum!";
    }
}
