package com.omelianenko.consolehangman.model;

import java.util.List;
import java.util.Random;

public class BasicWordSelector implements WordSelector {

    private final Random random = new Random();
    private static final List<String> words = List.of(
        "java",
        "python",
        "kotlin",
        "javascript"
    );

    @Override
    public String getRandomWord() {
        return words.get(random.nextInt(words.size()));
    }
}
