package com.omelianenko.consolehangman.conroller;

import com.omelianenko.consolehangman.util.InputScanner;
import com.omelianenko.consolehangman.model.WordCharChecker;
import com.omelianenko.consolehangman.model.WordSelector;
import com.omelianenko.consolehangman.view.ConsoleHangmanView;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HangmanGameController {

    public void startGame() {
        WordSelector wordSelector = new WordSelector();
        InputScanner inputScanner = new InputScanner();
        WordCharChecker wordCharChecker = new WordCharChecker();
        ConsoleHangmanView consoleView = new ConsoleHangmanView();

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
        consoleView.showStartMessage();
        consoleView.showCurrentHiddenLetters(new String(hiddenChars));
        consoleView.showHangmanVisualStatus(attemptsLeft);


        while (gameInProgress && attemptsLeft > 0){
            consoleView.showAttemptsLeft(attemptsLeft);
            consoleView.showInputRequestMessage();

            String input = inputScanner.startAndReadInput();

            //Проверяем ввод на пустоту
            if (input.isEmpty()) {
                consoleView.showEmptyInputError();
                continue;
            }

            //Проверка одноной буквы
            if (input.length() == 1) {
                char character = input.charAt(0);

                //Проверка, что буква еще не вводилась
                if (!checkedChars.add(character)) {
                    consoleView.showAlreadyGuessedLetterMessage();
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
                    consoleView.showLetterFoundMessage();
                } else {
                    consoleView.showLetterNotFoundMessage();
                    attemptsLeft--;
                }

            //Если введен не 1 символ, значит проверяем слово целиком
            } else {
               if (wordCharChecker.wordIsEqual(targetWord, input)) {
                   gameInProgress = false;
                   consoleView.showVictoryMessage(targetWord);
                   continue;
               } else {
                   consoleView.showIncorrectWordMessage();
                   attemptsLeft--;

               }
            }

            //Выводим итоге текущего раунда
            String currentHiddenChars = new String(hiddenChars);
            consoleView.showHangmanVisualStatus(attemptsLeft);
            consoleView.showCurrentHiddenLetters(currentHiddenChars);

            //Проверяем остались ли спрятанные буквы
            if (!currentHiddenChars.contains("-")){
                gameInProgress = false;
                consoleView.showVictoryMessage(targetWord);
            }
        }

        if (attemptsLeft == 0) {
            consoleView.showGameOverMessage(targetWord);
        }

        inputScanner.closeInput();
    }

}
