// MIT License
// Copyright (c) 2025 Shah

package user.customer;

import input.Processor;
import product.Product;

import javax.swing.JOptionPane;

public class CustomerUI implements Processor {
    public static int menu() {
        String str = "Please enter your option";
        Object[] options = { "Browse", "Checkout", "Back" };

        int chosenOption = JOptionPane.showOptionDialog(null, str, "Customer Menu", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        return chosenOption;
    }

    public static Customer createCustomer() {
        String name = JOptionPane.showInputDialog("Please enter name ");
        if (name == null) {
            return null;
        }

        String strInput = JOptionPane.showInputDialog("Please enter phone number");
        while (!Processor.isInteger(strInput)) {
            if (strInput == null) {
                return null;
            }
            JOptionPane.showMessageDialog(null, "Phone number must be integers only!");
            strInput = JOptionPane.showInputDialog("Please enter phone number");
        }
        int phoneNumber = Integer.parseInt(strInput);

        return new Customer(name, phoneNumber);
    }

    /* * returns the selected product object
     */
    public static Product selectProduct(Product[] products) {
        Object[] options = { "Previous", "Purchase", "Next" };
        int chosenOption = 0;
        int usableSize = Processor.getUsableArraySize(products);

        int i = 0;
        while (chosenOption != -1 && chosenOption != 1) {
            chosenOption = JOptionPane.showOptionDialog(null, products[i].toString(), "Browse Products",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[2]);
            if (chosenOption == 2) {
                i++;
            } else if (chosenOption == 0) {
                i--;
            }

            if (i < 0) {
                i = usableSize - 1;
            } else if (i >= usableSize) {
                i = 0;
            }
        }
        if (chosenOption == -1) {
            return null;
        }
        return products[i];
    }

    //public static void checkout(User user) {
    //    String message = user.toString();
    //    int intOption = JOptionPane.showOptionDialog(null, products[i].toString(), "Browse Products", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[2]);
    //}
}
