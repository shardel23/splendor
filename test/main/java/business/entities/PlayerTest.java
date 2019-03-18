package main.java.business.entities;

import main.java.business.Utils;
import main.java.business.enums.Color;
import main.java.business.exceptions.IllegalTakeChipsException;
import main.java.business.exceptions.MissingChipsInBankException;
import main.java.business.exceptions.MoreThanTenChipsException;
import main.java.business.enums.Color;
import main.java.business.enums.Level;
import main.java.business.exceptions.EmptyDeckException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Map;

import static main.java.business.Utils.createAmountsMap;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    // initialize wallet with 10 chips
    // initialize wallet with 7 chips
    // initialize b without blue chips and 3 green chips
    // initialize take chips strings

    private static Board board;
    private static Bank bank;
    private static Player unfull_player;
    private static final String UNFULL_WALLET_STR = "K2J1B1G2";
    private static final String BANK_CONTENT = "J1G3W5R5K5";

    //Shahar
    private Player player;

    @BeforeAll
    public static void beforeAll() {
        bank = Utils.b(BANK_CONTENT);
    }

    @BeforeEach
    public void beforeEach() {
        unfull_player = setPlayer(UNFULL_WALLET_STR);
        board = setBoard(bank);
        player = new Player("Name", 100, new Date());
        for (Color color : Color.getBasicValues()) {
            player.getWallet().addChips(color, 1);
        }
    }

    private static Player setPlayer(String walletStr) {
        Map<Color, Integer> chipsAmounts = createAmountsMap(walletStr);
        Player player = new Player("Joey", 1234, new Date());
        Wallet walletOfPlayer = player.getWallet();
        for (Map.Entry<Color, Integer> colorAmountPair : chipsAmounts.entrySet()) {
            walletOfPlayer.addChips(colorAmountPair.getKey(), colorAmountPair.getValue());
        }
        return player;
    }

    private static Board setBoard(Bank bank) {
        int numOfPlayers = 4;
        Board board = new Board(numOfPlayers);
        Bank bankOfBoard = board.getBank();
        for (Color color : Color.values()) {
            try {
                bankOfBoard.takeChips(color, bankOfBoard.getAmountOfColor(color) - bank.getAmountOfColor(color));
            } catch (MissingChipsInBankException e) {
                System.err.println(e.getMessage());
            }
        }
        return board;
    }

    private static void validateBankSuccess(String takeActionString, Bank beforeBank, Bank afterBank) {
        Map<Color, Integer> colorAmountMap = Utils.createAmountsMap(takeActionString);
        for (Color color : Color.values()) {
            if (colorAmountMap.containsKey(color)) {
                assertEquals(beforeBank.getAmountOfColor(color) - colorAmountMap.get(color), afterBank.getAmountOfColor(color));
            } else {
                assertEquals(beforeBank.getAmountOfColor(color), afterBank.getAmountOfColor(color));
            }
        }
    }

    private static void validateWalletSuccess(String takeActionString, Wallet beforeWallet, Wallet afterWallet) {
        Map<Color, Integer> colorAmountMap = Utils.createAmountsMap(takeActionString);
        for (Color color : Color.values()) {
            if (colorAmountMap.containsKey(color)) {
                assertEquals(beforeWallet.getChips(color) + colorAmountMap.get(color), unfull_player.getWallet().getChips(color));
            } else {
                assertEquals(beforeWallet.getChips(color), unfull_player.getWallet().getChips(color));
            }
        }
    }

    @Test
    public void moreThanTenChips() {
        String FullWalletStr = "K2J1B5G2";
        Player full_player = setPlayer(FullWalletStr);
        String legalTakeOneChip = "G1";
        String legalTakeTwoChipsSameColor = "W2";
        String legalTakeThreeChips = "W1K1R1";
        assertThrows(MoreThanTenChipsException.class, () -> full_player.takeChips(board, legalTakeThreeChips));
        assertThrows(MoreThanTenChipsException.class, () -> full_player.takeChips(board, legalTakeTwoChipsSameColor));
        assertThrows(MoreThanTenChipsException.class, () -> full_player.takeChips(board, legalTakeOneChip));
    }

    @Test
    public void legalTakeOneChip() {
        String legalTakeOneChip = "G1";
        legalExecution(legalTakeOneChip);
    }

    @Test
    public void illegalTakeOneChip() {
        String IllegalTakeOneChip = "J1";
        IllegalExecution(IllegalTakeChipsException.class, IllegalTakeOneChip);
    }

    @Test
    public void legalTakeTwoChips() {
        String legalTakeTwoChips = "G1R1";
        legalExecution(legalTakeTwoChips);
    }

    @Test
    public void legalTakeTwoChipsSameColor() {
        String legalTakeTwoChipsSameColor = "W2";
        legalExecution(legalTakeTwoChipsSameColor);
    }

    @Test
    public void illegalTakeTwoChips() {
        String IllegalTakeTwoChips = "B1J1";
        IllegalExecution(IllegalTakeChipsException.class, IllegalTakeTwoChips); // possible MissingChipsInBankException
    }

    @Test
    public void illegalTakeTwoChipsSameColor() {
        String IllegalTakeTwoChipsSameColor = "G2";
        IllegalExecution(IllegalTakeChipsException.class, IllegalTakeTwoChipsSameColor);
    }

    @Test
    public void legalTakeThreeChips() {
        String legalTakeThreeChips = "W1K1R1";
        legalExecution(legalTakeThreeChips);
    }

    @Test
    public void illegalTakeThreeChipsSameColor() {
        String illegalTakeThreeChipsSameColor = "R2G1";
        IllegalExecution(IllegalTakeChipsException.class, illegalTakeThreeChipsSameColor); // possible MissingChipsInBankException
    }

    @Test
    public void illegalTakeThreeChipsNotInBank() {
        String illegalTakeThreeChipsNotInBank = "K1B1W1";
        IllegalExecution(MissingChipsInBankException.class, illegalTakeThreeChipsNotInBank);
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
        } catch (MissingChipsInBankException e) {
            fail("There should be golden chip available in the bank");
        }
        assertEquals(originalDeckSize - 1, deck.size());
        assertEquals(originalGoldenChips + 1, player.getWallet().getChips(Color.GOLD));
    }

    @Test
    void reserveGoldenCardFromBoard() {
    }

    private static <T extends Throwable> void IllegalExecution(Class<T> expectedTException, String takeStr) {
        Wallet beforeWallet = new Wallet(unfull_player.getWallet());
        assertThrows(expectedTException, () -> unfull_player.takeChips(board, takeStr));
        // verify no change was made to players wallet or b.
        validateWalletSuccess("", beforeWallet, unfull_player.getWallet());
        validateBankSuccess("", bank, board.getBank());
    }

    private static void legalExecution(String takeStr) {
        Wallet beforeWallet = new Wallet(unfull_player.getWallet());
        assertDoesNotThrow(() -> unfull_player.takeChips(board, takeStr));
        validateWalletSuccess(takeStr, beforeWallet, unfull_player.getWallet());
        validateBankSuccess(takeStr, bank, board.getBank());
    }
}
