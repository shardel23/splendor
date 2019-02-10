package main.java.business.entities;

import main.java.business.Utils;
import main.java.business.enums.Color;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static main.java.business.Utils.p;
import static main.java.business.enums.Color.*;
import static main.java.business.enums.Level.*;
import static org.junit.jupiter.api.Assertions.*;

class WalletTest {

    private static Wallet wallet;
    private static Royal canBuyRoyal;
    private static Royal canNotBuyRoyal;
    private static Card canBuyCard;
    private static Card canNotBuyCard;
    private static final String WALLET_CHIP_STR = "J1G3B3W3";       // Red-4, Green-3, Blue-3, White-3. Black-5, Gold-1
    private static final String WALLET_BONUS_STR = "K5R4";
    private static final String CAN_BUY_ROYAL_STR = "K4R4";
    private static final String CAN_NOT_BUY_ROYAL_STR = "G3B3W3";
    private static final String CAN_BUY_CARD_STR = "B3G3R5K3";
    private static final String CAN_NOT_BUY_CARD_STR = "W6";

    @BeforeAll
    public static void beforeAll() {
        canBuyRoyal = Utils.r(CAN_BUY_ROYAL_STR);
        canNotBuyRoyal = Utils.r(CAN_NOT_BUY_ROYAL_STR);
        canBuyCard = Utils.c(THREE, RED, 3, p(CAN_BUY_CARD_STR));
        canNotBuyCard = Utils.c(TWO, WHITE, 3, p(CAN_NOT_BUY_CARD_STR));
    }

    @BeforeEach
    public void beforeEach () {
        wallet = Utils.w(WALLET_CHIP_STR, WALLET_BONUS_STR);
    }

    @Test
    void canBuyCard() {
        assertTrue(wallet.canBuyCard(canBuyCard));
        assertFalse(wallet.canBuyCard(canNotBuyCard));
    }

    @Test
    void canGetRoyal() {
        assertTrue(wallet.canGetRoyal(canBuyRoyal));
        assertFalse(wallet.canGetRoyal(canNotBuyRoyal));
    }

    @Test
    void pay() {
        int chipsCount = wallet.getTotalAmountOfChips();
        assertDoesNotThrow(() -> wallet.pay(canBuyCard.getPrice()));
        assertNotEquals(chipsCount, wallet.getTotalAmountOfChips());
        assertEquals(0, wallet.getChips(BLUE));
    }

    @Test
    void getTotal() {
        assertEquals(4, wallet.getTotal(RED));
        assertEquals(3, wallet.getTotal(WHITE));
        assertEquals(1, wallet.getTotal(GOLD));
    }

    @Test
    void getChips() {
        assertEquals(0, wallet.getChips(BLACK));
        assertEquals(3, wallet.getChips(WHITE));
        assertNotEquals(4, wallet.getChips(BLUE));
    }

    @Test
    void getBonus() {
        assertEquals(0, wallet.getBonus(GREEN));
        assertEquals(0, wallet.getBonus(WHITE));
        assertNotEquals(2, wallet.getBonus(RED));
    }

    @Test
    void addBonus() {
        int greenBonus = wallet.getBonus(GREEN);
        int greenChips = wallet.getChips(GREEN);
        int total = greenBonus + greenChips;
        int plusOne = 1;
        wallet.addBonus(GREEN, plusOne);
        assertEquals(greenChips, wallet.getChips(GREEN));
        assertEquals(greenBonus + plusOne, wallet.getBonus(GREEN));
        assertEquals(total + plusOne, wallet.getTotal(GREEN));
    }

    @Test
    void getTotalAmountOfChips() {
        int total = 0;
        for (Color color: Color.values()) {
            total += wallet.getChips(color);
        }
        assertEquals(total, wallet.getTotalAmountOfChips());
    }
}