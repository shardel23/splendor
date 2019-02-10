package main.java.business.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Player {

    private static final int MAX_GOLDEN = 3;

    private String name;
    private int id;
    private Date dateOfBirth;

    private Wallet wallet;
    private List<Card> cardsInHand;
    private List<Card> goldenCards;
    private Integer points;
    private List<Royal> royalsInHand;

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

    public int getVictoryPoints() {
        // TODO: Implement
        return -1;
    }

    public String getName() {
        return name;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public List<Card> getCardsInHand() {
        return cardsInHand;
    }

    public List<Card> getGoldenCards() {
        return goldenCards;
    }
}
