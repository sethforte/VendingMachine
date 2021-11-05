package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class CandyTest {


    @Test
    public void candy_Sound_Is_Glug_Glug_Yum(){
        //ARRANGE
        Candy item = new Candy("Kit Kat",new BigDecimal("1.00"),"A2");
        //ACT
        String actual = item.getSound();
        String expected = "Munch Munch, Yum!";
        //ASSERT
        Assert.assertEquals(expected, actual);
    }



    @Test
    public void candy_Is_Max_Qty_On_Load(){
        //ARRANGE
        Candy item = new Candy("Kit Kat",new BigDecimal("1.00"),"A2");
        //ACT
        int actual = item.getStock();
        int expected = 5;
        //ASSERT
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void candy_A2_Id_Returns_A2_Id() {
        Candy item = new Candy("Kit Kat",new BigDecimal("1.00"),"A2");

        String actual = item.getId();
        String expected = "A2";

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void candy_Kit_Kat_Name_Returns_Kit_Kat_Name() {
        Candy item = new Candy("Kit Kat",new BigDecimal("1.00"),"A2");

        String actual = item.getName();
        String expected = "Kit Kat";

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void candy_1_Dollar_Price_Returns_1_Dollar_Price() {
        Candy item = new Candy("Kit Kat",new BigDecimal("1.00"),"A2");

        BigDecimal actual = item.getPrice();
        BigDecimal expected = new BigDecimal("1.00");

        Assert.assertEquals(expected.compareTo(actual), 0);
    }

    @Test
    public void test_Is_SoldOut_Is_True_When_Stock_Is_0() {
        Candy item = new Candy("Kit Kat",new BigDecimal("1.00"),"A2");
        for(int i = 0; i < 5; i++) {
            item.reduceStock();
        }

        boolean expected = true;
        boolean actual = item.isSoldOut();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_Is_SoldOut_Is_False_When_Stock_Is_1() {
        Candy item = new Candy("Kit Kat",new BigDecimal("1.00"),"A2");
        for(int i = 0; i < 4; i++) {
            item.reduceStock();
        }

        boolean expected = false;
        boolean actual = item.isSoldOut();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_Reduce_Stock_When_Not_Sold_Out_Should_Return_True() {
        Candy item = new Candy("Kit Kat",new BigDecimal("1.00"),"A2");

        boolean expected = true;
        boolean actual = item.reduceStock();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_Reduce_Stock_When_Sold_Out_Should_Return_False() {
        Candy item = new Candy("Kit Kat",new BigDecimal("1.00"),"A2");
        for(int i = 0; i < 5; i++) {
            item.reduceStock();
        }

        boolean expected = false;
        boolean actual = item.reduceStock();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void to_String_Should_Return_Correctly() {
        Candy item = new Candy("Kit Kat",new BigDecimal("1.00"),"A2");

        String expected = "A2 Kit Kat 1.00";
        String actual = item.toString();

        Assert.assertEquals(expected, actual);
    }
}
