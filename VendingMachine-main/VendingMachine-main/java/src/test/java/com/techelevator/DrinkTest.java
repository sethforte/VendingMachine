package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class DrinkTest {



    @Test
    public void drink_Sound_Is_Glug_Glug_Yum(){
        //ARRANGE
        Drink drink = new Drink("Water",new BigDecimal("1.00"),"A2");
        //ACT
        String actual = drink.getSound();
        String expected = "Glug Glug, Yum!";
        //ASSERT
        Assert.assertEquals(expected, actual);
    }



    @Test
    public void drink_Is_Max_Qty_On_Load(){
        //ARRANGE
        Drink drink = new Drink("Water",new BigDecimal("1.00"),"B2");
        //ACT
        int actual = drink.getStock();
        int expected = 5;
        //ASSERT
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void drink_A2_Id_Returns_A2_Id() {
        Drink drink = new Drink("Water", new BigDecimal("1.00"),"A2");

        String actual = drink.getId();
        String expected = "A2";

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void drink_Water_Name_Returns_Water_Name() {
        Drink drink = new Drink("Water", new BigDecimal("1.00"),"A2");

        String actual = drink.getName();
        String expected = "Water";

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void drink_1_Dollar_Price_Returns_1_Dollar_Price() {
        Drink drink = new Drink("Water", new BigDecimal("1.00"),"A2");

        BigDecimal actual = drink.getPrice();
        BigDecimal expected = new BigDecimal("1.00");

        Assert.assertEquals(expected.compareTo(actual), 0);
    }

    @Test
    public void test_Is_SoldOut_Is_True_When_Stock_Is_0() {
        Drink drink = new Drink("Water", new BigDecimal("1.00"),"A2");
        for(int i = 0; i < 5; i++) {
            drink.reduceStock();
        }

        boolean expected = true;
        boolean actual = drink.isSoldOut();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_Is_SoldOut_Is_False_When_Stock_Is_1() {
        Drink drink = new Drink("Water", new BigDecimal("1.00"),"A2");
        for(int i = 0; i < 4; i++) {
            drink.reduceStock();
        }

        boolean expected = false;
        boolean actual = drink.isSoldOut();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_Reduce_Stock_When_Not_SoldOut_Should_Return_True() {
        Drink drink = new Drink("Water", new BigDecimal("1.00"),"A2");

        boolean expected = true;
        boolean actual = drink.reduceStock();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_Reduce_Stock_When_SoldOut_Should_Return_False() {
        Drink drink = new Drink("Water", new BigDecimal("1.00"),"A2");
        for(int i = 0; i < 5; i++) {
            drink.reduceStock();
        }

        boolean expected = false;
        boolean actual = drink.reduceStock();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void toString_Should_Return_Correctly() {
        Drink drink = new Drink("Water", new BigDecimal("1.00"),"A2");

        String expected = "A2 Water 1.00";
        String actual = drink.toString();

        Assert.assertEquals(expected, actual);
    }
}
