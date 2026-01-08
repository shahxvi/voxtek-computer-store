// MIT License
// Copyright (c) 2025 Shah
// Copyright (c) 2025 Marzell

import input.Processor;
import user.*;
import user.admin.*;
import user.customer.*;
import product.*;

import java.io.*;
import javax.swing.*;

public class Main implements Processor {
    public static void main(String[] args) {
        final int laptopIndex = 0, keyboardIndex = 1;
        final int adminIndex = 0, membersIndex = 1, customerIndex = 2;

        File[] inventoryFiles = {
                new File("laptops.txt"),
                new File("keyboards.txt"),
        };

        Product[][] products = {
                new Laptop[50],
                new Keyboard[50]
        };
        Product.initializeInventory(products, inventoryFiles);

        File adminFile = new File("admin.txt");
        File membersFile = new File("members.txt");

        User user;

        int intOption;
        String strOption;
        boolean choseCustomer, choseExit, choseAdmin, browse, login, register;

        // The crux of the program
        do {
            intOption = UserUI.menu();

            // Checks intOption and determine what the user chose
            choseCustomer = (intOption == 0);
            choseAdmin = (intOption == 1);
            choseExit = (intOption == 2 || intOption == -1);

            if (choseCustomer) {
                user = CustomerUI.createCustomer();
                if (user == null)
                    continue;

                strOption = UserUI.chooseInventory();
                if (strOption.equals("Laptops")) {
                    intOption = laptopIndex;
                } else if (strOption.equals("Keyboards")) {
                    intOption = keyboardIndex;
                }

                Product product = CustomerUI.selectProduct(products[intOption]);
                if (product == null)
                    continue;

                ((Customer) user).setProduct(product);
            } else if (choseAdmin) {
                //user = new Admin();
                //Admin.flow(products, user, adminFile);
            }
        } while (!choseExit);

        writeToFile(products[laptopIndex], inventoryFiles[laptopIndex]);
        writeToFile(products[keyboardIndex], inventoryFiles[keyboardIndex]);

        System.exit(0);
    }

    public static void writeToFile(Product[] product, File file) {
        try {
            PrintWriter outputFile = new PrintWriter(file);
            for (int i = 0; i < Processor.getUsableArraySize(product); i++) {
                outputFile.println(product[i].toRecord());
            }
            outputFile.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
