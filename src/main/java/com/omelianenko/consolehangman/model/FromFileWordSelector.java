package com.omelianenko.consolehangman.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class FromFileWordSelector implements WordSelector{

    private final String filePath;

    public FromFileWordSelector(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getRandomWord() {
        try {
            List<String> words = Files.readAllLines(Paths.get(filePath));
            if (words.isEmpty()) {
                throw new RuntimeException("Файл пуст: " + filePath);
            }

            Random random = new Random();
            return words.get(random.nextInt(words.size()));

        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла: " + filePath, e);
        }
    }
}
