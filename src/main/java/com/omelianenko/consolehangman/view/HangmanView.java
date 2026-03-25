package com.omelianenko.consolehangman.view;

public interface HangmanView {

    void showStartMessage();

    void showCurrentHiddenLetters(String word);

    void showAttemptsLeft(int attempts);

    void showInputRequestMessage();

    void showEmptyInputError();

    void showAlreadyGuessedLetterMessage();

    void showLetterFoundMessage();

    void showLetterNotFoundMessage();

    void showVictoryMessage(String word);

    void showIncorrectWordMessage();

    void showGameOverMessage(String word);

    void showHangmanVisualStatus(int attemptsLeft);
}
