package main.java.business;

public class Driver {

    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.initialize(2);
        gameManager.startGame();
    }

}
