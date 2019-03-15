package main.java.business.entities;

import main.java.business.enums.Level;
import main.java.business.exceptions.EmptyDeckException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class BoardTest {

    private static final Level level = Level.ONE;

    private static Board board;
    private static List<Card> openCards;

    @BeforeAll
    public static void beforeAll() throws EmptyDeckException {
        board = new Board(2);
        openCards = board.getOpenCards(level);
    }

    @Test
    void takeCard() {
        int index = 2;
        assertEquals(Board.MAX_OPEN_CARDS, openCards.size());
        Card cardToTake = openCards.get(1);
        Card takenCard = board.takeCard(level, index);
        assertEquals(cardToTake, takenCard);

        List<Card> newOpenCards = board.getOpenCards(level);
        assertEquals(Board.MAX_OPEN_CARDS, newOpenCards.size());
        assertNotEquals(openCards, newOpenCards);
        Card anotherTakenCard = board.takeCard(level, index);

        assertNotEquals(takenCard, anotherTakenCard);
    }
}