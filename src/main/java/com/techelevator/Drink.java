package com.techelevator;

public class Drink extends Product implements Buyable {

    public Drink(String slotNumber, String itemName, String itemType, double itemPrice, int itemQuantity) {
        super(slotNumber, itemName, itemType, itemPrice, itemQuantity);
    }
}