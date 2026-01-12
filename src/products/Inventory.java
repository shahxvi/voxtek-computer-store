// MIT License
// Copyright (c) 2025 Shah

package products;

import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Inventory {
    private final int numberOfProducts = 2;
    private final int laptopIndex = 0, keyboardIndex = 1;
    private Product[][] inventory; // First array corresponds to type, the second is their datas
    private File[] file;

    public Inventory(int laptopSize, int keyboardSize, File laptopFile, File keyboardFile) {
        this.inventory = new Product[numberOfProducts][];
        this.inventory[laptopIndex] = new Laptop[laptopSize];
        this.inventory[keyboardIndex] = new Keyboard[keyboardSize];

        this.file = new File[numberOfProducts];
        this.file[laptopIndex] = laptopFile;
        this.file[keyboardIndex] = keyboardFile;

        for (int i = 0; i < inventory.length; i++) {
            for (int j = 0; j < getRecordSize(file[i]); j++) {
                if (inventory[i] instanceof Laptop[]) {
                    inventory[i][j] = new Laptop();
                } else if (inventory[i] instanceof Keyboard[]) {
                    inventory[i][j] = new Keyboard();
                }

                inventory[i][j].load(file[i], j);
            }
        }
    }

    public Inventory(Inventory other) {
        this.inventory = new Product[other.inventory.length][];

        for (int i = 0; i < other.inventory.length; i++) {
            // Allocate memory for the current product type
            this.inventory[i] = new Product[other.inventory[i].length];
            for (int j = 0; j < other.inventory[i].length; j++) {
                if (other.inventory[i][j] != null) {
                    if (other.inventory[i][j] instanceof Laptop) {
                        this.inventory[i][j] = new Laptop((Laptop) other.inventory[i][j]); // Deep copy Laptop
                    } else if (other.inventory[i][j] instanceof Keyboard) {
                        this.inventory[i][j] = new Keyboard((Keyboard) other.inventory[i][j]); // Deep copy Keyboard
                    }
                }
            }
        }

        // Step 2: Copy files array (shallow copy, as `File` is immutable in Java)
        this.file = new File[other.file.length];
        for (int i = 0; i < other.file.length; i++) {
            this.file[i] = other.file[i];
        }
    }

    public void addProduct(Product addedProduct) {
        if (addedProduct instanceof Laptop) {
            int nullIndex = getNullIndex(inventory[laptopIndex]);
            inventory[laptopIndex][nullIndex] = addedProduct;
        } else if (addedProduct instanceof Keyboard) {
            int nullIndex = getNullIndex(inventory[keyboardIndex]);
            inventory[keyboardIndex][nullIndex] = addedProduct;
        }
    }

    // int laptopIndex = 0, keyboardIndex = 1;
    public void removeProduct(int chosenIndex, int index) {

        int chosenInventory = -1;
        if (chosenIndex == laptopIndex) {
            chosenInventory = laptopIndex;
        } else if (chosenIndex == keyboardIndex) {
            chosenInventory = keyboardIndex;
        }

        inventory[chosenInventory] = null;
        reorganize(inventory[chosenInventory]);
    }

    public void removeProduct(Product productToRemove) {
        int indexToRemove = -1;

        int productTypeIndex = -1;
        if (productToRemove instanceof Laptop) {
            productTypeIndex = laptopIndex;
        } else if (productToRemove instanceof Keyboard) {
            productTypeIndex = keyboardIndex;
        }

        for (int i = 0; i < inventory[productTypeIndex].length; i++) {
            if (inventory[productTypeIndex][i] != null) {
                if (inventory[productTypeIndex].equals(productToRemove)) {
                    indexToRemove = i;
                }
            }
        }

        inventory[indexToRemove] = null;
        reorganize(inventory[productTypeIndex]);
    }

    public Product getProduct(Product getProduct) {
        int chosenIndex = -1;

        if (getProduct instanceof Laptop) {
            chosenIndex = laptopIndex;
        } else if (getProduct instanceof Keyboard) {
            chosenIndex = keyboardIndex;
        }

        int removeIndex = -1;
        for (int i = 0; i < inventory[chosenIndex].length; i++) {
            if (inventory[chosenIndex][i].equals(getProduct)) {
                removeIndex = i;
                break;
            }
        }

        Product product = inventory[chosenIndex][removeIndex];

        inventory[chosenIndex][removeIndex] = null;
        reorganize(inventory[chosenIndex]);

        return product;
    }

    public Product getProduct(int inventoryIndex, int productIndex) {
        if (inventoryIndex < 0 || inventoryIndex >= inventory.length) {
            return null;
        }
        if (productIndex < inventory[inventoryIndex].length || productIndex >= inventory[inventoryIndex].length) {
            return null;
        }

        Product product = inventory[inventoryIndex][productIndex];

        inventory[inventoryIndex][productIndex] = null;
        reorganize(inventory[inventoryIndex]);

        return product;
    }

    public Product[][] getInventory() {
        return this.inventory;
    }

    public Product[] getInventory(int chosenIndex) {
        if (chosenIndex < 0 || chosenIndex > 1) {
            return null;
        }
        return this.inventory[chosenIndex];
    }

    public void writeToFile() {
        for (int i = 0; i < file.length; i++) {
            try (PrintWriter outputFile = new PrintWriter(file[i])) {
                for (int j = 0; j < inventory[i].length; j++) {
                    if (inventory[i][j] != null) {
                        outputFile.println(inventory[i][j].toRecord());
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /*
     * Method that returns the number of records in a file
     */
    private int getRecordSize(File file) {
        int recordSize = 0;
        try (Scanner inputFile = new Scanner(file)) {
            while (inputFile.hasNext()) {
                inputFile.nextLine();
                recordSize++;
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return recordSize;
    }

    private void reorganize(Product[] products) {
        Product[] reorganizedProducts = null;
        int productsIndex = -1;

        if (products instanceof Laptop[]) {
            reorganizedProducts = new Laptop[products.length];
            productsIndex = laptopIndex;
        } else if (products instanceof Keyboard[]) {
            reorganizedProducts = new Keyboard[products.length];
            productsIndex = keyboardIndex;
        }

        int index = 0;
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null) {
                reorganizedProducts[index++] = products[i];
            }
        }

        this.inventory[productsIndex] = reorganizedProducts;
    }

    private int getNullIndex(Product[] products) {
        for (int index = 0; index < products.length; index++) {
            if (products[index] == null) {
                return index;
            }
        }
        return -1;
    }
}
