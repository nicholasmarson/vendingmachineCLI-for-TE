package com.techelevator;

public class Munchy extends Product implements Buyable {

    public Munchy(String slotNumber, String itemName, String itemType, double itemPrice, int itemQuantity) {
        super(slotNumber, itemName, itemType, itemPrice, itemQuantity);
    }
}
