// MIT License
// Copyright (c) 2025 Raden

public abstract class User {
    String name;
    int phoneNumber;

    protected void setName(String name) {
        this.name = name;
    }

    protected void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String toString() {
        return name + " " + phoneNumber;
    }
}