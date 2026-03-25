package com.omelianenko.consolehangman;

import com.omelianenko.consolehangman.conroller.HangmanGameController;
import com.omelianenko.consolehangman.model.BasicWordSelector;
import com.omelianenko.consolehangman.model.DefaultWordCharChecker;
import com.omelianenko.consolehangman.model.WordCharChecker;
import com.omelianenko.consolehangman.model.WordSelector;
import com.omelianenko.consolehangman.util.ConsoleInputScanner;
import com.omelianenko.consolehangman.util.InputScanner;
import com.omelianenko.consolehangman.view.ConsoleHangmanView;
import com.omelianenko.consolehangman.view.HangmanView;

public class Main {

    public static void main(String[] args) {
        InputScanner inputScanner = new ConsoleInputScanner();
        WordCharChecker wordCharChecker = new DefaultWordCharChecker();
        WordSelector wordSelector = new BasicWordSelector();
        HangmanView hangmanView = new ConsoleHangmanView();


        HangmanGameController hangmanGameController = new HangmanGameController(
            inputScanner,
            wordCharChecker,
            wordSelector,
            hangmanView);
        hangmanGameController.startGame();
    }
}
