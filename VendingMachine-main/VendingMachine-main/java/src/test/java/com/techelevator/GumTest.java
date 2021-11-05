package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class GumTest {


    @Test
    public void gum_Sound_Is_Chew_Chew_Yum_Yum(){
        //ARRANGE
        Gum item = new Gum("Winderfresh",new BigDecimal("5.00"),"A2");
        //ACT
        String actual = item.getSound();
        String expected = "Chew Chew, Yum!";
        //ASSERT
        Assert.assertEquals(expected, actual);
    }



    @Test
    public void gum_Is_Max_Qty_On_Load(){
        //ARRANGE
        Gum item = new Gum("Winderfresh",new BigDecimal("5.00"),"A2");
        //ACT
        int actual = item.getStock();
        int expected = 5;
        //ASSERT
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void gum_A2_Id_Returns_A2_Id() {
        Gum item = new Gum("Winderfresh",new BigDecimal("1.00"),"A2");

        String actual = item.getId();
        String expected = "A2";

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void gum_Winderfresh_Name_Returns_Winderfresh_Name() {
        Gum item = new Gum("Winderfresh",new BigDecimal("1.00"),"A2");

        String actual = item.getName();
        String expected = "Winderfresh";

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void candy_1_Dollar_Price_Returns_1_Dollar_Price() {
        Gum item = new Gum("Winderfresh",new BigDecimal("1.00"),"A2");

        BigDecimal actual = item.getPrice();
        BigDecimal expected = new BigDecimal("1.00");

        Assert.assertEquals(expected.compareTo(actual), 0);
    }

    @Test
    public void test_Is_SoldOut_Is_True_When_Stock_Is_0() {
        Gum item = new Gum("Winderfresh",new BigDecimal("1.00"),"A2");
        for(int i = 0; i < 5; i++) {
            item.reduceStock();
        }

        boolean expected = true;
        boolean actual = item.isSoldOut();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_Is_SoldOut_Is_False_When_Stock_Is_1() {
        Gum item = new Gum("Winderfresh",new BigDecimal("1.00"),"A2");
        for(int i = 0; i < 4; i++) {
            item.reduceStock();
        }

        boolean expected = false;
        boolean actual = item.isSoldOut();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_Reduce_Stock_When_Not_Sold_Out_Should_Return_True() {
        Gum item = new Gum("Winderfresh",new BigDecimal("1.00"),"A2");

        boolean expected = true;
        boolean actual = item.reduceStock();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_Reduce_Stock_When_Sold_Out_Should_Return_False() {
        Gum item = new Gum("Winderfresh",new BigDecimal("1.00"),"A2");
        for(int i = 0; i < 5; i++) {
            item.reduceStock();
        }

        boolean expected = false;
        boolean actual = item.reduceStock();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void to_String_Should_Return_Correctly() {
        Gum item = new Gum("Winderfresh",new BigDecimal("1.00"),"A2");

        String expected = "A2 Winderfresh 1.00";
        String actual = item.toString();

        Assert.assertEquals(expected, actual);
    }
}
