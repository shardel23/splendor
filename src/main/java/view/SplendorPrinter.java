package main.java.view;

import main.java.business.entities.*;

import java.util.List;

public interface SplendorPrinter {

    void printBoard(Board board);

    void printCard(Card card);

    void printCards(List<Card> cards);

    void printBank(Bank bank);

    void printPlayer(Player player);

    void printPlayers(List<Player> players);

    void printPrice(Price price);

    void printWallet(Wallet wallet);

    void printRoyal(Royal royal);

    void printRoyals(List<Royal> royals);

    void printText(String text);

}
