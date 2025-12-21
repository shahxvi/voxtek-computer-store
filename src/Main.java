// MIT License
// Copyright (c) 2025 Shah

import java.io.*;
import javax.swing.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        int intOption;
        boolean choseCustomer, choseAdmin, choseExit, adminIsInitialized;

        do {
            intOption = Menu.main();

            // Checks intOption and determine what the user chose
            choseCustomer = (intOption == 0);
            choseAdmin = (intOption == 1);
            choseExit = (intOption == 2 || intOption == -1);

            if (choseCustomer) {
                // intOption = Menu.customer();
                // boolean isMember = (intOption == 1);
                // if (isMember) {
                // Menu.customerLogin();
                // }
            } else if (choseAdmin) {
                Admin admin = new Admin();
                adminIsInitialized = initializeAdmin(admin, new File("admin.txt"));
                if (adminIsInitialized) {
                    Menu.adminLogin(admin);
                }
            } else { // User wants to exit
                System.out.println("Cancelled");
            }

        } while (!choseExit);
    }

    private static boolean initializeAdmin(Admin admin, File file) {
        boolean fileNotFound = false;
        boolean createAdminFile = false;
        do {
            try {
                Scanner inputFile = new Scanner(file);

                // Checks if empty or not
                if (!inputFile.hasNextLine()) {
                    inputFile.close();
                    throw new FileNotFoundException(); // If the file is empty it is catched by FileNotFoundException
                }

                fileNotFound = false;

                while (inputFile.hasNext()) {
                    StringTokenizer token = new StringTokenizer(inputFile.nextLine(), ";");
                    admin.setName(token.nextToken());
                    admin.setPhoneNumber(Integer.parseInt(token.nextToken()));
                    admin.setId(Integer.parseInt(token.nextToken()));
                    admin.setPassword(token.nextToken());
                }

                // Returns to main and make the admin input it again to login
                inputFile.close();
                return true;
            } catch (FileNotFoundException e) {
                fileNotFound = true;

                JOptionPane.showMessageDialog(null, "Admin file not found.");
                int intOption = JOptionPane.showConfirmDialog(null, "Would you like to create the admin file?");

                createAdminFile = (intOption == 0);
                if (createAdminFile) {
                    createAdminFile(file);
                    fileNotFound = false;
                }

                return false; // returns false as to signify that admin is not Initialized
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (fileNotFound);

        return true;
    }

    private static void createAdminFile(File file) {
        try {
            String strInput;
            PrintWriter adminFile = new PrintWriter(file);

            String[] messages = {
                    "Enter admin's name",
                    "Enter admin's phone number",
                    "Enter admin's ID",
                    "Enter admin's password" };

            for (String message : messages) {
                strInput = JOptionPane.showInputDialog(message);
                if (strInput == null) {
                    // User cancels, file stays empty because we never closed it
                    return;
                }
                adminFile.print(strInput.trim() + ";");
            }
            adminFile.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
