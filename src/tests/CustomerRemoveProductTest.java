// MIT License
// Copyright (c) 2025 Shah

package tests;

import users.customers.Customer;
import products.*;

/**
 * Test class for Customer.removeProduct() functionality
 * This class tests the following scenarios:
 * - Normal removal of a product when there are multiple products in the cart
 * - Attempting to remove a product from an invalid index
 * - Reorganization of the cart after removal
 * - Handling of the cart when all products are removed
 */
public class CustomerRemoveProductTest {
    private static int testsPassed = 0;
    private static int testsFailed = 0;

    public static void main(String[] args) {
        System.out.println("=== Customer RemoveProduct Test Suite ===\n");

        testRemoveProductFromMultipleProducts();
        testRemoveFirstProduct();
        testRemoveLastProduct();
        testRemoveAllProducts();
        testInvalidIndexHandling();
        testCartReorganization();

        System.out.println("\n=== Test Results ===");
        System.out.println("Tests Passed: " + testsPassed);
        System.out.println("Tests Failed: " + testsFailed);
        
        if (testsFailed == 0) {
            System.out.println("\n✓ All tests passed!");
            System.exit(0);
        } else {
            System.out.println("\n✗ Some tests failed!");
            System.exit(1);
        }
    }

    /**
     * Test normal removal of a product when there are multiple products in the cart
     */
    private static void testRemoveProductFromMultipleProducts() {
        System.out.println("Test 1: Remove product from cart with multiple products");
        
        Customer customer = new Customer("Test User", 12345678, 5);
        Product[] cart = customer.getProductsCart();
        
        Laptop laptop = new Laptop("Dell", "XPS 15", 5999.00, "Intel i7", 16, 512, "SSD");
        Keyboard keyboard = new Keyboard("Logitech", "MX Keys", 499.00, "Mechanical", true);
        Laptop laptop2 = new Laptop("HP", "Pavilion", 3499.00, "Intel i5", 8, 256, "SSD");
        
        cart[0] = laptop;
        cart[1] = keyboard;
        cart[2] = laptop2;
        
        Product removed = customer.removeProduct(1);
        cart = customer.getProductsCart();
        
        boolean passed = cart[0] != null && cart[0].getBrand().equals("Dell") &&
                        cart[1] != null && cart[1].getBrand().equals("HP") &&
                        cart[2] == null &&
                        removed != null && removed.getBrand().equals("Logitech");
        
        printTestResult("Remove middle product", passed);
    }

    /**
     * Test removing the first product from the cart
     */
    private static void testRemoveFirstProduct() {
        System.out.println("\nTest 2: Remove first product from cart");
        
        Customer customer = new Customer("Test User", 12345678, 5);
        Product[] cart = customer.getProductsCart();
        
        Laptop laptop = new Laptop("Dell", "XPS 15", 5999.00, "Intel i7", 16, 512, "SSD");
        Keyboard keyboard = new Keyboard("Logitech", "MX Keys", 499.00, "Mechanical", true);
        
        cart[0] = laptop;
        cart[1] = keyboard;
        
        Product removed = customer.removeProduct(0);
        cart = customer.getProductsCart();
        
        boolean passed = cart[0] != null && cart[0].getBrand().equals("Logitech") &&
                        cart[1] == null &&
                        removed != null && removed.getBrand().equals("Dell");
        
        printTestResult("Remove first product", passed);
    }

    /**
     * Test removing the last (only) product from the cart
     */
    private static void testRemoveLastProduct() {
        System.out.println("\nTest 3: Remove last product from cart");
        
        Customer customer = new Customer("Test User", 12345678, 5);
        Product[] cart = customer.getProductsCart();
        
        Laptop laptop = new Laptop("Dell", "XPS 15", 5999.00, "Intel i7", 16, 512, "SSD");
        cart[0] = laptop;
        
        Product removed = customer.removeProduct(0);
        cart = customer.getProductsCart();
        
        boolean passed = cart[0] == null && removed != null && removed.getBrand().equals("Dell");
        
        printTestResult("Remove last product", passed);
    }

