// MIT License
// Copyright (c) 2025 Shah

public class Member extends Customer {
    private final double discount = 0.25;

    public Member() {
        super();
        products = null;
    }

    public Member(String name, int phoneNumber, Product[] products) {
        super(name, phoneNumber, products);
    }

    public Member(Member other) {
        super(other);
    }

    public double applyDiscount() {
        return discount;
    }
}
