package com.techelevator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class InventoryManager {

    //we want to read the CSV file here and put each object in the CSV into the Map. The CSV is
    //separated by commas, and we want to split the commas in order to index the Map properly.

    private static Map<String, Buyable> vendingMachineInventoryMap = new HashMap<>();

    Product chosenProduct = new Product(); // we can delete this if we want, as i dont think it is being used anywhere


    public Map<String, Buyable> getVendingMachineInventoryMap() { // we can delete this if we want, as i dont think it is being used anywhere
        return vendingMachineInventoryMap;
    }
    public void displayChosenMapData() {
        for(Map.Entry<String, Buyable> item : vendingMachineInventoryMap.entrySet()) {
            System.out.println(item.getKey() + " - " +  item.getValue().getItemName() + " - $" + item.getValue().getItemPrice() + " - " + item.getValue().getItemType() + " - " + item.getValue().getItemQuantity() + " available");
        }
    }

    public void dispenseItem(String selection) {

        Product product = (Product) vendingMachineInventoryMap.get(selection);

        if (product.isSoldOut()) {
            System.out.println("SOLD OUT");
        }
        else {
            System.out.print("Dispensing: " + product.getItemName() + " - $" + product.getItemPrice() + " - ");
            if (product instanceof Gum) {
                System.out.println("Chew Chew, Yum!");
            } else if (product instanceof Candy) {
                System.out.println("Yummy Yummy, So Sweet!");
            } else if (product instanceof Munchy) {
                System.out.println("Crunch Crunch, Yum!");
            } else if (product instanceof Drink) {
                System.out.println("Glug Glug, Yum!");
            }
            product.decreaseQuantityCounter();
        }
    }



    public Map<String, Buyable> setVendingMachineInventoryMap(String inventoryFromCsvFile) throws FileNotFoundException {
        try (BufferedReader newBufferedReader = new BufferedReader(new FileReader(inventoryFromCsvFile))) {
            String lineInFile;

            while (((lineInFile = newBufferedReader.readLine()) != null)) {
                //if there is something to read in the line, we want to split the lime by its commas
                String[] linesSeperatedByCommas = lineInFile.split(",");

                //now that we have split the lines by the commas and have them seperated, we need to
                //take them out and store them somewhere
                String slotNumber = linesSeperatedByCommas[0].trim();
                String itemName = linesSeperatedByCommas[1];
                double itemPrice = Double.parseDouble(linesSeperatedByCommas[2]);
                String itemType = linesSeperatedByCommas[3];
                int itemQuantity = 5;

                //now we have to set each item type to its class, and were using a switch because
                //thats what google said was best for this type of application of data

                Buyable currentProduct;

                switch (itemType) {
                    case "Gum":
                        currentProduct = new Gum(slotNumber, itemName, itemType, itemPrice, itemQuantity);
                        break;
                    case "Candy":
                        currentProduct = new Candy(slotNumber, itemName, itemType, itemPrice, itemQuantity);
                        break;
                    case "Munchy":
                        currentProduct = new Munchy(slotNumber, itemName, itemType, itemPrice, itemQuantity);
                        break;
                    case "Drink":
                        currentProduct = new Drink(slotNumber, itemName, itemType, itemPrice, itemQuantity);
                        break;

                    default:
                        throw new IllegalStateException("Unexpected value: " + itemType);
                }
                //now that everything is seperated and ordered by index, we can add everything to the Map
                //using key and value pairs Map<String, Buyable>

                vendingMachineInventoryMap.put(slotNumber, currentProduct);

            } // so now we have taken the data from the CSV file, seperated and split is by its index in the line,
            // and put all of its info into the Map with the slot number bring the key and the value being the
            // product that currently in that slot number

        } catch (IOException e) {
            e.printStackTrace();
        }
        return vendingMachineInventoryMap;
    }

}