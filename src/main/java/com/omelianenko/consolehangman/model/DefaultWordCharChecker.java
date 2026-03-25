package com.omelianenko.consolehangman.model;

public class DefaultWordCharChecker implements WordCharChecker {

    @Override
    public boolean wordIsEqual(
        String targetWord,
        String wordToCheck
    ) {
        return targetWord.equals(wordToCheck);
    }

    @Override
    public boolean wordContainsChar(
        String targetWord,
        char charToCheck
    ) {
        return targetWord.indexOf(charToCheck) != -1;
    }
}
