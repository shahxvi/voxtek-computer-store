// MIT License
// Copyright (c) 2025 Shah
// Copyright (c) 2025 Marzell

import input.Processor;
import user.*;
import user.admin.*;
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

        Admin admin = new Admin();

        // Get the inventory size of all products and make a cart array
        int totalProducts = Processor.getInventorySize(inventoryFiles[laptopIndex]) + Processor.getInventorySize(inventoryFiles[laptopIndex]);
        Product[] customersCart = new Product[totalProducts];
        int cartCounter = 0;

        int intOption;
        String strOption;
        boolean choseCustomer, choseExit, choseAdmin, browse, login, register;

        // The crux of the program
        do {
            intOption = User.menu();

            // Checks intOption and determine what the user chose
            choseCustomer = (intOption == 0);
            choseAdmin = (intOption == 1);
            choseExit = (intOption == 2 || intOption == -1);

            if (choseCustomer) {
                //do {
                //    intOption = Customer.menu();

                //    browse = (intOption == 0);
                //    login = (intOption == 1);
                //    register = (intOption == 2);
                //    choseExit = (intOption == 3 || intOption == -1);

                //    if (browse) {
                //        strOption = chooseInventory();

                //        boolean exit = (strOption == null);
                //        boolean laptops = (strOption.equals("Laptops"));
                //        boolean keyboards = (strOption.equals("Keyboards"));

                //        if (exit)
                //            break;
                //        else if (laptops) {
                //            intOption = laptopIndex;
                //        } else if (keyboards) {
                //            intOption = keyboardIndex;
                //        }

                //        Product product = Customer.browseInventory(products[intOption]);
                //        if (product != null) {
                //            customersCart[cartCounter] = product;
                //            cartCounter++;
                //        }

                //        // ((Customer) users[customerIndex][0]).addProduct(customersCart);
                //    } else if (login) {
                //        Member.login();
                //    } else if (register) {
                //        // register member
                //    }
                //} while (!choseExit);
                //choseExit = false;
            }

            if (choseAdmin) {
                Admin.flow(products, admin, adminFile);
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
