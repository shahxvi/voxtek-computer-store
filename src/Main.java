// MIT License
// Copyright (c) 2025 Shah
// Copyright (c) 2025 Marzell

import users.*;
import users.admins.*;
import users.customers.*;
import products.Inventory;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        File adminFile = new File("admin.txt");

        Inventory inventory = new Inventory(50, 50, new File("laptops.txt"), new File("keyboards.txt"));

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
                CustomerUI.run(inventory);
            } else if (choseAdmin) {
                // AdminUI.run(inventory, admin, adminFile);
            }
        } while (!choseExit);

        inventory.writeToFile();

        System.exit(0);
    }
}
