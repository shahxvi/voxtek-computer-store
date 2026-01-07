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
}
