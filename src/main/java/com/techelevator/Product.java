package com.techelevator;

public class Product implements Buyable{
    private String slotNumber;
    private String itemName;
    private String itemType;
    private double itemPrice;
    private int itemQuantity = 5;

    public Product(String slotNumber, String itemName, String itemType, double itemPrice, int itemQuantity) {
        this.slotNumber = slotNumber;
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
    }

    public Product() {

    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public boolean isSoldOut() {
        return itemQuantity == 0;

    }
    public void decreaseQuantityCounter() {
        if (itemQuantity > 0){
            itemQuantity--;
        }
    }
}


