// MIT License
// Copyright (c) 2025 Shah

package user.customer;

import user.User;
import product.*;
import input.Processor;

import javax.swing.*;

public class Customer extends User implements Processor {
    protected ShoppingCart shoppingCart;

    /* Constructors */
    public Customer() {
        super();
        shoppingCart = null;
    }

    public Customer(String name, int phoneNumber, Product[] products) {
        super(name, phoneNumber);
        for (int i = 0; i < products.length; i++) {
            shoppingCart.addProduct(products[i]);
        }
    }

    public Customer(Customer other) {
        super(other);
        this.shoppingCart = other.shoppingCart;
    }
    /* Constructors */

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    // TODO: Prompt the user to enter their name and phoneNumber
    public boolean registerMembership() {
        return true;
    }

    public static int menu() {
        String str = "Please enter your option";
        Object[] options = { "Browse", "Login", "Register", "Back" };

        int chosenOption = JOptionPane.showOptionDialog(null, str, "Customer Menu", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        return chosenOption;
    }

    public static Product browseInventory(Product[] products) {
        Object[] options = { "Previous", "Add to Cart", "Next" };
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
}
