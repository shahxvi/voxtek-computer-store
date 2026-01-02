// MIT License
// Copyright (c) 2025 Shah
// Copyright (c) 2025 Marzell

import java.util.*;
import javax.swing.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        int intOption;
        String strOption;
        boolean choseCustomer, choseExit, choseAdmin;

        final int laptopIndex = 0;
        final int keyboardIndex = 1;
        final int adminIndex = 2;

        Admin admin = new Admin();

        File[] file = {
                new File("laptops.txt"),
                new File("keyboards.txt"),
                new File("admin.txt")
        };

        Product[][] product = new Product[2][];
        product = new Laptop[laptopIndex][50];
        product = new Keyboard[keyboardIndex][50];
        initializeInventory(product, file);

        // The crux of the program
        do {
            intOption = menu();

            // Checks intOption and determine what the user chose
            choseCustomer = (intOption == 0);
            choseAdmin = (intOption == 1);
            choseExit = (intOption == 2 || intOption == -1);

            if (choseCustomer) {
                // initialize Inventory
                intOption = customerMenu();
                if (intOption == 0) {
                    // browse menu
                } else if (intOption == 1) {
                    // customer login menu
                    customerLogin();
                } else if (intOption == 2) {
                    // register member
                }
            }

            if (choseAdmin) {
                if (!initializeAdmin(admin, file[adminIndex]))
                    continue;

                if (!adminLogin(admin))
                    continue;

                strOption = chooseInventoryToEdit();

                if (strOption == null) {
                    continue; // Admin cancels edit inventory
                }

                if (strOption.equalsIgnoreCase("Laptops")) {
                    do {
                        intOption = chooseAddOrRemoveProduct(product[laptopIndex]);
                        if (intOption == 0) {
                            // laptops = addItem(laptops);
                        } else if (intOption == 1) {
                            product[laptopIndex] = (Laptop[]) removeItem(product[laptopIndex]);
                        }
                    } while (intOption != 2 && intOption != -1);
                } else if (strOption.equalsIgnoreCase("Keyboards")) {
                    do {
                        intOption = chooseAddOrRemoveProduct(product[keyboardIndex]);
                        if (intOption == 0) {
                            // keyboards = addItem(keyboards);
                        } else if (intOption == 1) {
                            product[keyboardIndex] = (Keyboard[]) removeItem(product[keyboardIndex]);
                        }
                    } while (intOption != 2 && intOption != -1);
                }
            }
        } while (!choseExit);

        System.exit(0);
    }

    // TODO: COMPLETE THIS METHOD
    public static void initializeInventory(Product[][] product, File[] file) {
        for (int i = 0; i < product.length; i++) {
            for (int j = 0; j < getUsableArraySize(product[i]); j++) {
            }
        }
    }

    public static String chooseInventoryToEdit() {
        String[] options = { "Laptops", "Keyboards" };
        String chosenOption = (String) JOptionPane.showInputDialog(null, "Please choose an inventory to edit",
                "Edit Inventory", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        return chosenOption;
    }

    /*
     * editInventory() is for admins only and it shows 2 options letting the admin
     * choose either add item or remove item and calls its respective method
     */
    public static int chooseAddOrRemoveProduct(Product[] products) {
        boolean choseExit = false, choseAddItem = false, choseRemoveItem = false;
        Object[] options = { "Add Inventory", "Remove Inventory", "Back" };

        int chosenOption = JOptionPane.showOptionDialog(null, "Please choose your action", "Edit Inventory",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        return chosenOption;
    }

    public static void addItem(Product[] products) {
    }

    public static Product[] removeItem(Product[] product) {
        // Get Usable size
        int usableSize = getUsableArraySize(product);

        Object[] obj = new Object[usableSize];
        for (int i = 0; i < usableSize; i++) {
            obj[i] = product[i].toRecord();
        }
        Object chosenOption = JOptionPane.showInputDialog(null, "Which item would you like to remove?", "Remove Item",
                JOptionPane.QUESTION_MESSAGE, null, obj, obj[0]);

        // Get the index of the chosen option
        int removedItem = 0;
        for (int i = 0; i < usableSize; i++) {
            if (obj[i].equals(chosenOption)) {
                removedItem = i;
                break; // Exit loop when match is found
            }
        }

        product[removedItem] = null;

        Product[] newProduct = null;
        if (product instanceof Laptop[]) {
            newProduct = new Laptop[usableSize - 1];
            int index = 0;
            for (int i = 0; i < newProduct.length; i++) {
                if (product[i] != null) {
                    newProduct[index++] = product[i];
                }
            }
        } else if (product instanceof Keyboard[]) {
            newProduct = new Keyboard[usableSize - 1];
            int index = 0;
            for (int i = 0; i < newProduct.length; i++) {
                if (product[i] != null) {
                    newProduct[index++] = product[i];
                }
            }
        }

        return newProduct;
    }

    /*
     * Method that returns the number of records in a file
     */
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

    public static int customerMenu() {
        String str;
        Object[] options = { "Browse", "Login", "Register", "Back" };
        str = "Please enter your option";

        int chosenOption = JOptionPane.showOptionDialog(null, str, "Customer Menu", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        return chosenOption;
    }

    public static void productList(Product[] products) {
    }

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
     * Creates the admin file if it isn't already
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

        try {
            PrintWriter adminFile = new PrintWriter(file);
            JOptionPane.showMessageDialog(null, "Admin file not found.");
            int intOption = JOptionPane.showConfirmDialog(null, "Would you like to create the admin file?");

            choseExit = (intOption == -1 || intOption == 1 || intOption == 2);

            if (choseExit) {
                return false;
            }

            for (int i = 0; i < messages.length; i++) {
                strInput = JOptionPane.showInputDialog(messages[i]);

                if (strInput == null) {
                    return false; // Admin cancels, file stays empty because we never closed it
                }

                // Makes sure that the user input of ID AND phone number is int only
                if (i == 1 || i == 2) {
                    boolean valid = false;

                    do {
                        valid = integersOnly(strInput);

                        if (valid) {
                            break;
                        }
                        if (i == 1) {
                            JOptionPane.showMessageDialog(null, "Phone number must be digits only.",
                                    "Phone Number Error", JOptionPane.WARNING_MESSAGE);
                            strInput = JOptionPane.showInputDialog(messages[1]);
                        }
                        if (i == 2) {
                            JOptionPane.showMessageDialog(null, "ID must be digits only.", "ID Error",
                                    JOptionPane.WARNING_MESSAGE);
                            strInput = JOptionPane.showInputDialog(messages[2]);
                        }

                    } while (!valid);
                }

                // add delimeter after every input except the last one
                if (i < messages.length - 1) {
                    adminFile.print(strInput.trim() + ";");
                } else {
                    adminFile.print(strInput.trim());
                }
            }
            adminFile.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
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

    public static void customerLogin() {
        String name = JOptionPane.showInputDialog("Enter your name: ");
        String strPhone = JOptionPane.showInputDialog("Enter your phone number: ");
        while (!integersOnly(strPhone)) {
            JOptionPane.showMessageDialog(null, "Please enter integer only.");
            strPhone = JOptionPane.showInputDialog("Enter your phone number: ");
        }
        int phoneNum = Integer.parseInt(strPhone);
    }

    public static boolean integersOnly(String strInput) {
        boolean valid = false;
        for (int j = 0; j < strInput.length(); j++) {
            if (!Character.isDigit(strInput.charAt(j))) {
                valid = false;
                break;
            } else {
                valid = true;
            }
        }
        return valid;
    }

    public static int getUsableArraySize(Product[] product) {
        int usableSize = 0;
        for (int i = 0; i < product.length; i++) {
            if (product[i] != null)
                usableSize++;
        }
        return usableSize;
    }
}
