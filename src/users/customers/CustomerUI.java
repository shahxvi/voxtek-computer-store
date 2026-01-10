// MIT License
// Copyright (c) 2025 Shah

package users.customers;

import processors.Processor;
import products.*;
import users.UserUI;

import javax.swing.JOptionPane;

public class CustomerUI implements Processor {
    private static Customer customer = null;
    private static boolean browse = false;
    private static boolean cart = false;
    private static boolean logout = false;
    private static boolean exit = false;

    public static void run(Product[][] products) {
        Product[][] backupProducts = new Product[50][50];
        for (int i = 0; i < products.length; i++) {
            for (int j = 0; j < Processor.getUsableArraySize(products[i]); j++) {
                if (products[i][j] instanceof Laptop) {
                    backupProducts[i][j] = new Laptop((Laptop) products[i][j]);
                } else if (products[i][j] instanceof Keyboard) {
                    backupProducts[i][j] = new Keyboard((Keyboard) products[i][j]);
                }
            }
        }

        do {
            menu();

            if (browse) {
                if (customer == null) {
                    createCustomer();
                }

                if (customer == null) {
                    continue;
                }

                String strOption = UserUI.chooseInventory();

                Product[] selectedInventory = null;
                if (strOption == null) {
                    continue;
                } else if (strOption.equals("Laptops")) {
                    selectedInventory = products[0];
                } else if (strOption.equals("Keyboards")) {
                    selectedInventory = products[1];
                }

                Product chosenProduct = browse(selectedInventory);
                if (chosenProduct == null)
                    continue;

                selectedInventory = customer.addProduct(chosenProduct, selectedInventory);

                if (strOption.equals("Laptops")) {
                    products[0] = selectedInventory;
                } else if (strOption.equals("Keyboards")) {
                    products[1] = selectedInventory;
                }
            } else if (cart) {
                int intOption = shoppingCart();

                if (intOption == 0) {
                    //TODO: put removedProduct back into the original product
                    Product removedProduct = removeProduct();
                } else if (intOption == 1) {
                    checkout();
                    return;
                }
            }
        } while (!exit);

        if (logout) {
            for (int i = 0; i < backupProducts.length; i++) {
                for (int j = 0; j < Processor.getUsableArraySize(backupProducts[i]); j++) {
                        products[i][j] = backupProducts[i][j];
                }
            }
        }
    }

    public static void createCustomer() {
        String name = JOptionPane.showInputDialog("Please enter name ");
        if (name == null) {
            return;
        }

        String strInput = JOptionPane.showInputDialog("Please enter phone number");
        while (!Processor.isInteger(strInput)) {
            if (strInput == null) {
                return;
            }
            JOptionPane.showMessageDialog(null, "Phone number must be integers only!");
            strInput = JOptionPane.showInputDialog("Please enter phone number");
        }
        int phoneNumber = Integer.parseInt(strInput);

        customer = new Customer(name, phoneNumber, 50);
    }

    public static void menu() {
        String str = "Please enter your option";

        Object[] options = { "Browse", "Cart", "Cancel" };
        if (customer != null) {
            options[2] = "Logout";
        }
        int chosenOption = JOptionPane.showOptionDialog(null, str, "Customer Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (chosenOption == 0) {
            browse = true;
            cart = false;
            exit = false;
            logout = false;
        } else if (chosenOption == 1) {
            cart = true;
            browse = false;
            exit = false;
            logout = false;
        } else {
            cart = false;
            browse = false;
            exit = true;
            if (customer != null) {
                customer = null;
                logout = true;
            }
        }
    }

    /*
     * returns the selected product object
     */
    public static Product browse(Product[] products) {
        Object[] options = { "Previous", "Add to Cart", "Next" };
        int chosenOption = 0;
        int usableSize = Processor.getUsableArraySize(products);

        int i = 0;
        while (chosenOption != -1 && chosenOption != 1) {
            chosenOption = JOptionPane.showOptionDialog(null, products[i].toString(), "Browse Products", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[2]);
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

    public static int shoppingCart() {
        if (customer == null || customer.getCartSize() <= 0) {
            JOptionPane.showMessageDialog(null, "Your cart is empty.");
            return -1;
        }

        // Get cart information
        String message = "Shopping Cart:\n";

        String[] cart = new String[customer.getCartSize()];
        for (int i = 0; i < cart.length; i++) {
            message += (i+1) + ". " + customer.getProduct(i).toShortString() + "\n";
        }

        Object[] options = { "Remove Product" , "Checkout", "Back" };
        return JOptionPane.showOptionDialog(null, message, "Browse Products", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
    }

    public static Product removeProduct() {
        String[] cart = new String[customer.getCartSize()];

        for (int i = 0; i < cart.length; i++) {
            cart[i] = customer.getProduct(i).toShortString();
        }
        String chosenOption = (String) JOptionPane.showInputDialog(null, "Please choose an inventory", "Choose Inventory", JOptionPane.QUESTION_MESSAGE, null, cart, cart[0]);

        int removedIndex = -1;
        for (int i = 0; i < cart.length; i++) {
            if (cart[i].equals(chosenOption)) {
                removedIndex = i;
                break;
            }
        }

        return customer.removeProduct(removedIndex);
    }

    public static void checkout() {
        JOptionPane.showMessageDialog(null, customer.toString());
    }
}
