// MIT License
// Copyright (c) 2025 Marzell

package users.customers.members;

import users.customers.Customer;

public class Member extends Customer {
    private double discount = 0.5;

    /* Constructor */
    public Member() {
        super();
    }

    public Member (String name, int phoneNumber) {
        super(name,phoneNumber);
    }
}
