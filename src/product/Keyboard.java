// MIT License
// Copyright (c) 2025 Marzell

package product;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Keyboard extends Product {
    private String switchType;
    private boolean isWireless;

    public Keyboard() {
        super();
        this.switchType = "";
        this.isWireless = false;
    }

    public Keyboard(String brand, String model, double price, String switchType, boolean isWireless) {
        super(brand, model, price);
        this.switchType = switchType;
        this.isWireless = isWireless;
    }

    public Keyboard(Keyboard other) {
        super(other);
        this.switchType = other.switchType;
        this.isWireless = other.isWireless;
    }

    public void setSwitchType(String switchType) {
        this.switchType = switchType;
    }

    public void setWireless(boolean wireless) {
        this.isWireless = wireless;
    }

    public String getSwitchType() {
        return switchType;
    }

    public boolean getWireless() {
        return isWireless;
    }

    @Override
    public void load(File inputFile, int recordPosition) {
        try {
            Scanner inputFileReader = new Scanner(inputFile);

            for (int field = 0; field < recordPosition; field++) {
                inputFileReader.nextLine();
            }

            if (inputFileReader.hasNextLine()) {
                String aRecord = inputFileReader.nextLine();
                StringTokenizer token = new StringTokenizer(aRecord, ";");

                setBrand(token.nextToken());
                setModel(token.nextToken());
                setPrice(Double.parseDouble(token.nextToken()));
                setSwitchType(token.nextToken());
                setWireless(Boolean.parseBoolean(token.nextToken()));
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
        return String.format("%s;%s;%.2f;%s;%b", brand, model, price, switchType, isWireless);
    }

    @Override
    public String toString() {
        return "Brand: " + brand +
               "\nModel: " + model +
               "\nSwitch type: " + switchType +
               "\nWireless: " + isWireless +
               "\nPrice: RM" + price;
    }

    @Override
    public String toShortString() {
        return brand + " " + model + "\t\tRM" + price;
    }
}
