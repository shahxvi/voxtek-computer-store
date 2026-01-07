// MIT License
// Copyright (c) 2025 Shah

package user.admin;

import user.*;
import product.Product;

import java.io.File;

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

    public static void flow(Product[][] products, Admin admin, File file) {
        String strOption;
        int intOption;
        boolean choseExit = false;

        if (!AdminFileHandler.initializeAdmin(admin, file))
            return;

        if (!AdminUI.login(admin))
            return;

        do {
            strOption = UserUI.chooseInventory();

            if (strOption == null)
                break;

            intOption = AdminUI.chooseAddOrRemoveProduct();

            choseExit = (intOption == 2 && intOption == -1);

            int chosenProduct = -1;
            if (strOption.equalsIgnoreCase("Laptops"))
                chosenProduct = 0;
            else if (strOption.equalsIgnoreCase("Keyboards"))
                chosenProduct = 1;

            if (intOption == 0) {
                products[chosenProduct] = AdminFileHandler.addLaptop(products[chosenProduct]);
            }
            else if (intOption == 1) {
                products[chosenProduct] = AdminFileHandler.removeProduct(products[chosenProduct]);
            }
        } while (!choseExit);

        return;
    }
}
