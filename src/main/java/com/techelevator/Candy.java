package com.techelevator;

public class Candy extends Product implements Buyable {
    public Candy(String slotNumber, String itemName, String itemType, double itemPrice, int itemQuantity) {
        super(slotNumber, itemName, itemType, itemPrice, itemQuantity);
    }
}