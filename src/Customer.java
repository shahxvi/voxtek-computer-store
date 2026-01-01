// MIT License
// Copyright (c) 2025 Raden

public class Customer extends User {
    private Product[] products;
    private boolean isMember;
    private double discount;

    /* Constructors */
    public Customer() {
        super();
        products = null;
        isMember = false;
        discount = 0.00;
    }

    public Customer(String name, int phoneNumber, Product[] products, boolean isMember, double discount) {
        super(name, phoneNumber);
        this.products = products;
        this.isMember = isMember;
        this.discount = discount;
    }

    public Customer(Customer other) {
        super(other);
        this.products = other.products;
        this.isMember = other.isMember;
        this.discount = other.discount;
    }
    /* Constructors */

    /* Setters */
    public void setMember (boolean isMember) {
        this.isMember = isMember;
    }

    public void setDiscount (double discount) {
        this.discount = discount;
    }
    /* Setters */

    /* Getters */
    public boolean getMember () {
        return isMember;
    }

    public double getDiscount () {
        return discount;
    }
    /* Getters */

    public void registerMembership (String name, int number) {
        super(name, number);
        isMember = true;
    }
}
