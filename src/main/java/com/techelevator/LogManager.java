package com.techelevator;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LogManager {
    private FileWriter writer;
    private AccountManager accountManager;

    public LogManager(AccountManager accountManager) throws IOException {
        this.accountManager = accountManager;
        this.writer = new FileWriter("Log.txt", true);
    }

    public void logFeedMoney(double depositAmount) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String time = LocalTime.now().format(formatter);
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        double balance = accountManager.getVendingMachineBalance();
        String depositAmountDecimalFormat = AccountManager.DECIMAL_FORMAT.format(depositAmount);
        String balanceDecimalFormat = AccountManager.DECIMAL_FORMAT.format(balance);
        writer.write(date + " " + time + " FEED MONEY: $" + depositAmountDecimalFormat + " $" + balanceDecimalFormat + "\n");
        writer.flush();
    }

    public void logPurchase(String itemName, String slotNumber, double itemPrice) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String time = LocalTime.now().format(formatter);
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        double balance = accountManager.getVendingMachineBalance();
        String balanceDecimalFormat = AccountManager.DECIMAL_FORMAT.format(balance);
        String itemPriceDecimalFormat = AccountManager.DECIMAL_FORMAT.format(itemPrice);

        writer.write(date + " " + time + " " + itemName + " " + slotNumber + " $" + itemPriceDecimalFormat + " $" + balanceDecimalFormat + "\n");
        writer.flush();
    }

    public void logChange(double changeInDollars) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String time = LocalTime.now().format(formatter);
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        double balance = accountManager.getVendingMachineBalance();
        String balanceDecimalFormat = AccountManager.DECIMAL_FORMAT.format(balance);
        String changeDecimalFormat = AccountManager.DECIMAL_FORMAT.format(changeInDollars);

        writer.write(date + " " + time + " GIVE CHANGE: $" + changeDecimalFormat + " $" + balanceDecimalFormat + "\n");
        writer.flush();

    }
}