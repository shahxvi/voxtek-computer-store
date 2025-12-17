// MIT License
// Copyright (c) 2025 Shah

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Computer extends Product {
    private String cpu;
    private int memoryMB;
    private int storageMB;
    private String storageType;

    public Computer() {
        super();
        cpu = "";
        memoryMB = 0;
        storageMB = 0;
        storageType = "";
    }

    public Computer(String brand, String model, double price, String cpu, int memoryMB, int storageMB,
            String storageType) {
        super(brand, model, price);
        this.cpu = cpu;
        this.memoryMB = memoryMB;
        this.storageMB = storageMB;
        this.storageType = storageType;
    }

    public Computer(Computer other) {
        super(other.getBrand(), other.getModel(), other.getPrice());
        cpu = other.cpu;
        memoryMB = other.memoryMB;
        storageMB = other.storageMB;
        storageType = other.storageType;
    }

    /* Setters */
    public void setCPU(String cpu) {
        this.cpu = cpu;
    }

    public void setMemoryMB(int memoryMB) {
        this.memoryMB = memoryMB;
    }

    public void setStorageMB(int storageMB) {
        this.storageMB = storageMB;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }
    /* Setters */

    /* Getters */
    public String getCPU() {
        return cpu;
    }

    public int getMemoryMB() {
        return memoryMB;
    }

    public int getStorageMB() {
        return storageMB;
    }

    public String getStorageType() {
        return storageType;
    }
    /* Getters */

    @Override
    public void getInventory(File inputFile, int recordPosition) {
        try {
            Scanner inputFileReader = new Scanner(inputFile);

            while (inputFileReader.hasNextLine()) {

                // Skips to the record of the recordPosition specified
                for (int field = 0; field < recordPosition; field++) {
                    inputFileReader.nextLine();
                }

                // Read the recordPosition specified
                String aRecord = inputFileReader.nextLine();
                StringTokenizer token = new StringTokenizer(aRecord, ";");

                // Stores data of each fields to object
                setBrand(token.nextToken());
                setModel(token.nextToken());
                setPrice(Integer.parseInt(token.nextToken()));
                setCPU(token.nextToken());
                setMemoryMB(Integer.parseInt(token.nextToken()));
                setStorageMB(Integer.parseInt(token.nextToken()));
                setStorageType(token.nextToken());
            }
            inputFileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
