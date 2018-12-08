package main.java.business.entities;

import main.java.business.Level;

import java.util.*;

public class Board {
    private static int MAX_OPEN_CARDS = 4;

    ColorToAmount chipsInBank;
    Map<Level, List<Card>> openCards;
    Map<Level, Deck> cardDecks;
    List<Royal> royals;

    private void initializeBoard(){
        chipsInBank = new ColorToAmount();
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
}
