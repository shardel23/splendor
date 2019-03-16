package main.java.business.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.java.business.enums.Color;
import main.java.business.enums.Level;
import main.java.business.exceptions.CantPayPriceException;
import main.java.business.exceptions.EmptyDeckException;
import main.java.business.exceptions.NotImplementedException;

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

    /*
    *   Play turn
    *       "choose action": 1. Buy Card    2. Take chips from bank     3. Reserve card
     */

    public void buyCardFromBoard(Board board, Level level, int index) throws CantPayPriceException{
        Card toBuy = board.getCard(level, index);
        Price cardPrice = toBuy.getPrice();

        wallet.pay(cardPrice);
        cardsInHand.add(toBuy);
        wallet.increaseBonusByOne(toBuy.getColorBonus());
        board.takeCard(level, index);
    }

    public void buyCardFromHand(int index) throws CantPayPriceException {
        Card toBuy = goldenCards.get(index);
        Price cardPrice = toBuy.getPrice();

        wallet.pay(cardPrice);
        cardsInHand.add(toBuy);
        wallet.increaseBonusByOne(toBuy.getColorBonus());

        goldenCards.remove(index);
    }

    public void goldenCardFromDeck(Board board, Level level) throws EmptyDeckException {
        Deck deck = board.getCardDecks().get(level);
        Card cardToGet = deck.draw();
        int chipsTaken = board.getBank().takeChips(Color.GOLD, 1);
        goldenCards.add(cardToGet);
        wallet.addChips(Color.GOLD, chipsTaken);
    }

    public void goldenCardFromBoard(Board board) throws NotImplementedException{
        throw new NotImplementedException();
    }

    public void takeChips(Board board, String colors) throws NotImplementedException{
        throw new NotImplementedException();
    }

    public void chooseRoyal() throws NotImplementedException{
        throw new NotImplementedException();
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
