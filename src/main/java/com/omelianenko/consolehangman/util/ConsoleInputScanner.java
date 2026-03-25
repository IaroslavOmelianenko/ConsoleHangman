package com.omelianenko.consolehangman.util;


import java.util.Scanner;

public class ConsoleInputScanner implements InputScanner {

    private final Scanner scanner;

    public ConsoleInputScanner() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String startAndReadInput() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine().trim().toLowerCase();
        }
        return "";
    }

    @Override
    public void closeInput() {
        scanner.close();
    }
}
