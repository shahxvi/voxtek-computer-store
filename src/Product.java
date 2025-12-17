import java.io.*;

public abstract class Product {
    /* Attributes */
    private String brand;
    private String model;
    private double price;
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
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    /* Setters */

    /* Getter */
    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }
    /* Getter */

    protected abstract void getInventory(File inputFile, int recordPosition);
}
