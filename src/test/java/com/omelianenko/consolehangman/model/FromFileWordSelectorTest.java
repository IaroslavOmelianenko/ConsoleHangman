package com.omelianenko.consolehangman.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FromFileWordSelectorTest {

    private static final String VALID_RESOURCE = "test-words.txt";
    private static final String EMPTY_RESOURCE = "empty-file.txt";
    private static final String MISSING_RESOURCE = "missing-file.txt";

    @Test
    @DisplayName("Возвращает случайное слово из файла")
    void should_ReturnRandomWord_FromValidFile() {
        FromFileWordSelector selector = new FromFileWordSelector(VALID_RESOURCE);

        String word = selector.getRandomWord();

        List<String> sampleWords = Arrays.asList("apple", "banana", "orange");

        assertTrue(sampleWords.contains(word));
    }


    @Test
    @DisplayName("Выброс исключения, если файл пуст")
    void should_ThrowException_When_FileIsEmpty() throws IOException {
        FromFileWordSelector selector = new FromFileWordSelector(EMPTY_RESOURCE);

        RuntimeException thrown = assertThrows(
            RuntimeException.class,
            selector::getRandomWord
        );

        assertTrue(thrown.getMessage().contains("Файл пуст"));
    }


    @Test
    @DisplayName("Выброс исключения, если файла не существует")
    void should_ThrowException_When_FileDoesNotExist() {
        FromFileWordSelector selector = new FromFileWordSelector(MISSING_RESOURCE);

        RuntimeException thrown = assertThrows(
            RuntimeException.class,
            selector::getRandomWord
        );

        assertTrue(thrown.getMessage().contains("Файл не найден"));
    }


    @Test
    @DisplayName("Возвращает разные слова при многократном вызове")
    void should_ReturnDifferentWords_WhenCalledMultipleTimes() throws IOException {
        FromFileWordSelector selector = new FromFileWordSelector(VALID_RESOURCE);

        Set<String> results = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            results.add(selector.getRandomWord());
        }

        // Проверка, что возвращались разные слова
        assertTrue(results.size() > 1);

    }
}
