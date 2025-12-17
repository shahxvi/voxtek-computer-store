// MIT License
// Copyright (c) 2025 Shah

import java.util.Scanner;

public class KeyboardInput {
    private static Scanner keyboard;

    public static int getValidInteger(int floor, int ceiling) {
        keyboard = new Scanner(System.in);
        int input;

        input = keyboard.nextInt();
        keyboard.nextLine();

        while (input < floor || input > ceiling) {
            System.out.print("Please enter a valid input (" + floor + " - " + ceiling + "): ");
            input = keyboard.nextInt();
            keyboard.nextLine();
        }

        keyboard.close();
        return input;
    }

    public static char getValidCharacter() {
        keyboard = new Scanner(System.in);
        char input;

        input = keyboard.next().charAt(0);
        keyboard.nextLine();
        input = Character.toUpperCase(input);

        boolean isYes = input == 'Y';
        boolean isNo = input == 'N';

        while (!isYes || !isNo) {
            System.out.print("Please enter a valid input (Y / N): ");
            input = keyboard.next().charAt(0);
            keyboard.nextLine();
            input = Character.toUpperCase(input);
        }

        keyboard.close();
        return input;
    }

    public static int getIntegerOnly() {
        keyboard = new Scanner(System.in);
        String strInput;

        int integerOnly;
        boolean isValid = false;

        do {
            strInput = keyboard.nextLine();

            for (int i = 0; i < strInput.length(); i++) {
                if (!Character.isDigit(strInput.charAt(i))) {
                    isValid = false;
                    break;
                }
            }

            if (!isValid) {
                System.out.print("Please enter valid integer: ");
            }
        } while (!isValid);

        keyboard.close();
        integerOnly = Integer.parseInt(strInput);

        return integerOnly;
    }
}
