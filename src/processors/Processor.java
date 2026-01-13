// MIT License
// Copyright (c) 2025 Shah

package processors;

import products.*;

public interface Processor {
    public static boolean isInteger(String strInput) {
        if (strInput == null || strInput.isEmpty()) {
            return false;
        }
        for (int j = 0; j < strInput.length(); j++) {
            if (!Character.isDigit(strInput.charAt(j))) {
                return false;
            }
        }
        return true;
    }

    public static int getUsableArraySize(Product[] product) {
        int usableSize = 0;
        for (int i = 0; i < product.length; i++) {
            if (product[i] != null)
                usableSize++;
        }
        return usableSize;
    }
}
