package main.java.business.entities;

import main.java.business.Color;

import java.util.*;

public class Player {

    private static int MAX_GOLDEN = 3;

    String name;
    int id;
    Date dateOfBirth;

    Wallet wallet;
    List<Card> cardsInHand;
    List<Card> goldenCards;
    Integer points;
    List<Royal> royalsInHand;

    private void initializePlayerHand(){
        wallet = new Wallet();
        cardsInHand = new ArrayList<>();
        goldenCards = new ArrayList<>(MAX_GOLDEN);
        points = 0;
        royalsInHand = new ArrayList<>();
    }

    public Player(String name, int id, Date Bday){
        this.name = name;
        this.id = id;
        dateOfBirth = Bday;
        initializePlayerHand();
    }

    public void buyCardFromBoard(Board board, Card toBuy) {
        wallet.pay(toBuy.getPrice());
        board.takeCard(toBuy);
    }

    public void buyCardFromHand() {

    }

    public void goldenCardFromDeck(Board board) {

    }

    public void goldenCardFromBoard(Board board) {

    }

    public void takeChips(Board board) {

    }

    public void chooseRoyal() {

    }
}
