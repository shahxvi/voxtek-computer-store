// MIT License
// Copyright (c) 2025 Shah

import javax.swing.*;
import java.io.*;
import java.util.*;

public class Menu {
    public static int main() {
        String str;
        Object[] options = { "Customer", "Admin", "Exit" };
        str = "Welcome to VoxTek Computer Store\n";
        str += "Please choose your option: ";

        int chosenOption = JOptionPane.showOptionDialog(null, str, "VoxTek", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

        return chosenOption;
    }

    public static void computerList(String[][] inventory) {
        for (int record = 0; record < inventory.length; record++) {
            for (int field = 0; field < inventory[record].length; field++) {
                System.out.printf("%-1d %s\n", record, inventory[record][field]/* .toString */);
            }
        }
    }

    /* for admins only */

    /*
     * This method initializes an admin class by retrieving its attribute from file
     * it returns true after the admin class has been initialized
     */
    public static boolean initializeAdmin(Admin admin, File file) {
        boolean initialized = false, choseCreateAdminFile = false, choseExit = false;

        do {
            try {
                Scanner inputFile = new Scanner(file);

                // Checks if empty or not
                if (!inputFile.hasNextLine()) {
                    inputFile.close();
                    throw new FileNotFoundException(); // If the file is empty it is catched by FileNotFoundException
                }

                while (inputFile.hasNext()) {
                    StringTokenizer token = new StringTokenizer(inputFile.nextLine(), ";");
                    admin.setName(token.nextToken());
                    admin.setPhoneNumber(Integer.parseInt(token.nextToken()));
                    admin.setId(Integer.parseInt(token.nextToken()));
                    admin.setPassword(token.nextToken());
                }
                inputFile.close();
                initialized = true;

            } catch (FileNotFoundException e) {
                // initialized is still false here
                JOptionPane.showMessageDialog(null, "Admin file not found.");
                int intOption = JOptionPane.showConfirmDialog(null, "Would you like to create the admin file?");

                choseExit = (intOption == -1 || intOption == 1 || intOption == 2);
                choseCreateAdminFile = (intOption == 0);

                if (choseCreateAdminFile) {
                    initialized = createAdminFile(file); // initialized be false if the user cancels halfway
                    if (initialized) {
                        JOptionPane.showMessageDialog(null, "Admin file created.");
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!initialized && !choseExit);

        return initialized;
    }

    /*
     * Creates the admin file
     * returns false if the admin cancels halfway
     */
    public static boolean createAdminFile(File file) {
        try {
            String strInput;
            PrintWriter adminFile = new PrintWriter(file);

            String[] messages = {
                    "Enter admin's name",
                    "Enter admin's phone number",
                    "Enter admin's ID",
                    "Enter admin's password"
            };

            for (String message : messages) {
                strInput = JOptionPane.showInputDialog(message);
                if (strInput == null) {
                    // Admin cancels, file stays empty because we never closed it
                    return false;
                }
                adminFile.print(strInput.trim() + ";");
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

    public static void adminLogin(Admin admin) {
        String strInput;
        int id;
        String password;
        boolean validID, validPassword;

        do {
            strInput = JOptionPane.showInputDialog("Please enter your ID");

            // Exits if the user cancels
            if (strInput == null) {
                return; // Go back to main
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
    }

    /*
     * editInventory() is for admins only and it shows 2 options letting the admin
     * choose to edit either Computers or Keyboards
     * will return one of the following possibilities:
     * - Computers, Keyboards, and null
     */
    public static String editInventory() {
        Object[] options = { "Computers", "Keyboards" };
        String chosenOption = (String) JOptionPane.showInputDialog(null, "Please choose an inventory to edit",
                "Edit Inventory", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        return chosenOption;
    }

    public static void editInventory(File file) {
    }
    /* for admins only */

    /* Input Processors */

    /* Input Processors */
}
