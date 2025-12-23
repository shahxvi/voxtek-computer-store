// MIT License
// Copyright (c) 2025 Marzell

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class Keyboard extends Product {
    private String switchType;
    private boolean wireless;

    public Keyboard() {
        super();
        this.switchType = "";
        this.wireless = false;
    }

    public Keyboard(String model, String brand, String price, String switchType, boolean wireless) {
        super(model, brand, price);
        this.switchType = switchType;
        this.wireless = wireless;
    }

    public Keyboard(Keyboard other) {
        super(other);
        this.switchType = other.switchType;
        this.wireless = other.wireless;
    }

    public void setSwitchType(String switchType) {
        this.switchType = switchType;
    }

    public void setWireless(boolean wireless) {
        this.wireless = wireless;
    }

    public String getSwitchType() {
        return switchType;
    }

    public boolean getWireless() {
        return wireless:
    }

    @Override
    public void loadInventory(File inputFile, int recordPosition) {
        try {
            Scanner inputFileReader = new Scanner(inputFile);

            for (int field = 0; field < recordPosition; field++) {
                inputFileReader.nextLine();
            }

            if (inputFileReader.hasNextLine()) {
                String aRecord = inputFileReader.nextLine();
                StringTokenizer token = new StringTokenizer(aRecord, ";");

                setModel(token.nextToken());
                setBrand(token.nextToken());
                setPrice(Double.parseDouble(token.nextToken()));
                setSwitchType(token.nextToken());
                setWireless(Boolean.parseBoolean(token.nextToken()));
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
    public String toRecord() {
        return String.format("%s,%s,%.2f,%s,%b\n", getModel(), getBrand(), getPrice(), switchType, wireless);
    }

    @Override
    public void writeToFile(PrintWriter outputFile) {
        outputFile.printf(toRecord());
    }

    @Override
    public String toString() {
        return "Brand:" + getBrand() +
                "\nModel: " + getModel() +
                "\nSwitch type: " + switchType() +
                "\nWireless: " + wireless() +
                "\nPrice: RM" + getPrice();
    }
}