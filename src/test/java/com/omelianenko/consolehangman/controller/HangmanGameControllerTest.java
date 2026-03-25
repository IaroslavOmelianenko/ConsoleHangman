package com.omelianenko.consolehangman.controller;

import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.omelianenko.consolehangman.model.WordCharChecker;
import com.omelianenko.consolehangman.model.WordSelector;
import com.omelianenko.consolehangman.util.InputScanner;
import com.omelianenko.consolehangman.view.HangmanView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HangmanGameControllerTest {

    @InjectMocks
    private HangmanGameController controller;

    @Mock
    private InputScanner inputScanner;

    @Mock
    private WordCharChecker wordCharChecker;

    @Mock
    private HangmanView hangmanView;

    @Mock
    private WordSelector wordSelector;


    @Test
    @DisplayName("Игрок угадал все буквы")
    void playerWinsByGuessingAllLetters() {
        when(wordSelector.getRandomWord()).thenReturn("apple");

        when(inputScanner.startAndReadInput())
            .thenReturn("a")
            .thenReturn("p")
            .thenReturn("l")
            .thenReturn("e");

        when(wordCharChecker.wordContainsChar("apple", 'a')).thenReturn(true);
        when(wordCharChecker.wordContainsChar("apple", 'p')).thenReturn(true);
        when(wordCharChecker.wordContainsChar("apple", 'l')).thenReturn(true);
        when(wordCharChecker.wordContainsChar("apple", 'e')).thenReturn(true);

        controller.startGame();

        verify(hangmanView).showVictoryMessage("apple");
        verify(inputScanner).closeInput();
    }


    @Test
    @DisplayName("Игрок угадал слово целиком")
    void playerWinsByGuessingWholeWord() {
        when(wordSelector.getRandomWord()).thenReturn("apple");

        when(inputScanner.startAndReadInput())
            .thenReturn("apple");

        when(wordCharChecker.wordIsEqual("apple", "apple")).thenReturn(true);

        controller.startGame();

        verify(hangmanView).showVictoryMessage("apple");
        verify(inputScanner).startAndReadInput();
    }


    @Test
    @DisplayName("Игрок проиграл истратив 6 попыток")
    void playerLosesAfterSixWrongAttempts() {
        when(wordSelector.getRandomWord()).thenReturn("apple");

        when(inputScanner.startAndReadInput())
            .thenReturn("b")
            .thenReturn("c")
            .thenReturn("d")
            .thenReturn("f")
            .thenReturn("g")
            .thenReturn("h");

        when(wordCharChecker.wordContainsChar(anyString(), anyChar())).thenReturn(false);

        controller.startGame();

        verify(hangmanView).showGameOverMessage("apple");
    }


    @Test
    @DisplayName("Игрок ничего не ввел и получил ошибку")
    void playerEntersEmptyInput() {
        when(wordSelector.getRandomWord()).thenReturn("apple");

        when(inputScanner.startAndReadInput())
            .thenReturn("")         // пустой ввод
            .thenReturn("apple"); // завершаем игру победой

        when(wordCharChecker.wordIsEqual("apple", "apple")).thenReturn(true);

        controller.startGame();

        verify(hangmanView).showEmptyInputError();
        verify(hangmanView).showVictoryMessage("apple");
    }


    @Test
    @DisplayName("Игрок повторно проверяет букву и получает ошибку")
    void playerRepeatsLetter() {
        when(wordSelector.getRandomWord()).thenReturn("apple");

        when(inputScanner.startAndReadInput())
            .thenReturn("a")
            .thenReturn("a")
            .thenReturn("apple"); // завершаем игру победой

        when(wordCharChecker.wordIsEqual("apple", "apple")).thenReturn(true);

        controller.startGame();

        verify(hangmanView).showAlreadyGuessedLetterMessage();
    }
}

