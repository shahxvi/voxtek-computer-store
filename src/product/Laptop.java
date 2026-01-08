// MIT License
// Copyright (c) 2025 Shah

package product;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Laptop extends Product {
    private String cpu;
    private int memoryGB;
    private int storageGB;
    private String storageType;

    public Laptop() {
        super();
        this.cpu = "";
        this.memoryGB = 0;
        this.storageGB = 0;
        this.storageType = "";
    }

    public Laptop(String brand, String model, double price, String cpu, int memoryGB, int storageGB, String storageType) {
        super(brand, model, price);
        this.cpu = cpu;
        this.memoryGB = memoryGB;
        this.storageGB = storageGB;
        this.storageType = storageType;
    }

    public Laptop(Laptop other) {
        super(other);
        this.cpu = other.cpu;
        this.memoryGB = other.memoryGB;
        this.storageGB = other.storageGB;
        this.storageType = other.storageType;
    }

    /* Setters */
    public void setCPU(String cpu) {
        this.cpu = cpu;
    }

    public void setMemoryGB(int memoryGB) {
        this.memoryGB = memoryGB;
    }

    public void setStorageGB(int storageGB) {
        this.storageGB = storageGB;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }
    /* Setters */

    /* Getters */
    public String getCPU() {
        return cpu;
    }

    public int getMemoryGB() {
        return memoryGB;
    }

    public int getStorageGB() {
        return storageGB;
    }

    public String getStorageType() {
        return storageType;
    }
    /* Getters */

    @Override
    public void load(File inputFile, int recordPosition) {
        try {
            Scanner inputFileReader = new Scanner(inputFile);

            // Skips to the record of the recordPosition specified
            for (int field = 0; field < recordPosition; field++) {
                inputFileReader.nextLine();
            }

            if (inputFileReader.hasNextLine()) {
                // Read the recordPosition specified
                String aRecord = inputFileReader.nextLine();
                StringTokenizer token = new StringTokenizer(aRecord, ";");

                // Stores data of each fields to object
                setBrand(token.nextToken());
                setModel(token.nextToken());
                setPrice(Double.parseDouble(token.nextToken()));
                setCPU(token.nextToken());
                setMemoryGB(Integer.parseInt(token.nextToken()));
                setStorageGB(Integer.parseInt(token.nextToken()));
                setStorageType(token.nextToken());
            }
            inputFileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toRecord() {
        return String.format("%s;%s;%.2f;%s;%d;%d;%s", brand, model, price, cpu, memoryGB, storageGB, storageType);
    }

    @Override
    public String toString() {
        return "Brand: " + brand +
               "\nModel: " + model +
               "\nCPU:" + cpu +
               "\nMemory: " + memoryGB + "GB" +
               "\nStorage: " + storageGB + "GB" +
               "\nStorage Type: " + storageType +
               "\nPrice: RM" + price;
    }

    @Override
    public String toShortString() {
        return brand + " " + model + " (RM" + price + ")";
    }
}
