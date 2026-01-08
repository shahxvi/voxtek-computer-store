// MIT License
// Copyright (c) 2025 Shah

package user.admin;

import product.*;
import input.Processor;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 * AdminFileHandler - Handles all file I/O operations for admin functionality.
 * 
 * This class is part of the admin subsystem architecture:
 * - Called by Admin.flow() to handle background file operations
 * - Manages loading/saving admin credentials
 * - Manages adding/removing products from inventory
 * 
 * Main should not call this directly - all calls go through Admin.flow()
 */
class AdminFileHandler implements Processor {

    /*
     * initializes the admin (loads admin from file to object)
     * if the file is missing or empty, it calls createAdminFile
     */
    public static boolean initializeAdmin(Admin admin, File file) {
        boolean isInitialized = false;

        while (!isInitialized) {
            try {
                loadAdmin(admin, file);
                isInitialized = true;
            } catch (FileNotFoundException e) {
                admin = AdminUI.createAdmin();
                boolean isCreated = createAdminFile(admin, file);
                if (!isCreated) {
                    return false; // User chose to exit
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

        return isInitialized;
    }

    /*
     * Loads admin from file to object (throws an exception)
     */
    public static boolean loadAdmin(Admin admin, File file) throws FileNotFoundException {
        try (Scanner inputFile = new Scanner(file)) {
            // Checks if empty or not
            if (!inputFile.hasNextLine()) {
                inputFile.close();
                throw new FileNotFoundException();
            }

            while (inputFile.hasNext()) {
                StringTokenizer token = new StringTokenizer(inputFile.nextLine(), ";");
                admin.setName(token.nextToken());
                admin.setPhoneNumber(Integer.parseInt(token.nextToken()));
                admin.setId(Integer.parseInt(token.nextToken()));
                admin.setPassword(token.nextToken());
            }
        }
        return true;
    }

    /*
     * Creates the admin file if it isn't already
     */
     static boolean createAdminFile(Admin admin, File file) {
        if (admin == null) {
            return false;
        }

        try (PrintWriter adminFile = new PrintWriter(file)) {
            adminFile.println(admin.toRecord());
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }

        return true;
    }

    public static Product[] addLaptop(Product[] products) {
        int emptyPointer = 0;
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null)
                emptyPointer = i;
        }
        Laptop laptop = AdminUI.createLaptop();

        products[emptyPointer] = laptop;

        return products;
    }

    //public static Product[] addKeyboard(Product[] products) {
    //    int emptyPointer = 0;
    //    for (int i = 0; i < products.length; i++) {
    //        if (products[i] == null)
    //            emptyPointer = i;
    //    }
    //    Keyboard keyboard = AdminUI.createKeyboard();
    //
    //    products[emptyPointer] = keyboard;
    //
    //    return products;
    //}
    //
    static Product[] removeProduct(Product[] products) {
        String chosenProduct = AdminUI.chooseProductToRemoveProduct(products);

        if (chosenProduct == null)
            return products;

        // Get the index of the chosen option
        int removedItem = -1;
        for (int i = 0; i < products.length; i++) {
            if (chosenProduct.equalsIgnoreCase(products[i].toRecord())) {
                removedItem = i;
                break; // Exit loop when match is found
            }
        }

        products[removedItem] = null;

        return Processor.reorganizeInventory(products);
    }
}