    /**
     * Test handling of the cart when all products are removed
     */
    private static void testRemoveAllProducts() {
        System.out.println("\nTest 4: Remove all products from cart");
        
        Customer customer = new Customer("Test User", 12345678, 5);
        Product[] cart = customer.getProductsCart();
        
        cart[0] = new Laptop("Dell", "XPS 15", 5999.00, "Intel i7", 16, 512, "SSD");
        cart[1] = new Keyboard("Logitech", "MX Keys", 499.00, "Mechanical", true);
        
        customer.removeProduct(0);
        customer.removeProduct(0); // After first removal, second product is now at index 0
        
        cart = customer.getProductsCart();
        boolean passed = cart[0] == null && cart[1] == null;
        
        printTestResult("Remove all products", passed);
    }

    /**
     * Test attempting to remove a product from an invalid index
     */
    private static void testInvalidIndexHandling() {
        System.out.println("\nTest 5: Handle invalid index");
        
        Customer customer = new Customer("Test User", 12345678, 5);
        Product[] cart = customer.getProductsCart();
        
        cart[0] = new Laptop("Dell", "XPS 15", 5999.00, "Intel i7", 16, 512, "SSD");
        
        boolean passed = false;
        try {
            // Try to remove from an invalid index (should throw exception)
            customer.removeProduct(5);
            passed = false; // Should not reach here
        } catch (ArrayIndexOutOfBoundsException e) {
            passed = true; // Expected exception
        } catch (Exception e) {
            passed = false; // Unexpected exception
        }
        
        printTestResult("Handle invalid index (out of bounds)", passed);
        
        // Test removing from null slot
        System.out.println("\nTest 5b: Handle removing null product");
        customer = new Customer("Test User", 12345678, 5);
        cart = customer.getProductsCart();
        cart[0] = new Laptop("Dell", "XPS 15", 5999.00, "Intel i7", 16, 512, "SSD");
        
        Product removed = customer.removeProduct(1); // Index 1 is null
        passed = removed == null;
        
        printTestResult("Handle removing null product", passed);
    }

    /**
     * Test reorganization of the cart after removal
     */
    private static void testCartReorganization() {
        System.out.println("\nTest 6: Cart reorganization after removal");
        
        Customer customer = new Customer("Test User", 12345678, 5);
        Product[] cart = customer.getProductsCart();
        
        Laptop laptop1 = new Laptop("Dell", "XPS 15", 5999.00, "Intel i7", 16, 512, "SSD");
        Keyboard keyboard = new Keyboard("Logitech", "MX Keys", 499.00, "Mechanical", true);
        Laptop laptop2 = new Laptop("HP", "Pavilion", 3499.00, "Intel i5", 8, 256, "SSD");
        Laptop laptop3 = new Laptop("Lenovo", "ThinkPad", 4299.00, "Intel i7", 16, 512, "SSD");
        
        cart[0] = laptop1;
        cart[1] = keyboard;
        cart[2] = laptop2;
        cart[3] = laptop3;
        
        // Remove keyboard at index 1
        customer.removeProduct(1);
        cart = customer.getProductsCart();
        
        // Verify that all non-null products are at the beginning with no gaps
        boolean passed = cart[0] != null && cart[0].getBrand().equals("Dell") &&
                        cart[1] != null && cart[1].getBrand().equals("HP") &&
                        cart[2] != null && cart[2].getBrand().equals("Lenovo") &&
                        cart[3] == null &&
                        cart[4] == null;
        
        printTestResult("Cart reorganization", passed);
    }

    private static void printTestResult(String testName, boolean passed) {
        if (passed) {
            System.out.println("  ✓ " + testName + " PASSED");
            testsPassed++;
        } else {
            System.out.println("  ✗ " + testName + " FAILED");
            testsFailed++;
        }
    }
}
