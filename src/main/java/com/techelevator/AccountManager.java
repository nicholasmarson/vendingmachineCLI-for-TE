package com.techelevator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class AccountManager {

    Scanner userInput = new Scanner(System.in);

    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
    private double vendingMachineBalance = 0;
    private int quarter;
    private int dime;
    private int nickel;
    private int changeInSmallestCents;
    private double depositAmount;

    LogManager logManager;

    public AccountManager() {
        try {
            logManager = new LogManager(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setVendingMachineBalance(double vendingMachineBalance) {
        this.vendingMachineBalance = vendingMachineBalance;
    }


    public double getVendingMachineBalance() {
        return this.vendingMachineBalance;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void feedMoney() throws IOException {

        FileWriter writer = new FileWriter("Log.txt", true);

        System.out.println();
        System.out.println("How much money would you like to deposit?");
        System.out.println();
        depositAmount = Double.parseDouble(userInput.nextLine());
        setVendingMachineBalance(getVendingMachineBalance() + depositAmount);
        System.out.println("Your current balance is $" + DECIMAL_FORMAT.format(getVendingMachineBalance()));
        logManager.logFeedMoney(depositAmount);
        writer.flush();

    }



    public void deductFromBalance(String selection) throws IOException {

        FileWriter writer = new FileWriter("Log.txt", true);

        InventoryManager inventoryManager = new InventoryManager(); // the map is static, so we shouldn't need this instance but it works so we're not changing it right now (;
        //inventoryManager.getVendingMachineInventoryMap();
        setVendingMachineBalance(vendingMachineBalance);
        vendingMachineBalance = (getVendingMachineBalance() - inventoryManager.getVendingMachineInventoryMap().get(selection).getItemPrice());
        System.out.println("Your new balance is: $" + DECIMAL_FORMAT.format(getVendingMachineBalance()));
        System.out.println();
        double itemPrice = inventoryManager.getVendingMachineInventoryMap().get(selection).getItemPrice();
        String product = inventoryManager.getVendingMachineInventoryMap().get(selection).getItemName();
        String itemName = inventoryManager.getVendingMachineInventoryMap().get(selection).getItemName();
        String slotNumber = inventoryManager.getVendingMachineInventoryMap().get(selection).getSlotNumber();
        logManager.logPurchase(itemName, slotNumber, itemPrice);
        writer.flush();

    }

    public void dispenseChange() throws IOException{

        FileWriter writer = new FileWriter("Log.txt", true);

        int changeInSmallestCents = (int) (getVendingMachineBalance() * 100);
        quarter = changeInSmallestCents % 25;
        dime = changeInSmallestCents % 10;
        nickel = changeInSmallestCents % 5;
        double changeInDollars = changeInSmallestCents / 100.0;

        System.out.println();
        System.out.println("Your change is: " + getVendingMachineBalance() + " in " + quarter + " quarters, " + nickel + " nickels, and " + dime + " dimes");
        System.out.println();
        setVendingMachineBalance(0.00);
        System.out.println("Remaining vending machine balance: " + DECIMAL_FORMAT.format(getVendingMachineBalance()));
        System.out.println();
        logManager.logChange(changeInDollars);
        writer.flush();


    }

    public int getChangeInSmallestCents() {
        return changeInSmallestCents;
    }

    public void applyBuyOneGetOneDollarOffDiscount() {

        System.out.println("It's BOGODO for August! You get a $1 off!");
        System.out.println();
        vendingMachineBalance += 1.00;


    }
}