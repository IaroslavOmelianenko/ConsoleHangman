package com.omelianenko.consolehangman.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.omelianenko.consolehangman.model.WordSelector;
import com.omelianenko.consolehangman.util.InputScanner;
import com.omelianenko.consolehangman.view.HangmanView;
import java.util.Optional;
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
    private HangmanView hangmanView;

    @Mock
    private WordSelector wordSelector;


    @Test
    @DisplayName("Игрок угадал все буквы")
    void playerWinsByGuessingAllLetters() {
        when(wordSelector.getRandomWord()).thenReturn("apple");

        when(inputScanner.startAndReadInput())
            .thenReturn(Optional.of("a"))
            .thenReturn(Optional.of("p"))
            .thenReturn(Optional.of("l"))
            .thenReturn(Optional.of("e"));

        controller.startGame();

        verify(hangmanView).showVictoryMessage("apple");
        verify(inputScanner).closeInput();
    }


    @Test
    @DisplayName("Игрок угадал слово целиком")
    void playerWinsByGuessingWholeWord() {
        when(wordSelector.getRandomWord()).thenReturn("apple");

        when(inputScanner.startAndReadInput())
            .thenReturn(Optional.of("apple"));

        controller.startGame();

        verify(hangmanView).showVictoryMessage("apple");
        verify(inputScanner).startAndReadInput();
    }


    @Test
    @DisplayName("Игрок проиграл истратив 6 попыток")
    void playerLosesAfterSixWrongAttempts() {
        when(wordSelector.getRandomWord()).thenReturn("apple");

        when(inputScanner.startAndReadInput())
            .thenReturn(Optional.of("b"))
            .thenReturn(Optional.of("c"))
            .thenReturn(Optional.of("d"))
            .thenReturn(Optional.of("f"))
            .thenReturn(Optional.of("g"))
            .thenReturn(Optional.of("h"));

        controller.startGame();

        verify(hangmanView).showGameOverMessage("apple");
    }


    @Test
    @DisplayName("Игрок ничего не ввел и получил ошибку")
    void playerEntersEmptyInput() {
        when(wordSelector.getRandomWord()).thenReturn("apple");

        when(inputScanner.startAndReadInput())
            .thenReturn(Optional.empty())         // пустой ввод
            .thenReturn(Optional.of("apple")); // завершаем игру победой

        controller.startGame();

        verify(hangmanView).showEmptyInputError();
        verify(hangmanView).showVictoryMessage("apple");
    }


    @Test
    @DisplayName("Игрок повторно проверяет букву и получает ошибку")
    void playerRepeatsLetter() {
        when(wordSelector.getRandomWord()).thenReturn("apple");

        when(inputScanner.startAndReadInput())
            .thenReturn(Optional.of("a"))
            .thenReturn(Optional.of("a"))
            .thenReturn(Optional.of("apple")); // завершаем игру победой

        controller.startGame();

        verify(hangmanView).showAlreadyGuessedLetterMessage();
    }
}

