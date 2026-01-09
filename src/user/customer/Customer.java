// MIT License
// Copyright (c) 2025 Shah

package user.customer;

import user.User;
import product.Product;
import input.Processor;

import javax.swing.*;

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

    /*
     * This method will add the product and removes the original one
     */
    public Product[] addProduct(Product product, Product[] original) {
        if (productsCart == null) {
            return original;
        }
        productsCart[cartPointer] = product;

        int deleteIndex = -1;
        for (int i = 0; i < original.length; i++) {
            if (original[i].equals(productsCart[cartPointer])) {
                deleteIndex = i;
                break;
            }
        }
        cartPointer++;
        if (deleteIndex != -1) {
            original[deleteIndex] = null;
        }
        return Processor.reorganizeInventory(original);
    }

    public Product removeProduct(int index) {
        productsCart[index] = null;
        cartPointer--;

        productsCart = Processor.reorganizeInventory(productsCart);
        return null;
    }

    public Product[] getProductsCart() {
        return productsCart;
    }

    public int getCartSize() {
        if (productsCart == null) {
            return 0;
        }
        return Processor.getUsableArraySize(productsCart);
    }

    public Product getProduct(int index) {
        if (productsCart[index] == null) {
            return null;
        }
        return productsCart[index];
    }

    public String toString() {
        if (productsCart == null) {
            return "\nName: " + name +
                   "\nPhone Number: " + phoneNumber;
        }

        String str = "";
        for (int i = 0; i < cartPointer; i++) {
            str += ("\n" + productsCart[i].toShortString());
        }

        return "\nName: " + name +
               "\nPhone Number: " + phoneNumber +
               "\n" + str;
    }
}
