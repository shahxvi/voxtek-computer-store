// MIT License
// Copyright (c) 2025 Shah
// Copyright (c) 2025 Marzell

import processors.Processor;
import users.*;
import users.admins.*;
import users.customers.*;
import products.*;

import java.io.*;
import javax.swing.*;

public class Main implements Processor {
    public static void main(String[] args) {
        final int laptopIndex = 0, keyboardIndex = 1;
        final int adminIndex = 0, membersIndex = 1, customerIndex = 2;

        File adminFile = new File("admin.txt");

        File[] inventoryFiles = {
                new File("laptops.txt"),
                new File("keyboards.txt"),
        };

        Product[][] products = {
                new Laptop[50],
                new Keyboard[50]
        };
        Product.initializeInventory(products, inventoryFiles);

        Customer customer;
        Admin admin = new Admin();

        int intOption;
        String strOption;
        boolean choseCustomer, choseExit, choseAdmin;

        // The crux of the program
        do {
            intOption = UserUI.menu();

            // Checks intOption and determine what the user chose
            choseCustomer = (intOption == 0);
            choseAdmin = (intOption == 1);
            choseExit = (intOption == 2 || intOption == -1);

            if (choseCustomer) {
                CustomerUI.run(products);
            } else if (choseAdmin) {
                AdminUI.run(products, admin, adminFile);
            }
        } while (!choseExit);

        for (Product p : products[laptopIndex]) {
            if (p == null) {
                System.out.println("null");
            } else {
                System.out.println(p.toShortString());
            }
        }

        writeToFile(products[laptopIndex], inventoryFiles[laptopIndex]);
        writeToFile(products[keyboardIndex], inventoryFiles[keyboardIndex]);

        System.exit(0);
    }

    public static void writeToFile(Product[] product, File file) {
        product = Processor.reorganizeInventory(product);

        try (PrintWriter outputFile = new PrintWriter(file)) {
            for (int i = 0; i < product.length; i++) {
                if (product[i] != null) {
                    outputFile.println(product[i].toRecord());
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
