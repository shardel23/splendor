package main.java.business.entities;

import main.java.business.enums.Color;
import main.java.business.enums.Level;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GameManager {

    private List<Player> players;
    private Board board;

    private int currentPlayerIndex;
    private Player activePlayer;
    private Scanner scanner = new Scanner(System.in);

    public void initialize(int numOfPlayers) {
        players = new ArrayList<>(numOfPlayers);
        initializePlayersList(numOfPlayers);
        board = new Board(numOfPlayers);
    }

    public void startGame() {
        while (!isGameOver()) {
            activePlayer = players.get(currentPlayerIndex);
            showBoard();
            showPlayer(activePlayer);
            waitForPlayersChoice();
            nextTurn();
        }
    }

    private void waitForPlayersChoice() {
        System.out.println(activePlayer.getName() + ", it's your turn.");
        System.out.println("Choose an action:");
        System.out.println("1. Take chips");
        System.out.println("2. Love Ayelet");
        System.out.println("3. Quit");
        int choice = scanner.nextInt();
    }

    private void showPlayer(Player activePlayer) {
        System.out.println(activePlayer.getName() + " : ");
        System.out.println("Chips : ");
        System.out.println(activePlayer.getWallet());
        System.out.println("Cards bought : ");
        for (Card card : activePlayer.getCardsInHand()) {
            showCard(card);
        }
        System.out.println("Golden cards : ");
        for (Card card : activePlayer.getGoldenCards()) {
            showCard(card);
        }
    }

    private void showBoard() {
        showRoyals();
        showOpenCards();
        showBank();
        showOtherPlayers();
    }

    private void showBank() {
        Bank bank = board.getBank();
        System.out.println("Bank : ");
        for (Color color : Color.values()) {
            System.out.print(color + "-" + bank.getAmountOfColor(color) + " ");
        }
        System.out.println();
    }

    private void showOpenCards() {
        System.out.println("Open Cards : ");
        for (Level level : Level.values()) {
            System.out.println("Level " + level.name() + " : ");
            List<Card> cards = board.getOpenCards(level);
            for (Card card : cards) {
                showCard(card);
            }
            System.out.println();
        }
    }

    private void showCard(Card card) {
        System.out.print("VP-" + card.getPointsBonus() + " Bonus-" + card.getColorBonus() +
                " Price-");
        showPrice(card.getPrice());
        System.out.println();
    }

    private void showPrice(Price price) {
        Map<Color, Integer> colorToPrice = price.getColorToPrice();
        for (Color color : colorToPrice.keySet()) {
            int amount = colorToPrice.get(color);
            System.out.print(amount + color.getShortName());
        }
    }

    private void showRoyals() {
        List<Royal> royals = board.getRoyals();
        System.out.println("Royals : ");
        for (Royal royal : royals) {
            showRoyal(royal);
            System.out.print(" ");
        }
        System.out.println();
    }

    private void showRoyal(Royal royal) {
        showPrice(royal.getPrice());
    }

    private void showOtherPlayers() {
        for (int i=0; i<players.size(); i++) {
            if (i != currentPlayerIndex) {
                showPlayer(players.get(i));
            }
        }
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
