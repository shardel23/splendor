package main.java.business.entities;

import main.java.business.Utils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoyalTest {

    private static Wallet wallet;
    private static Royal canBuy;
    private static Royal canNotBuy;
    private static final String WALLET_CHIP_STR = "J1G3B3W3";
    private static final String WALLET_BONUS_STR = "K5R4";
    private static final String CAN_BUY_STR = "K4R4";
    private static final String CAN_NOT_BUY_STR = "G3B3W3";

    @BeforeAll
    public static void beforeAll() {
        wallet = Utils.w(WALLET_CHIP_STR, WALLET_BONUS_STR);
        canBuy = Utils.r(CAN_BUY_STR);
        canNotBuy = Utils.r(CAN_NOT_BUY_STR);
    }

    @Test
    void canGetRoyal() {
        assertTrue(canBuy.canGetRoyal(wallet));
        assertFalse(canNotBuy.canGetRoyal(wallet));
    }
}