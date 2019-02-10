package main.java.business.entities;

import main.java.business.Exceptions.EmptyDeckException;
import main.java.business.GamePartsGenerator;
import main.java.business.Level;
import main.java.business.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private static final int MAX_OPEN_CARDS = 4;

    private Bank chipsBank;
    private Map<Level, List<Card>> openCards;
    private Map<Level, Deck> cardDecks;
    private List<Royal> royals;

    public Board(int numOfPlayers) {
        cardDecks = GamePartsGenerator.getDecks();
        initializeOpenCards();
        royals = new ArrayList<>();
        if (numOfPlayers == 2) {
            chipsBank = new Bank(Utils.createAmountsMap("W4G4B4R4K4J5"));
        }
        else if (numOfPlayers == 3) {
            chipsBank = new Bank(Utils.createAmountsMap("W5G5B5R5K5J5"));
        }
        else {
            chipsBank = new Bank(Utils.createAmountsMap("W7G7B7R7K7J5"));
        }
    }

    private void initializeOpenCards() {
        openCards = new HashMap<>();
        List<Card> level1Cards = new ArrayList<>();
        List<Card> level2Cards = new ArrayList<>();
        List<Card> level3Cards = new ArrayList<>();
        for (int i=0; i<4; i++) {
            try {
                level1Cards.add(cardDecks.get(Level.ONE).draw());
                level2Cards.add(cardDecks.get(Level.TWO).draw());
                level3Cards.add(cardDecks.get(Level.THREE).draw());
            }
            catch (EmptyDeckException emptyDeckException) {
                System.err.println("Decks should be full on start-up, but were empty...");
            }
        }
        openCards.put(Level.ONE, level1Cards);
        openCards.put(Level.TWO, level2Cards);
        openCards.put(Level.THREE, level3Cards);
    }

    private void initializeBoard(){
        chipsBank = new Bank();
        openCards = new HashMap<>();
        cardDecks = new HashMap<>();
        for (Level level: Level.values()) {
            openCards.put(level, new ArrayList<>());
            cardDecks.put(level, new Deck());
        }
        royals = new ArrayList<>();
    }

    public Board(){
        initializeBoard();
    }

    public List<Card> getOpenCards(Level level) {
        return new ArrayList<>(openCards.get(level));
    }

    public void takeCard(Card card) {
        Level level = card.getLevel();
        List<Card> sameLevelOpenCards = openCards.get(level);
        for (Card openCard : sameLevelOpenCards) {
            if (card.equals(openCard)) {
                int index = sameLevelOpenCards.indexOf(openCard);
                sameLevelOpenCards.remove(openCard);
                drawAndPutOnBoard(level, index);
            }
        }
    }

    private void drawAndPutOnBoard(Level level, int index) {
        try {
            Card newCard = cardDecks.get(level).draw();
            openCards.get(level).add(newCard);
        } catch (EmptyDeckException ignored) {

        }
    }


    public List<Royal> getRoyals() {
        return royals;
    }

    public Bank getBank() {
        return chipsBank;
    }
}
