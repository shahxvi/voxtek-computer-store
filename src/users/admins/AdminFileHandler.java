// MIT License
// Copyright (c) 2025 Shah

package users.admins;

import products.*;
import processors.Processor;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

class AdminFileHandler implements Processor {
    private static File file = new File("admin.txt");

    /*
     * initializes the admin (loads admin from file to object)
     * if the file is missing or empty, it calls createAdminFile
     */
    static boolean initializeAdmin(Admin admin) {
        boolean isInitialized = false;

        while (!isInitialized) {
            try {
                loadAdmin(admin);
                isInitialized = true;
            } catch (FileNotFoundException e) {
                admin = AdminUI.createAdmin();
                boolean isCreated = createAdminFile(admin);

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
    static boolean loadAdmin(Admin admin) throws FileNotFoundException {
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
     static boolean createAdminFile(Admin admin) {
        if (admin == null) {
            return false;
        }

        try (PrintWriter adminFile = new PrintWriter(file)) {
            adminFile.println(admin.toRecord());
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }

        JOptionPane.showMessageDialog(null, "Admin File Created!");

        return true;
    }
}
