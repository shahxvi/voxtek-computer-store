// MIT License
// Copyright (c) 2025 Shah

package users.customers;

import users.User;
import products.*;
import processors.Processor;

public class Customer extends User implements Processor {
    private Product[] productsCart;
    private int cartSize;
    private int cartPointer = 0;

    /* Constructors */
    public Customer() {
        super();
        productsCart = null;
    }

    public Customer(String name, int phoneNumber) {
        super(name, phoneNumber);
        productsCart = null;
    }

    public Customer(String name, int phoneNumber, Product[] productsCart) {
        super(name, phoneNumber);
        this.productsCart = productsCart;
    }

    public Customer(String name, int phoneNumber, int cartSize) {
        super(name, phoneNumber);
        this.productsCart = new Product[cartSize];
    }

    public Customer(Customer other) {
        super(other);
        this.productsCart = other.productsCart;
    }
    /* Constructors */

    public void addProduct(Product product, Inventory inventory) {
        if (productsCart == null || inventory == null || product == null) {
            return;
        }

        productsCart[cartPointer++] = inventory.getProduct(product);
    }

    public void removeProduct(int removeIndex, Inventory inventory) {
        if (removeIndex < 0 || removeIndex >= cartPointer) {
            return;
        }

        Product removedProduct = this.productsCart[removeIndex];

        this.productsCart[removeIndex] = null;
        this.cartPointer--;

        reorganize();

        inventory.addProduct(removedProduct);
    }

    public void setProductsCart(Product[] products) {
        this.productsCart = products;
    }

    public Product[] getProductsCart() {
        return productsCart;
    }

    public int getCartSize() {
        if (productsCart == null) {
            return 0;
        }
        return cartSize;
    }

    public Product getProduct(int index) {
        if (productsCart[index] == null) {
            return null;
        }
        return productsCart[index];
    }

    public double getTotalPrice() {
        double totalPrice = 0.00;
        for (int i = 0; i < cartPointer; i++) {
            totalPrice += productsCart[i].getPrice();
        }
        return totalPrice;
    }

    public String toString() {
        if (productsCart == null) {
            return "\nName: " + name +
                   "\nPhone Number: " + phoneNumber;
        }

        String products = "";
        for (int i = 0; i < cartPointer; i++) {
            products += ("\n" + productsCart[i].toShortString());
        }

        return "\nName: " + name +
               "\nPhone Number: " + phoneNumber +
               "\n" + products +
               "\nTotal Price: RM" + getTotalPrice();
    }

    public void reorganize() {
        Product[] reorganizedProducts = new Product[50];

        int index = 0;
        for (int i = 0; i < reorganizedProducts.length; i++) {
            if (productsCart[i] == null) {
                continue;
            }
            reorganizedProducts[index++] = productsCart[i];
        }
        this.productsCart = reorganizedProducts;
    }
}
