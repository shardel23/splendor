package main.java.business;

import main.java.business.entities.Deck;
import main.java.business.enums.Level;
import org.junit.jupiter.api.Test;

import java.util.Map;

class GamePartsGeneratorTest {

    @Test
    void getDecks() {
        Map<Level, Deck> decks = GamePartsGenerator.getDecks();
        Deck deck1 = decks.get(Level.ONE);
        Deck deck2 = decks.get(Level.TWO);
        Deck deck3 = decks.get(Level.THREE);

    }

}