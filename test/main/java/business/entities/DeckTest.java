package main.java.business.entities;

import main.java.business.Exceptions.EmptyDeckException;
import main.java.business.GamePartsGenerator;
import main.java.business.enums.Level;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    private static Map<Level, Deck> decks;
    private static Deck emptyDeck;

    @BeforeAll
    public static void beforeAll() {
        decks = GamePartsGenerator.getDecks();
        emptyDeck = new Deck();
    }

    @Test
    void isEmpty() {
        assertTrue(emptyDeck.isEmpty());
    }

    @Test
    void draw() throws EmptyDeckException {
        Map<Level, Deck> decks = GamePartsGenerator.getDecks();
        Deck deck1 = decks.get(Level.ONE);
        int originalDeckSize = deck1.size();
        Card c1 = deck1.draw();
        assertFalse(deck1.contains(c1));
        assertEquals(originalDeckSize-1, deck1.size());
        Card c2 = deck1.draw();
        assertNotEquals(c1, c2);
        assertThrows(EmptyDeckException.class, emptyDeck::draw);
    }
}