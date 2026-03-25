package com.omelianenko.consolehangman.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BasicWordSelector implements WordSelector {

    @Override
    public String getRandomWord() {

        List<String> words = new ArrayList<>();
        words.add("java");
        words.add("python");
        words.add("kotlin");
        words.add("javascript");

        Random random = new Random();

        return words.get(random.nextInt(words.size()));
    }
}
