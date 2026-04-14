package com.omelianenko.consolehangman;

import com.omelianenko.consolehangman.controller.HangmanGameController;
import com.omelianenko.consolehangman.model.FromFileWordSelector;
import com.omelianenko.consolehangman.model.WordSelector;
import com.omelianenko.consolehangman.util.ConsoleInputScanner;
import com.omelianenko.consolehangman.util.InputScanner;
import com.omelianenko.consolehangman.view.ConsoleHangmanView;
import com.omelianenko.consolehangman.view.HangmanView;

public class Main {

    public static void main(String[] args) {
        InputScanner inputScanner = new ConsoleInputScanner();
        //WordCharChecker wordCharChecker = new DefaultWordCharChecker();
        WordSelector wordSelector = new FromFileWordSelector("words.txt");
        HangmanView hangmanView = new ConsoleHangmanView();

        HangmanGameController hangmanGameController = new HangmanGameController(
            inputScanner,
            wordSelector,
            hangmanView);
        hangmanGameController.startGame();
    }
}
