// MIT License
// Copyright (c) 2025 Shah

import javax.swing.*;

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
                System.out.printf("%-1s %s\n", record, inventory[record][field]/* .toString */);
            }
        }
    }

    /* for admins only */
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

    public static void editInventory() {
        System.out.println("1. Computers");
        System.out.println("2. Keyboards\n");
        System.out.println("Please choose inventory to edit: ");
    }
    /* for admins only */

    /* Input Processors */

    /* Input Processors */
}
