// MIT License
// Copyright (c) 2025 Shah

package user.customer;

import user.User;
import product.*;
import input.Processor;

import javax.swing.*;

public class Customer extends User implements Processor {
    private Product product;

    /* Constructors */
    public Customer() {
        super();
        product = null;
    }

    public Customer(String name, int phoneNumber) {
        super(name, phoneNumber);
        product = null;
    }

    public Customer(String name, int phoneNumber, Product product) {
        super(name, phoneNumber);
        this.product = product;
    }

    public Customer(Customer other) {
        super(other);
        this.product = other.product;
    }
    /* Constructors */

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public String toString() {
        if (product == null) {
            return "\nName: " + name +
                   "\nPhone Number: " + phoneNumber;
        }
        return "\nName: " + name +
               "\nPhone Number: " + phoneNumber +
               "\n" + product.toShortString();
    }
}
