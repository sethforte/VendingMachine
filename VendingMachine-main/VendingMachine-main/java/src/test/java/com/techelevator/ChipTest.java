package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ChipTest {


    @Test
    public void chipSoundIsCrunchCrunchYum(){
        //ARRANGE
        Chip item = new Chip("Pringles",new BigDecimal("5.00"),"A2");
        //ACT
        String actual = item.getSound();
        String expected = "Crunch Crunch, Yum!";
        //ASSERT
        Assert.assertEquals(expected, actual);
    }



    @Test
    public void chipIsMaxQtyOnLoad(){
        //ARRANGE
        Chip item = new Chip("Pringles",new BigDecimal("5.00"),"A2");
        //ACT
        int actual = item.getStock();
        int expected = 5;
        //ASSERT
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void chip_A2_Id_Returns_A2_Id() {
        Chip item = new Chip("Pringles",new BigDecimal("5.00"),"A2");

        String actual = item.getId();
        String expected = "A2";

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void chip_Pringles_Name_Returns_Pringles_Name() {
        Chip item = new Chip("Pringles",new BigDecimal("5.00"),"A2");

        String actual = item.getName();
        String expected = "Pringles";

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void chip_1_Dollar_Price_Returns_1_Dollar_Price() {
        Chip item = new Chip("Pringles",new BigDecimal("5.00"),"A2");

        BigDecimal actual = item.getPrice();
        BigDecimal expected = new BigDecimal("5.00");

        Assert.assertEquals(expected.compareTo(actual), 0);
    }

    @Test
    public void test_Is_SoldOut_Is_True_When_Stock_Is_0() {
        Chip item = new Chip("Pringles",new BigDecimal("5.00"),"A2");
        for(int i = 0; i < 5; i++) {
            item.reduceStock();
        }

        boolean expected = true;
        boolean actual = item.isSoldOut();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_Is_SoldOut_Is_False_When_Stock_Is_1() {
        Chip item = new Chip("Pringles",new BigDecimal("5.00"),"A2");
        for(int i = 0; i < 4; i++) {
            item.reduceStock();
        }

        boolean expected = false;
        boolean actual = item.isSoldOut();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_Reduce_Stock_When_Not_Sold_Out_Should_Return_True() {
        Chip item = new Chip("Pringles",new BigDecimal("5.00"),"A2");

        boolean expected = true;
        boolean actual = item.reduceStock();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_Reduce_Stock_When_Sold_Out_Should_Return_False() {
        Chip item = new Chip("Pringles",new BigDecimal("5.00"),"A2");
        for(int i = 0; i < 5; i++) {
            item.reduceStock();
        }

        boolean expected = false;
        boolean actual = item.reduceStock();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void to_String_Should_Return_Correctly() {
        Chip item = new Chip("Pringles",new BigDecimal("5.00"),"A2");

        String expected = "A2 Pringles 5.00";
        String actual = item.toString();

        Assert.assertEquals(expected, actual);
    }
}
