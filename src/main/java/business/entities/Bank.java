package main.java.business.entities;

import main.java.business.Utils;
import main.java.business.enums.Color;
import main.java.business.exceptions.IllegalReturnChipsException;
import main.java.business.exceptions.MissingChipsInBankException;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    // TODO: Implement b initialization (C'tor with num of players)
    // TODO: b management (borrow/ put back)
    // TODO: implement "returnChips" method.

    private static final String GOLDEN_CHIPS_STR = "J5";
    private static final String EMPTY_BANK = "";
    private static final Map<Integer, Integer> NumOfPlayersToNumOfChips = new HashMap<>() {{
        put(2, 4); put(3, 5); put(4, 7);
    }};
    private static final int DEFAULT_MAX_CHIPS = 7;
    private static final int MAX_GOLDEN_CHIPS = 5;

    private Integer maxChips;
    private Map<Color, Integer> colorToAmount;

    public Bank() {
        maxChips = DEFAULT_MAX_CHIPS;
        colorToAmount = new HashMap<>();
    }

    public Bank(int numOfPlayers) {
        this.maxChips = NumOfPlayersToNumOfChips.getOrDefault(numOfPlayers, DEFAULT_MAX_CHIPS);
        colorToAmount = Utils.createAmountsMap(generateBankStr(numOfPlayers));
    }

    public Bank(Bank other) {
        this.maxChips = other.maxChips;
        colorToAmount = new HashMap<>(other.colorToAmount);
    }

    public Bank(Map<Color, Integer> map) {
        maxChips = DEFAULT_MAX_CHIPS;
        colorToAmount = new HashMap<>(map);
    }

    public int getAmountOfColor(Color color) {
        return colorToAmount.getOrDefault( color, 0);
    }

    public void takeChips(Color color, Integer amount) throws MissingChipsInBankException {
        if (colorToAmount.getOrDefault(color, 0) < amount) {
            throw new MissingChipsInBankException();
        }
        colorToAmount.put(color, colorToAmount.getOrDefault(color, 0) - amount);
    }

    public void returnChips(Color color, Integer amount) throws IllegalReturnChipsException {
        int limit = color == Color.GOLD ? MAX_GOLDEN_CHIPS : maxChips;
        if (colorToAmount.getOrDefault(color, 0) + amount > limit) {
            throw new IllegalReturnChipsException();
        }
        colorToAmount.put(color, colorToAmount.getOrDefault(color, 0) + amount);
    }

    private String generateBankStr(int numOfPlayers){
        int numOfChips = NumOfPlayersToNumOfChips.getOrDefault(numOfPlayers, 0);
        if (numOfChips == 0) return EMPTY_BANK;
        String bankStr = GOLDEN_CHIPS_STR;
        for (Character letter: Utils.CHAR_TO_COLOR.keySet()) {
            bankStr += letter + numOfChips;
        }
        return bankStr;
    }
}
