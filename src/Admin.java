// MIT License
// Copyright (c) 2025 Shah

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class Admin extends User implements InputProcessor {
    private int id;
    private String password;

    /* Contructors */
    public Admin() {
        super();
        id = 0;
        password = "";
    }

    public Admin(String name, int phoneNumber, int id, String password) {
        super(name, phoneNumber);
        this.id = id;
        this.password = password;
    }

    public Admin(Admin other) {
        super(other);
        this.id = other.id;
        this.password = other.password;
    }
    /* Contructors */

    /* Setters */
    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    /* Setters */

    /* Getters */
    public int getId() {
        return id;
    }
    /* Getters */

    public boolean verifyID(int inputID) {
        if (id == inputID) {
            return true;
        }
        return false;
    }

    public boolean verifyPassword(String inputPassword) {
        if (password.equalsIgnoreCase(inputPassword)) {
            return true;
        }
        return false;
    }

    public static boolean login(Admin admin) {
        String strInput;
        int id;
        String password;
        boolean validID, validPassword;

        do {
            strInput = JOptionPane.showInputDialog("Please enter your ID");

            // For when the user presses okay without any input
            while (strInput == null || strInput.isBlank() || strInput.isEmpty()) {
                // Exits if the user cancels
                if (strInput == null)
                    return false; // Go back to main
                strInput = JOptionPane.showInputDialog("Please enter your ID");
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
                        valid = InputProcessor.isInteger(strInput);

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

    // TODO: Handle when the user cancels or presses OK withhout input
    public static Product[] addProduct(Product[] products) {
        String brand = JOptionPane.showInputDialog("Please enter brand");
        String model = JOptionPane.showInputDialog("Please enter model");
        String priceStr = JOptionPane.showInputDialog("Please enter price");

        while (!InputProcessor.isInteger(priceStr)) {
            JOptionPane.showMessageDialog(null, "Please enter digits only for price");
            priceStr = JOptionPane.showInputDialog("Please enter price");
        }
        int price = Integer.parseInt(priceStr);

        if (products instanceof Laptop[]) {
            String cpu = JOptionPane.showInputDialog("Please enter cpu");
            String memoryGBStr = JOptionPane.showInputDialog("Please enter memory size (GB)");

            while (!InputProcessor.isInteger(memoryGBStr)) {
                JOptionPane.showMessageDialog(null, "Please enter digits only for memory size");
                memoryGBStr = JOptionPane.showInputDialog("Please enter memory size (GB)");
            }
            int memoryGB = Integer.parseInt(memoryGBStr);

            String storageGBStr = JOptionPane.showInputDialog("Please enter storage size (GB)");

            while (!InputProcessor.isInteger(storageGBStr)) {
                JOptionPane.showMessageDialog(null, "Please enter digits only for storage size");
                storageGBStr = JOptionPane.showInputDialog("Please enter storage size (GB)");
            }
            int storageGB = Integer.parseInt(storageGBStr);

            String storageType = JOptionPane.showInputDialog("Please enter storage type");
            products[InputProcessor.getUsableArraySize(products)] = new Laptop(brand, model, price, cpu, memoryGB,
                    storageGB,
                    storageType);
        } else if (products instanceof Keyboard[]) {
        }

        return products;
    }

    public static Product[] removeProduct(Product[] products) {
        // Get Usable size
        int usableSize = InputProcessor.getUsableArraySize(products);

        Object[] obj = new Object[usableSize];
        for (int i = 0; i < usableSize; i++) {
            obj[i] = products[i].toRecord();
        }
        String chosenProduct = (String) JOptionPane.showInputDialog(null, "Which item would you like to remove?",
                "Remove Item", JOptionPane.QUESTION_MESSAGE, null, obj, obj[0]);

        if (chosenProduct == null)
            return products;

        // Get the index of the chosen option
        int removedItem = -1;
        for (int i = 0; i < usableSize; i++) {
            if (chosenProduct.equalsIgnoreCase(products[i].toRecord())) {
                removedItem = i;
                break; // Exit loop when match is found
            }
        }

        products[removedItem] = null;

        return InputProcessor.reorganizeInventory(products);
    }
}
