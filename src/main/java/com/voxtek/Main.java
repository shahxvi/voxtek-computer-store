// MIT License
// Copyright (c) 2025 Shah
// Copyright (c) 2025 Marzell

package com.voxtek;

import com.voxtek.user.UserUI;
import com.voxtek.user.admin.AdminUI;
import com.voxtek.user.customer.CustomerUI;
import com.voxtek.product.Inventory;

import java.io.File;

class Main {
    static void main(String[] args) {
        int intOption;
        boolean choseCustomer, choseExit, choseAdmin;

        Inventory inventory = new Inventory(50, 50, new File("laptops.txt"), new File("keyboards.txt"));

        // The crux of the program
        do {
            intOption = UserUI.mainMenu();

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
