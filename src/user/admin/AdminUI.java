// MIT License
// Copyright (c) 2025 Shah

package user.admin;

import input.Processor;
import product.*;

import javax.swing.JOptionPane;

class AdminUI implements Processor {
    /*
     * chooseAddOrRemoveProduct() is for admins only and it shows 2 options letting
     * the admin choose either add item or remove item and returns the option chosen
     */
    public static int chooseAddOrRemoveProduct(Product[] products) {
        Object[] options = { "Add Inventory", "Remove Inventory", "Back" };

        int chosenOption = JOptionPane.showOptionDialog(null, "Please choose your action", "Edit Inventory",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        return chosenOption;
    }

    public static boolean login(Admin admin) {
        String strInput = "";
        String password = "";
        int id = 0;

        do {
            while (strInput == null || strInput.isBlank() || strInput.isEmpty()) {
                if (strInput == null)
                    return false;
                strInput = JOptionPane.showInputDialog("Please enter your ID");
                if (!Processor.isInteger(strInput)) {
                    JOptionPane.showMessageDialog(null, "ID must be integers only");
                }
            }
            id = Integer.parseInt(strInput);

            while (password == null || password.isBlank() || password.isEmpty()) {
                if (password == null)
                    return false;
                password = JOptionPane.showInputDialog("Please enter your password");
            }

            if (!admin.verifyCredentials(id, password)) {
                JOptionPane.showMessageDialog(null, "Incorrect ID or Password");
            }
        } while (!admin.verifyCredentials(id, password));

        return true;
    }

    public static Admin createAdmin() {
        String[] prompts = {
                "Enter admin's name",
                "Enter admin's phone number",
                "Enter admin's ID",
                "Enter admin's password"
        };
        boolean choseExit = false;

        JOptionPane.showMessageDialog(null, "Admin file not found.");
        int intOption = JOptionPane.showConfirmDialog(null, "Would you like to create the admin file?");

        choseExit = (intOption == -1 || intOption == 1 || intOption == 2);

        if (choseExit) {
            return null;
        }

        String name = "", password = "";
        int phoneNumber = -1, id = -1;

        String strInput = "";
        for (int i = 0; i < prompts.length; i++) {
            strInput = JOptionPane.showInputDialog(prompts[i]);

            if (strInput == null) {
                return null; // Admin cancels, file stays empty because we never closed it
            }

            if (i == 0) {
                name = strInput;
            }

            // Makes sure that the user input of ID AND phone number is int only
            if (i == 1 || i == 2) {
                do {
                    if (i == 1) {
                        JOptionPane.showMessageDialog(null, "Phone number must be digits only.", "Phone Number Error", JOptionPane.WARNING_MESSAGE);
                        strInput = JOptionPane.showInputDialog(prompts[1]);
                    }
                    else if (i == 2) {
                        JOptionPane.showMessageDialog(null, "ID must be digits only.", "ID Error", JOptionPane.WARNING_MESSAGE);
                        strInput = JOptionPane.showInputDialog(prompts[2]);
                    }
                } while (!Processor.isInteger(strInput));
                if (i == 1)
                    phoneNumber = Integer.parseInt(strInput);
                else if (i == 2)
                    id = Integer.parseInt(strInput);
            }

            if (i == 3) {
                password = strInput;
            }
        }

        return new Admin(name, phoneNumber, id, password);
    }

    // TODO: Handle when the user cancels or presses OK withhout input
    static Laptop createLaptop() {
        String brand = JOptionPane.showInputDialog("Please enter brand");
        String model = JOptionPane.showInputDialog("Please enter model");
        String priceStr = JOptionPane.showInputDialog("Please enter price");

        while (!Processor.isInteger(priceStr)) {
            JOptionPane.showMessageDialog(null, "Please enter digits only for price");
            priceStr = JOptionPane.showInputDialog("Please enter price");
        }
        int price = Integer.parseInt(priceStr);

        String cpu = JOptionPane.showInputDialog("Please enter cpu");
        String memoryGBStr = JOptionPane.showInputDialog("Please enter memory size (GB)");

        while (!Processor.isInteger(memoryGBStr)) {
            JOptionPane.showMessageDialog(null, "Please enter digits only for memory size");
            memoryGBStr = JOptionPane.showInputDialog("Please enter memory size (GB)");
        }
        int memoryGB = Integer.parseInt(memoryGBStr);

        String storageGBStr = JOptionPane.showInputDialog("Please enter storage size (GB)");

        while (!Processor.isInteger(storageGBStr)) {
            JOptionPane.showMessageDialog(null, "Please enter digits only for storage size");
            storageGBStr = JOptionPane.showInputDialog("Please enter storage size (GB)");
        }
        int storageGB = Integer.parseInt(storageGBStr);

        String storageType = JOptionPane.showInputDialog("Please enter storage type");
        return new Laptop(brand, model, price, cpu, memoryGB, storageGB, storageType);
    }

    static Keyboard createKeyboard() {
        return new Keyboard(String model, String brand, double price, String switchType, boolean isWireless);
    }
}
