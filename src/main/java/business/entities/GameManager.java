package main.java.business.entities;

import main.java.view.CLIPrinter;
import main.java.view.SplendorPrinter;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameManager {

    private List<Player> players;
    private Board board;
    private SplendorPrinter printer;

    private int currentPlayerIndex;
    private Player activePlayer;
    private Scanner scanner = new Scanner(System.in);

    public GameManager() {
        printer = new CLIPrinter();
    }

    public void initialize(int numOfPlayers) {
        players = new ArrayList<>(numOfPlayers);
        initializePlayersList(numOfPlayers);
        board = new Board(numOfPlayers);
    }

    public void startGame() {
        while (!isGameOver()) {
            activePlayer = players.get(currentPlayerIndex);
            printer.printBoard(board);
            printer.printPlayers(players);
            waitForPlayersChoice();
            nextTurn();
        }
    }

    private void waitForPlayersChoice() {
        printer.printText(activePlayer.getName() + ", it's your turn.");
        printer.printText("Choose an action:");
        printer.printText("1. Take chips");
        printer.printText("2. Love Ayelet");
        printer.printText("3. Quit");
        int choice = scanner.nextInt();
    }

    private void nextTurn() {
        currentPlayerIndex++;
        currentPlayerIndex %= players.size();
    }

    private boolean isGameOver() {
        final int victoryPointsToWin = 15;
        for (Player player : players) {
            if (player.getVictoryPoints() >= victoryPointsToWin) {
                return true;
            }
        }
        return false;
    }

    private void initializePlayersList(int numOfPlayers) {
        for (int i=1; i<=numOfPlayers; i++) {
            players.add(new Player("player" + i, i, Date.valueOf(LocalDate.now())));
        }
    }
}
