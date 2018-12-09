package main.java.business.entities;

import main.java.business.Exceptions.EmptyDeckException;
import main.java.business.Level;

import java.util.*;

public class Board {
    private static final int MAX_OPEN_CARDS = 4;

    private Bank chipsBank;
    private Map<Level, List<Card>> openCards;
    private Map<Level, Deck> cardDecks;
    private List<Royal> royals;

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


}
