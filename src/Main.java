// MIT License
// Copyright (c) 2025 Shah

import java.util.*;
import javax.swing.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        int intOption;
        String strOption;
        boolean choseCustomer, choseExit, isAdminConfigured, choseAdmin, adminLoggedIn;

        Admin admin = new Admin();
        File adminFile = new File("admin.txt");

        /* Initialize Computers */
        File computerFile = new File("computers.txt");
        int computerCount = getInventorySize(computerFile);
        Computer[] computers = new Computer[computerCount];
        for (int i = 0; i < computerCount; i++) {
            computers[i] = new Computer();
            computers[i].getInventory(computerFile, i);
        }

        /* Initialize Keyboards */
        File keyboardFile = new File("keyboards.txt");
        // int keyboardCount = getRecordSize(keyboardFile);
        // Keyboard[] keyboards = new Keyboard[keyboardCount];

        // The crux of the program
        do {
            intOption = mainMenu();

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
                if (!initializeAdmin(admin, adminFile))
                    continue;

                if (!adminLogin(admin))
                    continue;

                strOption = chooseInventoryToEdit();

                if (strOption == null) {
                    continue; // Admin cancels edit inventory
                } else if (strOption.equalsIgnoreCase("Computers")) {
                    editInventory(computers);
                } else if (strOption.equalsIgnoreCase("Keyboards")) {
                    // editInventory(keyboardFile);
                }
            }
        } while (!choseExit);

        for (Computer c : computers) {
            c.updateInventory(computerFile);
        }

        System.exit(0);
    }

    /* Methods */
    public static int mainMenu() {
        String str;
        Object[] options = { "Customer", "Admin", "Exit" };
        str = "Welcome to VoxTek Computer Store\n";
        str += "Please choose your option: ";

        int chosenOption = JOptionPane.showOptionDialog(null, str, "VoxTek", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        return chosenOption;
    }

    /* TODO: Make this polymorphic (using Product) */
    public static void computerList(String[][] inventory) {
        for (int record = 0; record < inventory.length; record++) {
            for (int field = 0; field < inventory[record].length; field++) {
                System.out.printf("%-1d %s\n", record, inventory[record][field].toString()); // Log
            }
        }
    }

    /* for admins only */

    /*
     * initializes the admin (loads admin from file to object)
     * if the file is missing or empty, it calls createAdminFile
     */
    public static boolean initializeAdmin(Admin admin, File file) {
        boolean isInitialized = false;

        while (!isInitialized) {
            try {
                loadAdmin(admin, file);
                isInitialized = true;
            } catch (FileNotFoundException e) {
                boolean isCreated = createAdminFile(admin, file);
                if (!isCreated) {
                    return false; // User chose to exit
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

        return isInitialized;
    }

    /*
     * Loads admin from file to object (throws an exception)
     */
    public static boolean loadAdmin(Admin admin, File file) throws FileNotFoundException {
        try (Scanner inputFile = new Scanner(file)) {
            // Checks if empty or not
            if (!inputFile.hasNextLine()) {
                inputFile.close();
                throw new FileNotFoundException();
            }

            while (inputFile.hasNext()) {
                StringTokenizer token = new StringTokenizer(inputFile.nextLine(), ";");
                admin.setName(token.nextToken());
                admin.setPhoneNumber(Integer.parseInt(token.nextToken()));
                admin.setId(Integer.parseInt(token.nextToken()));
                admin.setPassword(token.nextToken());
            }
        }
        return true;
    }

    /*
     * Creates the admin fild if it isn't already
     */
    public static boolean createAdminFile(Admin admin, File file) {
        String[] messages = {
                "Enter admin's name",
                "Enter admin's phone number",
                "Enter admin's ID",
                "Enter admin's password"
        };
        boolean choseExit = false;
        String strInput = null;

        try (PrintWriter adminFile = new PrintWriter(file)) {
            JOptionPane.showMessageDialog(null, "Admin file not found.");
            int intOption = JOptionPane.showConfirmDialog(null, "Would you like to create the admin file?");

            choseExit = (intOption == -1 || intOption == 1 || intOption == 2);

            if (choseExit) {
                return false;
            }

            for (int i = 0; i < messages.length; i++) {
                strInput = JOptionPane.showInputDialog(messages[i]);
                if (strInput == null) {
                    // Admin cancels, file stays empty because we never closed it
                    return false;
                }

                // add delimeter after every input except the last one
                if (i < messages.length - 1) {
                    adminFile.print(strInput.trim() + ";");
                } else {
                    adminFile.print(strInput.trim());
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return true;
    }

    public static boolean adminLogin(Admin admin) {
        String strInput;
        int id;
        String password;
        boolean validID, validPassword;

        do {
            strInput = JOptionPane.showInputDialog("Please enter your ID");

            // Exits if the user cancels
            if (strInput == null) {
                return false; // Go back to main
            }

            id = Integer.parseInt(strInput);
            password = JOptionPane.showInputDialog("Please enter your password");

            // Check if ID or Password Matches
            validID = admin.verifyID(id);
            validPassword = admin.verifyPassword(password);

            if (!validID || !validPassword) {
                JOptionPane.showMessageDialog(null, "Incorrect ID or Password");
            }
        } while (!validID || !validPassword);

        return true;
    }

    /*
     * editInventory() is for admins only and it shows 2 options letting the admin
     * choose to edit either Computers or Keyboards
     * will return one of the following possibilities:
     * - Computers, Keyboards, and null
     */
    public static String chooseInventoryToEdit() {
        String[] options = { "Computers", "Keyboards" };
        String chosenOption = (String) JOptionPane.showInputDialog(null, "Please choose an inventory to edit",
                "Edit Inventory", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        return chosenOption;
    }

    public static int editInventory(Product[] products) {
        Object[] options = { "Add Inventory", "Remove Inventory", "Back" };

        int chosenOption = JOptionPane.showOptionDialog(null, "Please choose your action", "Edit Inventory",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        return chosenOption;
        // if add item then call addItem() and so on
    }

    public static void addItem(Product[] products) {
    }

    public static void removeItem(Product[] products) {
        Object[] obj = new Object[products.length];
        for (int record = 0; record < products.length; record++) {
            obj[record] = products[record].toString();
        }
        Object chosenOption = JOptionPane.showInputDialog(null, "Which item would you like to remove?", "Remove Item",
                JOptionPane.QUESTION_MESSAGE, null, obj, obj[0]);

        // Get the index of the chosen option
        int intChosenOption = 0;
        for (int i = 0; i < products.length; i++) {
            if (obj[i].equals(chosenOption)) {
                intChosenOption = i;
                break; // Exit loop when match is found
            }
        }
        System.out.println("intChosenOption =  " + intChosenOption); // Log
    }
    /* for admins only */

    public static int getInventorySize(File file) {
        int recordSize = 0;
        try (Scanner inputFile = new Scanner(file)) {
            while (inputFile.hasNext()) {
                inputFile.nextLine();
                recordSize++;
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return recordSize;
    }

}
