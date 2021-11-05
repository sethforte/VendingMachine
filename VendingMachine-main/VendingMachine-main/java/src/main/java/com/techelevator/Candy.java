package com.techelevator;

import java.math.BigDecimal;

public class Candy extends VendingMachineItem{
    public Candy(String name, BigDecimal price, String id) {
        super(name, price, id);
    }

    @Override
    public String getSound() {
        return "Munch Munch, Yum!";
    }
}
