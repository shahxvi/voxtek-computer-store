// MIT License
// Copyright (c) 2025 Shah

package processors;

import products.*;

import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public interface Processor {
    public static boolean isInteger(String strInput) {
        if (strInput == null || strInput.isEmpty()) {
            return false;
        }
        for (int j = 0; j < strInput.length(); j++) {
            if (!Character.isDigit(strInput.charAt(j))) {
                return false;
            }
        }
        return true;
    }

    public static int getUsableArraySize(Product[] product) {
        int usableSize = 0;
        for (int i = 0; i < product.length; i++) {
            if (product[i] != null)
                usableSize++;
        }
        return usableSize;
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return recordSize;
    }
}
