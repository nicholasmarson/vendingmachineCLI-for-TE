package com.techelevator;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

/*
 * This class is provided to you as a *suggested* class to start
 * your project. Feel free to refactor this code as you see fit.
 */
public class VendingMachineCLI {
	public static void main(String[] args) throws IOException {
		try {
		Scanner userInput = new Scanner(System.in);

		AccountManager accountManager = null;
		LogManager logManager = new LogManager(accountManager);

		accountManager = new AccountManager();
		InventoryManager inventoryController = new InventoryManager();
		inventoryController.setVendingMachineInventoryMap("E:\\workspace\\java-green-minicapstonemodule1-team0\\main.csv");
		inventoryController.getVendingMachineInventoryMap();
		MenuController menuController = new MenuController();
		FileWriter writer = new FileWriter("Log.txt", true);

		double itemPrice;
		boolean vendingMachineRunning = true;
		boolean insidePurchaseMenu = false;
		int bogodoCounter = 0;
		int changeInSmallestCents;
		int quarter;
		int nickel;
		int dime;


		while (vendingMachineRunning) {

			menuController.displayMainMenu();
			String mainMenuSelection = userInput.nextLine();

			if (mainMenuSelection.equalsIgnoreCase("1")) {
				inventoryController.displayChosenMapData();
			} else if (mainMenuSelection.equalsIgnoreCase("2")) {
				insidePurchaseMenu = true;
			} else if (mainMenuSelection.equalsIgnoreCase("3")) {
				vendingMachineRunning = false;
			}


			while (insidePurchaseMenu) {
				menuController.displayPurchaseMenu();
				String purchaseMenuSelection = userInput.nextLine();

				if (purchaseMenuSelection.equalsIgnoreCase("1")) {
					accountManager.feedMoney();
				} else if (purchaseMenuSelection.equalsIgnoreCase("2")) {
					inventoryController.displayChosenMapData();
					System.out.println();
					System.out.println("Please make a selection: ");
					System.out.println();
					String selection = userInput.nextLine().trim().toUpperCase();

					if (mainMenuSelection.equalsIgnoreCase("3")) {
						insidePurchaseMenu = false;
						break;
					}
					if (inventoryController.getVendingMachineInventoryMap().containsKey(selection)) {
						Product product = (Product) inventoryController.getVendingMachineInventoryMap().get(selection);
						if (product.isSoldOut()) {
							System.out.println("SELECTION IS SOLD OUT");
						} else {
							itemPrice = product.getItemPrice();
							if (itemPrice > accountManager.getVendingMachineBalance()) {
								System.out.println("Insufficient funds. Please insert more money to continue with your purchase");
							} else {
								inventoryController.dispenseItem(selection);

								bogodoCounter++;

								if (bogodoCounter % 2 == 0) {
									accountManager.applyBuyOneGetOneDollarOffDiscount();
									accountManager.deductFromBalance(selection);
								} else {
									accountManager.deductFromBalance(selection);
								}
							}
						}
					}
				} else if (purchaseMenuSelection.equalsIgnoreCase("3")) {
					accountManager.dispenseChange();
					bogodoCounter = 0;
					insidePurchaseMenu = false;
					break;
				}
			}
		}

		writer.close();


	}catch (IOException e) {
		System.out.println("Error writing to log file: " + e.getMessage());
	}
		// we need to calculate the change and give it back to the user, using nickels, dimes and quarters in the smallest amount of change possible

	}
}