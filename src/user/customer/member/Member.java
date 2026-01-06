// MIT License
// Copyright (c) 2025 Shah

package user.customer.member;

import input.Processor;

import javax.swing.JOptionPane;

public class Member extends Customer implements Processor {
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

    public static void login() {
        String name = JOptionPane.showInputDialog("Enter your name: ");
        String strPhone = JOptionPane.showInputDialog("Enter your phone number: ");
        while (!Processor.isInteger(strPhone)) {
            JOptionPane.showMessageDialog(null, "Please enter integer only.");
            strPhone = JOptionPane.showInputDialog("Enter your phone number: ");
        }
        int phoneNum = Integer.parseInt(strPhone);
    }
}
