package main.java.view;

import main.java.business.entities.*;
import main.java.business.enums.Color;
import main.java.business.enums.Level;

import java.util.List;
import java.util.Map;

public class CLIPrinter implements SplendorPrinter {
    @Override
    public void printBoard(Board board) {
        System.out.println("Royals : ");
        printRoyals(board.getRoyals());
        System.out.println("Open Cards : ");
        for (Level level : Level.values()) {
            System.out.println("Level " + level.name() + " : ");
            printCards(board.getOpenCards(level));
            System.out.println();
        }
        System.out.println("Bank : ");
        printBank(board.getBank());
    }

    @Override
    public void printCard(Card card) {
        System.out.print("VP-" + card.getPointsBonus() + " Bonus-" + card.getColorBonus() +
                " Price-");
        printPrice(card.getPrice());
        System.out.println();
    }

    @Override
    public void printCards(List<Card> cards) {
        for (Card card : cards) {
            printCard(card);
        }
    }

    @Override
    public void printBank(Bank bank) {
        for (Color color : Color.values()) {
            System.out.print(color + "-" + bank.getAmountOfColor(color) + " ");
        }
        System.out.println();
    }

    @Override
    public void printPlayer(Player player) {
        System.out.println(player.getName() + " : ");
        System.out.println("Chips : ");
        printWallet(player.getWallet());
        System.out.println("Cards bought : ");
        printCards(player.getCardsInHand());
        System.out.println("Golden cards : ");
        printCards(player.getGoldenCards());
    }

    @Override
    public void printPlayers(List<Player> players) {
        for (Player player : players) {
            printPlayer(player);
        }
    }

    @Override
    public void printPrice(Price price) {
        Map<Color, Integer> colorToPrice = price.getColorToPrice();
        for (Color color : colorToPrice.keySet()) {
            int amount = colorToPrice.get(color);
            System.out.print(amount + color.getShortName());
        }
    }

    @Override
    public void printWallet(Wallet wallet) {
        // TODO: Implement
    }

    @Override
    public void printRoyal(Royal royal) {
        printPrice(royal.getPrice());
    }

    @Override
    public void printRoyals(List<Royal> royals) {
        for (Royal royal : royals) {
            printRoyal(royal);
            System.out.print(" ");
        }
        System.out.println();
    }

    @Override
    public void printText(String text) {
        System.out.println(text);
    }
}
