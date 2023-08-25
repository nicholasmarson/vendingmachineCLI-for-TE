package com.techelevator;

public class Gum extends Product implements Buyable {

    public Gum (String slotNumber, String itemName, String itemType, double itemPrice, int itemQuantity) {
        super(slotNumber, itemName, itemType, itemPrice, itemQuantity);
    }
}
