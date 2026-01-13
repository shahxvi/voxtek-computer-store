// MIT License
// Copyright (c) 2025 Shah

package users.admins;

import users.*;

public class Admin extends User {
    private int id;
    private String password;

    /* Contructors */
    public Admin() {
        super();
        id = 0;
        password = "";
    }

    public Admin(String name, int phoneNumber, int id, String password) {
        super(name, phoneNumber);
        this.id = id;
        this.password = password;
    }

    public Admin(Admin other) {
        super(other);
        this.id = other.id;
        this.password = other.password;
    }
    /* Contructors */

    /* Setters */
    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    /* Setters */

    /* Getters */
    public int getId() {
        return id;
    }
    /* Getters */

    public boolean verifyCredentials(int id, String password) {
        if (this.id == id && this.password.equals(password)) {
            return true;
        }
        return false;
    }

    public String toRecord() {
        return String.format("%s;%d;%d;%s", getName(), getPhoneNumber(), id, password);
    }
}
