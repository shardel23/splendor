package main.java.business.entities;

import main.java.business.Exceptions.NotImplementedException;

import java.util.*;

public class Player {
    // TODO: implement player turn functions (buy card, take chips, reserve card)
    // TODO: implement getters (get wallet, get cards in hand, get reserved cards...) so player can review it's status

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

    public void buyCardFromHand() throws NotImplementedException {
        throw new NotImplementedException();
    }

    public void goldenCardFromDeck(Board board) throws NotImplementedException{
        throw new NotImplementedException();
    }

    public void goldenCardFromBoard(Board board) throws NotImplementedException{
        throw new NotImplementedException();
    }

    public void takeChips(Board board) throws NotImplementedException{
        throw new NotImplementedException();
    }

    public void chooseRoyal() throws NotImplementedException{
        throw new NotImplementedException();
    }
}
