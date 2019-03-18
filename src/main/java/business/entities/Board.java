package main.java.business.entities;

import main.java.business.GamePartsGenerator;
import main.java.business.enums.Level;
import main.java.business.exceptions.EmptyDeckException;

import java.util.*;

public class Board {
    // TODO: initialize (open first cards, royals, initialize bank)
    // TODO: implement functionality (take card, draw new card, reserve card, take royal...)
    // TODO: return copy or actual properties? (getCard, getOpenCards...)

    public static final int MAX_OPEN_CARDS = 4;

    private Bank chipsBank;
    private Map<Level, List<Card>> openCards;
    private Map<Level, Deck> cardDecks;
    private List<Royal> royals;

    public Board(int numOfPlayers) {
        cardDecks = GamePartsGenerator.getDecks();
        initializeOpenCards();
        List<Royal> royalsDeck = GamePartsGenerator.getAllRoyals();
        royals = randomizeXRoyals(numOfPlayers+1, royalsDeck);
        chipsBank = new Bank(numOfPlayers);
    }

    private List<Royal> randomizeXRoyals(int x, List<Royal> royalsDeck) {
        Random rng = new Random();
        List<Royal> randomizedRoyals = new ArrayList<>();
        for (int i=0; i<x; i++) {
            randomizedRoyals.add(royalsDeck.remove(rng.nextInt(royalsDeck.size())));
        }
        return randomizedRoyals;
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

    public Card takeCard(Level level, int index) {
        if (index < 1 || index > MAX_OPEN_CARDS) {
            throw new IndexOutOfBoundsException("Card position should be a number between 1 to " + MAX_OPEN_CARDS);
        }
        return getCard(level, index);
    }

    public Card getCard(Level level, int index) {
        int trueIndex = index - 1;
        List<Card> sameLevelOpenCards = openCards.get(level);
        Card card = sameLevelOpenCards.remove(trueIndex);
        drawAndPutOnBoard(level, trueIndex);
        return card;
    }

    private void drawAndPutOnBoard(Level level, int index) {
        try {
            Card newCard = cardDecks.get(level).draw();
            openCards.get(level).add(index, newCard);
        } catch (EmptyDeckException ignored) {

        }
    }


    public List<Royal> getRoyals() {
        return royals;
    }

    public Bank getBank() {
        return chipsBank;
    }

    public void setRoyals(List<Royal> royals) {
        this.royals = royals;
    }

    public Map<Level, Deck> getCardDecks() {
        return cardDecks;
    }
}
