// MIT License
// Copyright (c) 2025 Shah
// Copyright (c) 2025 Raden

package user;

import javax.swing.JOptionPane;

public abstract class User {
    private String name;
    private int phoneNumber;

    /* Constructors */
    public User() {
        name = "";
        phoneNumber = 0;
    }

    public User(String name, int phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public User(User other) {
        this.name = other.name;
        this.phoneNumber = other.phoneNumber;
    }
    /* Constructors */

    /* Setters */
    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /* Setters */

    /* Getters */
    public String getName() {
        return name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }
    /* Getters */

    /*
     * menu() - Gives user options to login as customer, admin or exit from the
     * program
     */
    public static int menu() {
        String str;
        Object[] options = { "Customer", "Admin", "Exit" };
        str = "Welcome to VoxTek Computer Store\n";
        str += "Please choose your option: ";

        int chosenOption = JOptionPane.showOptionDialog(null, str, "VoxTek", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        return chosenOption;
    }

    /*
     * chooseInventory() is a menu to choose what inventory they want to edit
     */
    public static String chooseInventory() {
        String[] options = { "Laptops", "Keyboards" };
        String chosenOption = (String) JOptionPane.showInputDialog(null, "Please choose an inventory",
                "Choose Inventory", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        return chosenOption;
    }
}
