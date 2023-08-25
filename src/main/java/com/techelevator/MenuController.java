package com.techelevator;

import java.util.Scanner;

public class MenuController {

    //what does the menu controller have, and what does it do

    Scanner userInput = new Scanner(System.in);
    AccountManager accountManager = new AccountManager();

    public void displayMainMenu() {

        System.out.println();
        System.out.println("Please select an option :\n\n(1) Display Vending Machine Items\n(2) Purchase\n(3) Exit");
        System.out.println();

    }
    public void displayPurchaseMenu() {

        System.out.println();
        System.out.println("(1) Feed Money\n(2) Select Product\n(3) Finish Transaction");
        System.out.println();
    }

}