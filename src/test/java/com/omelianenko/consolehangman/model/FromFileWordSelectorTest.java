package com.omelianenko.consolehangman.model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FromFileWordSelectorTest {

    @TempDir
    Path tempDir;

    private Path testFile;

    @BeforeEach
    void setUp() throws IOException {
        testFile = tempDir.resolve("words.txt");
    }


    @Test
    @DisplayName("Возвращает случайное слово из файла")
    void should_ReturnRandomWord_FromValidFile() throws IOException {
        List<String> sampleWords = Arrays.asList("apple", "banana", "orange");
        Files.write(testFile, sampleWords);

        FromFileWordSelector selector = new FromFileWordSelector(testFile.toString());

        String word = selector.getRandomWord();

        assertTrue(sampleWords.contains(word));
    }


    @Test
    @DisplayName("Выброс исключения, если файл пуст")
    void should_ThrowException_When_FileIsEmpty() throws IOException {
        // Подготовка
        Files.createFile(testFile); // создаём пустой файл

        FromFileWordSelector selector = new FromFileWordSelector(testFile.toString());

        // Действие и проверка
        RuntimeException thrown = assertThrows(
            RuntimeException.class,
            selector::getRandomWord
        );

        assertTrue(thrown.getMessage().contains("Файл пуст"));
    }


    @Test
    @DisplayName("Выброс исключения, если файла не существует")
    void should_ThrowException_When_FileDoesNotExist() {
        FromFileWordSelector selector = new FromFileWordSelector("nonexistent.txt");

        // Действие и проверка
        RuntimeException thrown = assertThrows(
            RuntimeException.class,
            selector::getRandomWord
        );

        assertTrue(thrown.getMessage().contains("Ошибка чтения файла"));
        assertNotNull(thrown.getCause());
    }


    @Test
    @DisplayName("Возвращает разные слова при многократном вызове")
    void should_ReturnDifferentWords_WhenCalledMultipleTimes() throws IOException {
        // Подготовка
        List<String> words = Arrays.asList("apple", "banana", "orange");
        Files.write(testFile, words);

        FromFileWordSelector selector = new FromFileWordSelector(testFile.toString());

        Set<String> results = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            results.add(selector.getRandomWord());
        }

        // Проверка, что возвращались разные слова
        assertTrue(results.size() > 1);
    }


}
