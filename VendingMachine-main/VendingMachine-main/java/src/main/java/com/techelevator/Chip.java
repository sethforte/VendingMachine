package com.techelevator;

import java.math.BigDecimal;

public class Chip extends VendingMachineItem{
    public Chip(String name, BigDecimal price, String id) {
        super(name, price, id);
    }

    @Override
    public String getSound() {
        return "Crunch Crunch, Yum!";
    }
}
