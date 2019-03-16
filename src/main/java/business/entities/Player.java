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
    // TODO: implement getters (get wallet, get cards in hand, get reserved cards...) so player can review it's status

    private static final int MAX_GOLDEN = 3;

    private String name;
    private int id;
    private Date dateOfBirth;

    private Wallet wallet;
    private List<Card> boughtCards;
    private List<Card> goldenCards;
    private int victoryPoints;
    private List<Royal> royalsAchieved;

    private void initializePlayerHand(){
        wallet = new Wallet();
        boughtCards = new ArrayList<>();
        goldenCards = new ArrayList<>(MAX_GOLDEN);
        victoryPoints = 0;
        royalsAchieved = new ArrayList<>();
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
        boughtCards.add(toBuy);
        wallet.increaseBonusByOne(toBuy.getColorBonus());
        board.takeCard(level, index);
    }

    public void buyCardFromHand(int index) throws CantPayPriceException {
        Card toBuy = goldenCards.get(index);
        Price cardPrice = toBuy.getPrice();

        wallet.pay(cardPrice);
        boughtCards.add(toBuy);
        wallet.increaseBonusByOne(toBuy.getColorBonus());

        goldenCards.remove(index);
    }

    public void reserveGoldenCardFromDeck(Board board, Level level) throws EmptyDeckException {
        Deck deck = board.getCardDecks().get(level);
        Card cardToGet = deck.draw();
        int goldenChipsTaken = board.getBank().takeChips(Color.GOLD, 1);
        goldenCards.add(cardToGet);
        wallet.addChips(Color.GOLD, goldenChipsTaken);
    }

    public void reserveGoldenCardFromBoard(Board board, Level level, int index) {
        Card toReserve = board.getCard(level, index);
        int goldenChipsTaken = board.getBank().takeChips(Color.GOLD, 1);
        goldenCards.add(toReserve);
        board.takeCard(level, index);
        wallet.addChips(Color.GOLD, goldenChipsTaken);
    }

    public void takeChips(Board board, String colors) throws NotImplementedException{
        throw new NotImplementedException();
    }

    public void chooseRoyal() throws NotImplementedException{
        throw new NotImplementedException();
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public String getName() {
        return name;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public List<Card> getBoughtCards() {
        return boughtCards;
    }

    public List<Card> getGoldenCards() {
        return goldenCards;
    }
}
