// MIT License
// Copyright (c) 2025 Shah

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class Computer extends Product {
    private String cpu;
    private int memoryBG;
    private int storageGB;
    private String storageType;

    public Computer() {
        super();
        this.cpu = "";
        this.memoryBG = 0;
        this.storageGB = 0;
        this.storageType = "";
    }

    public Computer(String brand, String model, double price, String cpu, int memoryBG, int storageGB,
            String storageType) {
        super(brand, model, price);
        this.cpu = cpu;
        this.memoryBG = memoryBG;
        this.storageGB = storageGB;
        this.storageType = storageType;
    }

    public Computer(Computer other) {
        super(other);
        this.cpu = other.cpu;
        this.memoryBG = other.memoryBG;
        this.storageGB = other.storageGB;
        this.storageType = other.storageType;
    }

    /* Setters */
    public void setCPU(String cpu) {
        this.cpu = cpu;
    }

    public void setMemoryMB(int memoryBG) {
        this.memoryBG = memoryBG;
    }

    public void setStorageMB(int storageGB) {
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

    public int getMemoryMB() {
        return memoryBG;
    }

    public int getStorageMB() {
        return storageGB;
    }

    public String getStorageType() {
        return storageType;
    }
    /* Getters */

    @Override
    public void getInventory(File inputFile, int recordPosition) {
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

    @Override
    public void updateInventory(PrintWriter outputFile) {
        outputFile.printf("%s;%s;%.2f;%s;%d;%d;%s\n", getBrand(), getModel(), getPrice(), cpu, memoryBG, storageGB,
                storageType);
    }

    public String toString() {
        return String.format("%s;%s;%.2f;%s;%d;%d;%s\n", getBrand(), getModel(), getPrice(), cpu, memoryBG, storageGB,
                storageType);
    }
}
