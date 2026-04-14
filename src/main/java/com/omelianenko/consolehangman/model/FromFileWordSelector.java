package com.omelianenko.consolehangman.model;

import com.omelianenko.consolehangman.util.ResourceReader;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FromFileWordSelector implements WordSelector {

    private final String resourcePath;
    private final Random random = new Random();

    @Override
    public String getRandomWord() {
        List<String> words = ResourceReader.readFromResource(resourcePath);
        return words.get(random.nextInt(words.size()));
    }
}
