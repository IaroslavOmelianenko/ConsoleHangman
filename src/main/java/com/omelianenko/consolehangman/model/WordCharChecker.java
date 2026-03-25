package com.omelianenko.consolehangman.model;

public interface WordCharChecker {

    boolean wordIsEqual(String targetWord, String wordToCheck);

    boolean wordContainsChar(String targetWord, char charToCheck);
}
