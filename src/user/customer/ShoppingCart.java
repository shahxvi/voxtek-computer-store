// MIT License
// Copyright (c) 2025 Shah

package customer;

import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<Product> products = new ArrayList<>();

    public void addProducts(Product[] products) {
        for (int i = 0; i < products.length; i++) {
            this.products.add(products[i]);
        }
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public Product getProduct(int index) {
        return products.get(index);
    }

    public Product[] getProducts() {
        Product[] allProducts = new Product[products.size()];
        for (int i = 0; i < allProducts.length; i++) {
            allProducts[i] = products.get(i);
        }
        return allProducts;
    }
}
