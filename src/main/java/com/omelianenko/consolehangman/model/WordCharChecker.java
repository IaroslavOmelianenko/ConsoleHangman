package com.omelianenko.consolehangman.model;

public class WordCharChecker {

    public boolean wordIsEqual (
        String targetWord,
        String wordToCheck
    ){
        return targetWord.equals(wordToCheck);
    }

    public boolean wordContainsChar (
        String targetWord,
        char charToCheck
    ){
        return targetWord.indexOf(charToCheck) != -1;
    }
}
