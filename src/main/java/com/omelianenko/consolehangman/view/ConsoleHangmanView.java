package com.omelianenko.consolehangman.view;

public class ConsoleHangmanView implements HangmanView {

    @Override
    public void showStartMessage() {
        System.out.println("Игра началась! Угадайте загаданное слово");
    }

    @Override
    public void showCurrentHiddenLetters(String word) {
        System.out.println("Слово: " + word);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    @Override
    public void showAttemptsLeft(int attempts) {
        System.out.println("Попыток: " + attempts);
    }

    @Override
    public void showInputRequestMessage() {
        System.out.print("Введите букву или слово целиком: ");
    }

    @Override
    public void showEmptyInputError() {
        System.out.println("Ошибка: вы ничего не ввели. Введите букву или слово");
    }

    @Override
    public void showAlreadyGuessedLetterMessage() {
        System.out.println("Вы уже проверили эту букву");
    }

    @Override
    public void showLetterFoundMessage() {
        System.out.println("Есть такая буква");
    }

    @Override
    public void showLetterNotFoundMessage() {
        System.out.println("Нет такой буквы");
    }

    @Override
    public void showVictoryMessage(String word) {
        System.out.println("Победа! Вы угадали слово " + word);
    }

    @Override
    public void showIncorrectWordMessage() {
        System.out.println("Нет, это не то слово");
    }

    @Override
    public void showGameOverMessage(String word) {
        System.out.println("Game Over. У вас кончились попытки. Слово было " + word);
    }

    @Override
    public void showHangmanVisualStatus(int attemptsLeft) {
        if (attemptsLeft < 0 || attemptsLeft >= hangmanVisualStatus.length) {
            System.out.println("Не могу отобразить виселицу. Количество попыток не соответствует");
            return;
        }
        System.out.println(hangmanVisualStatus[attemptsLeft]);
    }

    private static final String[] hangmanVisualStatus = {
        """
         _____
         |   |
         0   |
        /|\\  |
        / \\  |
             |
        """,
        """
         _____
         |   |
         0   |
        /|\\  |
        /    |
             |
        """,
        """
         _____
         |   |
         0   |
        /|\\  |
             |
             |
        """,
        """
         _____
         |   |
         0   |
        /|   |
             |
             |
        """,
        """
         _____
         |   |
         0   |
         |   |
             |
             |
        """,
        """
         _____
         |   |
         0   |
             |
             |
             |
        """,
        """
         _____
         |   |
             |
             |
             |
             |
        """
    };
}
