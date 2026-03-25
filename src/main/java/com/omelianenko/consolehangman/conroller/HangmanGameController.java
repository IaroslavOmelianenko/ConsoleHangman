package com.omelianenko.consolehangman.conroller;

import com.omelianenko.consolehangman.model.WordCharChecker;
import com.omelianenko.consolehangman.model.WordSelector;
import com.omelianenko.consolehangman.util.InputScanner;
import com.omelianenko.consolehangman.view.HangmanView;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HangmanGameController {

    private final InputScanner inputScanner;
    private final WordCharChecker wordCharChecker;
    private final WordSelector wordSelector;
    private final HangmanView hangmanView;

    public HangmanGameController(
        InputScanner inputScanner,
        WordCharChecker wordCharChecker,
        WordSelector wordSelector,
        HangmanView hangmanView) {
        this.inputScanner = inputScanner;
        this.wordCharChecker = wordCharChecker;
        this.wordSelector = wordSelector;
        this.hangmanView = hangmanView;
    }

    public void startGame() {

        //Получаем слово
        String targetWord = wordSelector.getRandomWord();

        //Прячем слово
        char[] hiddenChars = new char[targetWord.length()];
        Arrays.fill(hiddenChars, '-');

        //Сет для проверки уже введенных символов
        Set<Character> checkedChars = new HashSet<>();

        boolean gameInProgress = true;
        int attemptsLeft = 6;

        //Даём сообщение о старте
        hangmanView.showStartMessage();
        hangmanView.showCurrentHiddenLetters(new String(hiddenChars));
        hangmanView.showHangmanVisualStatus(attemptsLeft);

        while (gameInProgress && attemptsLeft > 0) {
            hangmanView.showAttemptsLeft(attemptsLeft);
            hangmanView.showInputRequestMessage();

            String input = inputScanner.startAndReadInput();

            //Проверяем ввод на пустоту
            if (input.isEmpty()) {
                hangmanView.showEmptyInputError();
                continue;
            }

            //Проверка одноной буквы
            if (input.length() == 1) {
                char character = input.charAt(0);

                //Проверка, что буква еще не вводилась
                if (!checkedChars.add(character)) {
                    hangmanView.showAlreadyGuessedLetterMessage();
                    continue;
                }

                //Проверяем, что буква есть в слове
                if (wordCharChecker.wordContainsChar(targetWord, character)) {
                    //Открываем букву в спрятанном слове
                    for (int i = 0; i < targetWord.length(); i++) {
                        if (targetWord.charAt(i) == character) {
                            hiddenChars[i] = character;
                        }
                    }
                    hangmanView.showLetterFoundMessage();
                } else {
                    hangmanView.showLetterNotFoundMessage();
                    attemptsLeft--;
                }

                //Если введен не 1 символ, значит проверяем слово целиком
            } else {
                if (wordCharChecker.wordIsEqual(targetWord, input)) {
                    gameInProgress = false;
                    hangmanView.showVictoryMessage(targetWord);
                    continue;
                } else {
                    hangmanView.showIncorrectWordMessage();
                    attemptsLeft--;

                }
            }

            //Выводим итоге текущего раунда
            String currentHiddenChars = new String(hiddenChars);
            hangmanView.showHangmanVisualStatus(attemptsLeft);
            hangmanView.showCurrentHiddenLetters(currentHiddenChars);

            //Проверяем остались ли спрятанные буквы
            if (!currentHiddenChars.contains("-")) {
                gameInProgress = false;
                hangmanView.showVictoryMessage(targetWord);
            }
        }

        if (attemptsLeft == 0) {
            hangmanView.showGameOverMessage(targetWord);
        }

        inputScanner.closeInput();
    }

}
