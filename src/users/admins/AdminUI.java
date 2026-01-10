// MIT License
// Copyright (c) 2025 Shah

package users.admins;

import processors.Processor;
import products.*;
import users.UserUI;

import java.io.*;
import javax.swing.JOptionPane;

public class AdminUI implements Processor {

    public static void run(Product[][] products, Admin admin, File file) {
        String strOption;
        int intOption;
        boolean choseExit = false;

        if (!AdminFileHandler.initializeAdmin(admin, file))
            return;

        if (!login(admin))
            return;

        do {
            strOption = UserUI.chooseInventory();

            if (strOption == null)
                break;

            intOption = chooseAddOrRemoveProduct();

            choseExit = (intOption == 2 && intOption == -1);

            int chosenProduct = -1;
            if (strOption.equalsIgnoreCase("Laptops"))
                chosenProduct = 0;
            else if (strOption.equalsIgnoreCase("Keyboards"))
                chosenProduct = 1;

            if (intOption == 0) {
                products[chosenProduct] = AdminFileHandler.addLaptop(products[chosenProduct]);
            } else if (intOption == 1) {
                products[chosenProduct] = AdminFileHandler.removeProduct(products[chosenProduct]);
            }
        } while (!choseExit);
    }


    /*
     * chooseAddOrRemoveProduct() is for admins only and it shows 2 options letting
     * the admin choose either add item or remove item and returns the option chosen
     */
    static int chooseAddOrRemoveProduct() {
        Object[] options = { "Add Inventory", "Remove Inventory", "Back" };

        int chosenOption = JOptionPane.showOptionDialog(null, "Please choose your action", "Edit Inventory",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        return chosenOption;
    }

    static boolean login(Admin admin) {
        String strInput = "";
        String password = "";
        int id = 0;

        do {
            strInput = JOptionPane.showInputDialog("Please enter your ID");
            if (strInput == null) {
                return false;
            }

            if (!Processor.isInteger(strInput)) {
                JOptionPane.showMessageDialog(null, "ID must be integers only");
                continue;
            }

            id = Integer.parseInt(strInput);

            password = JOptionPane.showInputDialog("Please enter your password");
            if (password == null) {
                return false;
            }

            if (!admin.verifyCredentials(id, password)) {
                JOptionPane.showMessageDialog(null, "Incorrect ID or Password");
            }
        } while (!admin.verifyCredentials(id, password));

        return true;
    }

    static Admin createAdmin() {
        boolean choseExit = false;

        JOptionPane.showMessageDialog(null, "Admin file not found.");
        int intOption = JOptionPane.showConfirmDialog(null, "Would you like to create the admin file?");

        choseExit = (intOption == -1 || intOption == 1 || intOption == 2);

        if (choseExit) {
            return null;
        }

        String name = null, password = "";
        int phoneNumber = -1, id = -1;

        String strInput = null; 

        name = JOptionPane.showInputDialog("Please enter admin's name ");
        if (name == null) {
            return null;
        }

        strInput = JOptionPane.showInputDialog("Please enter admin's phone number");
        while (!Processor.isInteger(strInput)) {
            if (strInput == null) {
                return null;
            }
            JOptionPane.showMessageDialog(null, "Phone number must be integers only!");
            strInput = JOptionPane.showInputDialog("Please enter admin's phone number");
        }
        phoneNumber = Integer.parseInt(strInput);

        strInput = JOptionPane.showInputDialog("Please enter admin's ID");
        while (!Processor.isInteger(strInput)) {
            if (strInput == null) {
                return null;
            }
            JOptionPane.showMessageDialog(null, "ID must be integers only!");
            strInput = JOptionPane.showInputDialog("Please enter admin's ID");
        }
        id = Integer.parseInt(strInput);

        password = JOptionPane.showInputDialog("Please enter admin's password");
        if (password == null) {
            return null;
        }

        return new Admin(name, phoneNumber, id, password);
    }

    static Laptop createLaptop() {
        String brand = JOptionPane.showInputDialog("Please enter brand");
        if (brand == null) {
            return null;
        }

        String model = JOptionPane.showInputDialog("Please enter model");
        if (model == null) {
            return null;
        }

        String priceStr = JOptionPane.showInputDialog("Please enter price");
        if (priceStr == null) {
            return null;
        }

        while (!Processor.isInteger(priceStr)) {
            JOptionPane.showMessageDialog(null, "Please enter digits only for price");
            priceStr = JOptionPane.showInputDialog("Please enter price");
            if (priceStr == null) {
                return null;
            }
        }
        int price = Integer.parseInt(priceStr);

        String cpu = JOptionPane.showInputDialog("Please enter cpu");
        if (cpu == null) {
            return null;
        }
        String memoryGBStr = JOptionPane.showInputDialog("Please enter memory size (GB)");
        if (memoryGBStr == null) {
            return null;
        }

        while (!Processor.isInteger(memoryGBStr)) {
            JOptionPane.showMessageDialog(null, "Please enter digits only for memory size");
            memoryGBStr = JOptionPane.showInputDialog("Please enter memory size (GB)");
            if (memoryGBStr == null) {
                return null;
            }
        }
        int memoryGB = Integer.parseInt(memoryGBStr);

        String storageGBStr = JOptionPane.showInputDialog("Please enter storage size (GB)");
        if (storageGBStr == null) {
            return null;
        }

        while (!Processor.isInteger(storageGBStr)) {
            JOptionPane.showMessageDialog(null, "Please enter digits only for storage size");
            storageGBStr = JOptionPane.showInputDialog("Please enter storage size (GB)");
            if (storageGBStr == null) {
                return null;
            }
        }
        int storageGB = Integer.parseInt(storageGBStr);

        String storageType = JOptionPane.showInputDialog("Please enter storage type");
        if (storageType == null) {
            return null;
        }
        return new Laptop(brand, model, price, cpu, memoryGB, storageGB, storageType);
    }

    static Keyboard createKeyboard() {
        String brand = JOptionPane.showInputDialog("Please enter brand");
        if (brand == null) {
            return null;
        }
        String model = JOptionPane.showInputDialog("Please enter model");
        if (model == null) {
            return null;
        }
        String priceStr = JOptionPane.showInputDialog("Please enter price");
        if (priceStr == null) {
            return null;
        }

        while (!Processor.isInteger(priceStr)) {
            JOptionPane.showMessageDialog(null, "Please enter digitls only for price");
            priceStr = JOptionPane.showInputDialog("Please enter price");
            if (priceStr == null) {
                return null;
            }
        }
        int price = Integer.parseInt(priceStr);

        String switchType = JOptionPane.showInputDialog("Please enter switch type");
        if (switchType == null) {
            return null;
        }

        boolean isWireless = JOptionPane.showConfirmDialog(null, "Is the keyboard wireless?",
                "Yes/No", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

        return new Keyboard(brand, model, price, switchType, isWireless);
    }

    static String chooseProductToRemoveProduct(Product[] products) {
        // Get Usable size
        int usableSize = Processor.getUsableArraySize(products);

        Object[] obj = new Object[usableSize];
        for (int i = 0; i < usableSize; i++) {
            obj[i] = products[i].toRecord();
        }
        String chosenProduct = (String) JOptionPane.showInputDialog(null, "Which item would you like to remove?",
                "Remove Item", JOptionPane.QUESTION_MESSAGE, null, obj, obj[0]);
        return chosenProduct;
    }
}
