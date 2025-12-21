// MIT License
// Copyright (c) 2025 Shah

import java.util.Scanner;
import javax.swing.*;

public class Menu {
    public static int main() {
        String str;
        Object[] options = { "Customer", "Admin", "Cancel" };
        str = "Welcome to VoxTek Computer Store\n";
        str += "Please choose your option: ";

        int chosenOption = JOptionPane.showOptionDialog(null, str, "VoxTek Computer Store", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, options, options[2]);
        return chosenOption;
    }

    public static void computerList(String[][] inventory) {
        for (int record = 0; record < inventory.length; record++) {
            for (int field = 0; field < inventory[record].length; field++) {
                System.out.printf("%-1s %s\n", record, inventory[record][field]/* .toString */);
            }
        }
    }

    // for admins only
    public static void adminLogin(Admin admin) {
        Scanner keyboardInput = new Scanner(System.in);
        String id, password;

        id = JOptionPane.showInputDialog(null, "Please enter your ID");
        System.out.println(id);

        keyboardInput.close();
    }

    // for admins only
    public static void editInventory() {
        System.out.println("1. Computers");
        System.out.println("2. Keyboards\n");
        System.out.println("Please choose inventory to edit: ");
    }

    public static void clearScreen() {
        try {
            String osName = System.getProperty("os.name");
            if (osName.startsWith("Windows"))
                new ProcessBuilder("cls").inheritIO().start().waitFor();
            else
                new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
