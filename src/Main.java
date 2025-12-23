// MIT License
// Copyright (c) 2025 Shah

import java.io.File;

public class Main {
    public static void main(String[] args) {
        int intOption;
        String strOption;
        boolean choseCustomer, choseAdmin, choseExit, adminIsInitialized, editComputers, editKeyboards;

        Admin admin = new Admin();
        File adminFile = new File("admin.txt");
        File computerFile = new File("computers.txt");
        File keyboardFile = new File("keyboards.txt");

        // The crux of the program
        do {
            intOption = Menu.main();

            // Checks intOption and determine what the user chose
            choseCustomer = (intOption == 0);
            choseAdmin = (intOption == 1);
            choseExit = (intOption == 2 || intOption == -1);

            if (choseCustomer) {
                // initialize Inventory
                // intOption = Menu.customer();
                // boolean isMember = (intOption == 1);
                // if (isMember) {
                // Menu.customerLogin();
                // }
            }

            if (choseAdmin) {
                adminIsInitialized = Menu.initializeAdmin(admin, adminFile);

                if (!adminIsInitialized) {
                    continue;
                }

                Menu.adminLogin(admin);
                strOption = Menu.editInventory();

                editComputers = (strOption.equalsIgnoreCase("Computers"));
                editKeyboards = (strOption.equalsIgnoreCase("Keyboards"));

                if (editComputers) {
                    // edit computer instances
                } else if (editKeyboards) {
                    // edit computer instances
                }
            }
        } while (!choseExit);

        System.exit(0);
    }
}
