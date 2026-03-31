package com.omelianenko.consolehangman.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FromFileWordSelector implements WordSelector {

    private final String resourcePath;
    private final Random random = new Random();

    @Override
    public String getRandomWord() {
        try (InputStream inputStream = getClass().getClassLoader()
            .getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new RuntimeException("Файл не найден в ресурсах: " + resourcePath);
            }

            List<String> words = new BufferedReader(new InputStreamReader(inputStream))
                .lines()
                .filter(line -> !line.trim().isEmpty())
                .toList();
            if (words.isEmpty()) {
                throw new RuntimeException("Файл пуст: " + resourcePath);
            }
            return words.get(random.nextInt(words.size()));

        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла: " + resourcePath, e);
        }
    }
}
