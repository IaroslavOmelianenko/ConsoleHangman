package com.omelianenko.consolehangman.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class ResourceReader {

    public static List<String> readFromResource(String resourcePath) {
        try (InputStream inputStream = ResourceReader.class.getClassLoader()
            .getResourceAsStream(resourcePath)) {

            if (inputStream == null) {
                throw new RuntimeException("Файл не найден в ресурсах: " + resourcePath);
            }

            List<String> lines = new BufferedReader(new InputStreamReader(inputStream))
                .lines()
                .filter(line -> !line.trim().isEmpty())
                .toList();

            if (lines.isEmpty()) {
                throw new RuntimeException("Файл пуст: " + resourcePath);
            }
            return lines;

        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла: " + resourcePath, e);
        }
    }

}
