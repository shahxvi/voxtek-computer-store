// MIT License
// Copyright (c) 2025 Shah

import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public interface InputProcessor {
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
     * This method reorganizes an array so that there's no null array in between
     * populated index
     */
    public static Product[] reorganizeInventory(Product[] products) {
        Product[] reorganizedProducts = null;
        if (products instanceof Laptop[]) {
            reorganizedProducts = new Laptop[products.length];
        } else if (products instanceof Keyboard[]) {
            reorganizedProducts = new Keyboard[products.length];
        }

        int index = 0;
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null) {
                reorganizedProducts[index++] = products[i];
            }
        }

        return reorganizedProducts;
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
