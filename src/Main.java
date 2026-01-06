// MIT License
// Copyright (c) 2025 Shah
// Copyright (c) 2025 Marzell

import java.io.*;
import javax.swing.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main implements InputProcessor {
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
        initializeInventory(products, inventoryFiles);

        File adminFile = new File("admin.txt");
        File membersFile = new File("members.txt");

        Admin admin = new Admin();

        // Get the inventory size of all products and make a cart array
        int totalProducts = InputProcessor.getInventorySize(inventoryFiles[laptopIndex])
                + InputProcessor.getInventorySize(inventoryFiles[laptopIndex]);
        Product[] customersCart = new Product[totalProducts];
        int cartCounter = 0;

        int intOption;
        String strOption;
        boolean choseCustomer, choseExit, choseAdmin, browse, login, register;

        // The crux of the program
        do {
            intOption = menu();

            // Checks intOption and determine what the user chose
            choseCustomer = (intOption == 0);
            choseAdmin = (intOption == 1);
            choseExit = (intOption == 2 || intOption == -1);

            if (choseCustomer) {
                do {
                    intOption = Customer.menu();

                    browse = (intOption == 0);
                    login = (intOption == 1);
                    register = (intOption == 2);
                    choseExit = (intOption == 3 || intOption == -1);

                    if (browse) {
                        strOption = chooseInventory();

                        boolean exit = (strOption == null);
                        boolean laptops = (strOption.equals("Laptops"));
                        boolean keyboards = (strOption.equals("Keyboards"));

                        if (exit)
                            break;
                        else if (laptops) {
                            intOption = laptopIndex;
                        } else if (keyboards) {
                            intOption = keyboardIndex;
                        }

                        Product product = Customer.browseInventory(products[intOption]);
                        if (product != null) {
                            customersCart[cartCounter] = product;
                            cartCounter++;
                        }

                        // ((Customer) users[customerIndex][0]).addProduct(customersCart);
                    } else if (login) {
                        Member.login();
                    } else if (register) {
                        // register member
                    }
                } while (!choseExit);
                choseExit = false;
            }

            if (choseAdmin) {
                if (!Admin.initializeAdmin(admin, adminFile))
                    continue;

                if (!Admin.login(admin))
                    continue;

                do {
                    strOption = chooseInventory();

                    if (strOption == null)
                        break;

                    int chosenProduct = -1;
                    if (strOption.equalsIgnoreCase("Laptops"))
                        chosenProduct = laptopIndex;
                    else if (strOption.equalsIgnoreCase("Keyboards"))
                        chosenProduct = keyboardIndex;

                    intOption = Admin.chooseAddOrRemoveProduct(products[chosenProduct]);
                    choseExit = (intOption == 2 && intOption == -1);

                    if (intOption == 0)
                        products[chosenProduct] = Admin.addProduct(products[chosenProduct]);
                    else if (intOption == 1)
                        products[chosenProduct] = Admin.removeProduct(products[chosenProduct]);
                } while (!choseExit);
            }
        } while (!choseExit);

        writeToFile(products[laptopIndex], inventoryFiles[laptopIndex]);
        writeToFile(products[keyboardIndex], inventoryFiles[keyboardIndex]);

        System.exit(0);
    }

    /*
     * menu() - Gives user options to login as customer, admin or exit from the
     * program
     */
    public static int menu() {
        String str;
        Object[] options = { "Customer", "Admin", "Exit" };
        str = "Welcome to VoxTek Computer Store\n";
        str += "Please choose your option: ";

        int chosenOption = JOptionPane.showOptionDialog(null, str, "VoxTek", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        return chosenOption;
    }

    /*
     * chooseInventory() is a menu to choose what inventory they want to edit
     */
    public static String chooseInventory() {
        String[] options = { "Laptops", "Keyboards" };
        String chosenOption = (String) JOptionPane.showInputDialog(null, "Please choose an inventory",
                "Choose Inventory", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        return chosenOption;
    }

    /********** Helper Methods **********/
    public static void initializeInventory(Product[][] product, File[] file) {
        for (int i = 0; i < product.length; i++) {
            for (int j = 0; j < InputProcessor.getInventorySize(file[i]); j++) {
                if (product[i] instanceof Laptop[]) {
                    product[i][j] = new Laptop();
                } else if (product[i] instanceof Keyboard[]) {
                    product[i][j] = new Keyboard();
                }
                product[i][j].loadInventory(file[i], j);
            }
        }
    }

    public static void writeToFile(Product[] product, File file) {
        try {
            PrintWriter outputFile = new PrintWriter(file);
            for (int i = 0; i < InputProcessor.getUsableArraySize(product); i++) {
                outputFile.println(product[i].toRecord());
            }
            outputFile.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    /********** Helper Methods **********/
}
