package main.java.business.entities;

import main.java.business.Utils;
import main.java.business.enums.Color;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BankTest {

    private static Bank emptyBank;
    private static Bank bankWithoutBlue;
    private static final String BANK_STR = "W2R1G4K3J5";

    @BeforeAll
    public static void beforeAll() {
        emptyBank = new Bank();
        bankWithoutBlue = Utils.b(BANK_STR);
    }

    @Test
    void getAmountOfColorEmptyBank(){
        int amount;
        for (Color color: Color.values()) {
            amount = emptyBank.getAmountOfColor(color);
            assertEquals(0, amount);
        }
    }
    @Test
    void getAmountOfColorNotInBank(){
        int amount = bankWithoutBlue.getAmountOfColor(Color.BLUE);
        assertEquals(0, amount);
    }
    @Test
    void getAmountOfColorInBank(){
        Map<Color, Integer> expectedAmounts = Utils.createAmountsMap(BANK_STR);
        for (Color color: Color.values()) {
            if (color == Color.BLUE) { continue; }
            assertEquals(expectedAmounts.get(color), bankWithoutBlue.getAmountOfColor(color));
        }
    }
}