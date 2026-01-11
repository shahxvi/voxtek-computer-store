// MIT License
// Copyright (c) 2025 Shah

package users.admins;

import products.*;
import processors.Processor;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

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

        JOptionPane.showMessageDialog(null, "Admin File Created!");

        return true;
    }

    public static Product[] addProduct(Product[] products) {
        int emptyPointer = 0;
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null)
                emptyPointer = i;
        }

        Product addedProduct = null;
        if (products instanceof Laptop[]) {
            addedProduct = AdminUI.createLaptop();
        } else if (products instanceof Keyboard[]) {
            addedProduct = AdminUI.createKeyboard();
        }

        products[emptyPointer] = addedProduct;

        return products;
    }

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

        JOptionPane.showMessageDialog(null, "Product removed");

        return Processor.reorganizeInventory(products);
    }
}
