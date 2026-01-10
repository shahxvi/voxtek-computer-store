// MIT License
// Copyright (c) 2025 Shah

package products;

import processors.Processor;

import java.io.File;

public abstract class Product implements Processor {
    /* Attributes */
    protected String brand;
    protected String model;
    protected double price;
    /* Attributes */

    /* Contructors */
    protected Product() {
        brand = "";
        model = "";
        price = 0.00;
    }

    protected Product(String brand, String model, double price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    protected Product(Product other) {
        brand = other.brand;
        model = other.model;
        price = other.price;
    }
    /* Contructors */

    /* Setters */
    public final void setBrand(String brand) {
        this.brand = brand;
    }

    public final void setModel(String model) {
        this.model = model;
    }

    public final void setPrice(double price) {
        this.price = price;
    }
    /* Setters */

    /* Getter */
    public final String getBrand() {
        return brand;
    }

    public final String getModel() {
        return model;
    }

    public final double getPrice() {
        return price;
    }
    /* Getter */

    public static void initializeInventory(Product[][] inventory, File[] file) {
        for (int i = 0; i < inventory.length; i++) {
            for (int j = 0; j < Processor.getInventorySize(file[i]); j++) {
                if (inventory[i] instanceof Laptop[]) {
                    inventory[i][j] = new Laptop();
                } else if (inventory[i] instanceof Keyboard[]) {
                    inventory[i][j] = new Keyboard();
                }

                inventory[i][j].load(file[i], j);
            }
        }
    }

    /*
     * loadInventory()
     * This method must be implemented by every subclass.
     * The inventory of the product would be stored in a file that the subclass must
     * read and store it into memory.
     *
     * The subclass would hold only a record hence you must pass a recordPosition.
     */
    protected abstract void load(File inputFile, int recordPosition);

    public abstract String toRecord();

    public abstract String toString();

    public abstract String toShortString();

}
