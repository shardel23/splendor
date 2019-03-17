package main.java.business.entities;

import main.java.business.enums.Color;
import main.java.business.exceptions.MissingChipsInBankException;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    // TODO: Implement b initialization (C'tor with num of players)
    // TODO: b management (borrow/ put back)
    // TODO: refactor. initialize b with number of players. remove b initialization from Board class.
    // TODO: inplement "returnChips" method.

    private Map<Color, Integer> colorToAmount;

    public Bank() {
        colorToAmount = new HashMap<>();
    }

    public Bank(Bank other) {
        colorToAmount = new HashMap<>(other.colorToAmount);
    }

    public Bank(Map<Color, Integer> map) {
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

}
