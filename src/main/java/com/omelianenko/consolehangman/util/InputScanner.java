package com.omelianenko.consolehangman.util;


import java.util.Scanner;

public class InputScanner {

    private final Scanner scanner;

    public InputScanner() {
        this.scanner = new Scanner(System.in);
    }

    public String startAndReadInput() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine().trim().toLowerCase();
        }
        return "";
    }

    public void closeInput() {
        scanner.close();
    }
}
