package main.java.business.entities;

import main.java.business.enums.Color;
import main.java.business.enums.Level;
import main.java.business.exceptions.EmptyDeckException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class PlayerTest {

    private Player player;
    private static Board board;

    @BeforeAll
    public static void beforeAll() {
        board = new Board(2);
    }

    @BeforeEach
    public void before() {
        player = new Player("Name", 100, new Date());
        for (Color color : Color.getBasicValues()) {
            player.getWallet().addChips(color, 1);
        }
    }

    @Test
    void buyCardFromBoard() {

    }

    @Test
    void buyCardFromHand() {
    }

    @Test
    void reserveGoldenCardFromDeck() {
        Level level = Level.THREE;
        Deck deck = board.getCardDecks().get(level);
        int originalDeckSize = deck.size();
        int originalGoldenChips = player.getWallet().getChips(Color.GOLD);
        try {
            player.reserveGoldenCardFromDeck(board, level);
        } catch (EmptyDeckException e) {
            fail("Deck should not be empty on startup");
        }
        assertEquals(originalDeckSize - 1, deck.size());
        assertEquals(originalGoldenChips + 1, player.getWallet().getChips(Color.GOLD));
    }

    @Test
    void reserveGoldenCardFromBoard() {
    }
}