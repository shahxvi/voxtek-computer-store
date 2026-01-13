// MIT License
// Copyright (c) 2025 Shah
// Copyright (c) 2025 Marzell

import users.UserUI;
import users.admins.AdminUI;
import users.customers.CustomerUI;
import products.Inventory;

public class Main {
    public static void main(String[] args) {
        int intOption;
        boolean choseCustomer, choseExit, choseAdmin;

        Inventory inventory = new Inventory(50, 50, "laptops.txt", "keyboards.txt");

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
                AdminUI.run(inventory);
            }
        } while (!choseExit);

        inventory.writeToFile();

        System.exit(0);
    }
}
