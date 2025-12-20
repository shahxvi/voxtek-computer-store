// MIT License
// Copyright (c) 2025 Shah

import java.util.Scanner;

public class Menu {
    public static void main() {
        System.out.println("██╗   ██╗ ██████╗ ██╗  ██╗████████╗███████╗██╗  ██╗");
        System.out.println("██║   ██║██╔═══██╗╚██╗██╔╝╚══██╔══╝██╔════╝██║ ██╔╝");
        System.out.println("██║   ██║██║   ██║ ╚███╔╝    ██║   █████╗  █████╔╝ ");
        System.out.println("╚██╗ ██╔╝██║   ██║ ██╔██╗    ██║   ██╔══╝  ██╔═██╗ ");
        System.out.println(" ╚████╔╝ ╚██████╔╝██╔╝ ██╗   ██║   ███████╗██║  ██╗");
        System.out.println("  ╚═══╝   ╚═════╝ ╚═╝  ╚═╝   ╚═╝   ╚══════╝╚═╝  ╚═╝");
        System.out.println("Welcome to VoxTek Computer Store\n");
        System.out.println("1. Customer");
        System.out.println("2. Admin\n");
        System.out.print("Please choose your option: ");
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

        System.out.println("Please enter your ID: ");
        id = keyboardInput.nextLine();
        System.out.println("Please enter your password: ");
        password = keyboardInput.nextLine();

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
