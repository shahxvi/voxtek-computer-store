// MIT License
// Copyright (c) 2025 Shah

public class VoxTek {
    public static void main(String[] args) {

        int option = Menu.main();
        if (option == 0) {
            System.out.println("Customer Selected");
        } else if (option == 1) {
            System.out.println("Admin Selected");
            Admin admin = new Admin();
            Menu.adminLogin(admin);
        } else {
            System.out.println("Cancelled");
        }
    }
}
