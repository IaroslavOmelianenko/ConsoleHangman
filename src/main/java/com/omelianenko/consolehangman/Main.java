package com.omelianenko.consolehangman;

import com.omelianenko.consolehangman.conroller.HangmanGameController;

public class Main {

    public static void main(String[] args) {
        HangmanGameController hangmanGameController = new HangmanGameController();
        hangmanGameController.startGame();
    }
}
