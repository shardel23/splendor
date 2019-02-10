package main.java.business;

import main.java.business.entities.GameManager;

public class Driver {

    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.initialize(3);
        gameManager.startGame();
    }

}
