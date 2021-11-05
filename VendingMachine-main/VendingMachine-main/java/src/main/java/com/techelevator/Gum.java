package com.techelevator;

import java.math.BigDecimal;

public class Gum extends VendingMachineItem{
    public Gum(String name, BigDecimal price, String id) {
        super(name, price, id);

    }

    @Override
    public String getSound() {
        return "Chew Chew, Yum!";
    }
}
