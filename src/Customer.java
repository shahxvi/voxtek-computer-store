// MIT License
// Copyright (c) 2025 Shah
// Copyright (c) 2025 Raden

public class Customer extends User {
    protected Product[] products;

    /* Constructors */
    public Customer() {
        super();
        products = null;
    }

    public Customer(String name, int phoneNumber, Product[] products) {
        super(name, phoneNumber);
        this.products = products;
    }

    public Customer(Customer other) {
        super(other);
        this.products = other.products;
    }
    /* Constructors */

    public void addProduct(Product[] products) {
        this.products = products;
    }

    // TODO: Prompt the user to enter their name and phoneNumber
    public boolean registerMembership() {
        return true;
    }
}
