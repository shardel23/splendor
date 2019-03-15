package main.java.business.entities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ChipBonusPairTest {

    private static ChipBonusPair emptyPair;
    private static ChipBonusPair zeroChips;
    private static ChipBonusPair zeroBonus;

    @BeforeAll
    public static void beforeAll() {
        zeroChips = new ChipBonusPair(0, 4);
        zeroBonus = new ChipBonusPair(9,0);
    }

    @BeforeEach
    public void beforeEach() {
        emptyPair = new ChipBonusPair();
        assertEquals(0, emptyPair.getChips());
        assertEquals(0, emptyPair.getBonus());
        assertEquals(0, emptyPair.getTotal());
    }

    @Test
    void getTotal() {
        assertEquals(0, emptyPair.getTotal());
        assertEquals(4, zeroChips.getTotal());
        assertEquals(9, zeroBonus.getTotal());
    }

    @Test
    void getChips() {
        assertEquals(0, emptyPair.getChips());
        assertEquals(0, zeroChips.getChips());
        assertEquals(9, zeroBonus.getChips());
    }

    @Test
    void setChips() {
        int newChipsNum = 39;
        emptyPair.setChips(newChipsNum);
        assertEquals(newChipsNum, emptyPair.getChips());
        assertEquals(0, emptyPair.getBonus());
        assertEquals(newChipsNum, emptyPair.getTotal());
    }

    @Test
    void getBonus() {
        assertEquals(0, emptyPair.getBonus());
        assertEquals(4, zeroChips.getBonus());
        assertEquals(0, zeroBonus.getBonus());
    }

    @Test
    void setBonus() {
        int newBonusNum = 4;
        emptyPair.setBonus(newBonusNum);
        assertEquals(newBonusNum, emptyPair.getBonus());
        assertEquals(0, emptyPair.getChips());
        assertEquals(newBonusNum, emptyPair.getTotal());
    }

    @Test
    void addBonus() {
        int added = 4;
        emptyPair.addBonus(added);
        assertEquals(added, emptyPair.getBonus());
        assertEquals(0, emptyPair.getChips());
        assertEquals(added, emptyPair.getTotal());
        int substructed = -1;
        emptyPair.addBonus(substructed);
        assertEquals(added + substructed, emptyPair.getBonus());
        assertEquals(0, emptyPair.getChips());
        assertEquals(added + substructed, emptyPair.getTotal());
    }

    @Test
    void payChips() {
        int chipsAmount = 8;
        int amountToPay = 4;
        emptyPair.setChips(8);
        assertEquals(chipsAmount, emptyPair.getChips());
        emptyPair.payChips(amountToPay);
        assertEquals(chipsAmount - amountToPay, emptyPair.getChips());
        int currChips = emptyPair.getChips();
        emptyPair.payChips(currChips);
        assertEquals(0, emptyPair.getChips());
    }
}