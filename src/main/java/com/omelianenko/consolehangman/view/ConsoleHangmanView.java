package com.omelianenko.consolehangman.view;

public class ConsoleHangmanView {

    public void showStartMessage() {
        System.out.println("Игра началась! Угадайте загаданное слово");
    }

    public void showCurrentHiddenLetters(String word) {
        System.out.println("Слово: " + word);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void showAttemptsLeft(int attempts) {
        System.out.println("Попыток: " + attempts);
    }

    public void showInputRequestMessage() {
        System.out.print("Введите букву или слово целиком: ");
    }

    public void showEmptyInputError() {
        System.out.println("Ошибка: вы ничего не ввели. Введите букву или слово");
    }

    public void showAlreadyGuessedLetterMessage() {
        System.out.println("Вы уже проверили эту букву");
    }

    public void showLetterFoundMessage() {
        System.out.println("Есть такая буква");
    }

    public void showLetterNotFoundMessage() {
        System.out.println("Нет такой буквы");
    }

    public void showVictoryMessage(String word) {
        System.out.println("Победа! Вы угадали слово " + word);
    }

    public void showIncorrectWordMessage() {
        System.out.println("Нет, это не то слово");
    }

    public void showGameOverMessage(String word) {
        System.out.println("Game Over. У вас кончились попытки. Слово было " + word);
    }

    public void showHangmanVisualStatus(int attemptsLeft){
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
