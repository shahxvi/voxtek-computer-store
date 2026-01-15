// MIT License
// Copyright (c) 2025 Shah

package users.customers;

import java.io.*;
import javax.swing.JOptionPane;

class CustomerFileHandler {
    static void printOrder(Customer customer) {
        File fileName = new File(customer.getName() + "_");
        try (PrintWriter outputFile = new PrintWriter()) {
            outputFile.println("VoxTek Technology Store Order");
            outputFile.println("Customer's Name: " + customer.getName());
            outputFile.println("Customer's Phone Number: " + customer.getPhoneNumber());

            for (int i = 0; i < customer.getCartSize(); i++) {
                String product = customer.getProduct(i).toShortString();
                double price = customer.getProduct(i).getPrice();

                outputFile.println(product + "RM" + price);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
