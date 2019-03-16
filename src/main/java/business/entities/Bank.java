package main.java.business.entities;

import main.java.business.enums.Color;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    // TODO: Implement bank initialization (C'tor with num of players)
    // TODO: bank management (borrow/ put back)

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

    /**
     * Try to take chips of a specific color from the bank.
     * @param color
     * @param amountToTake
     * @return The amountToTake of chips taken, based on availability.
     */
    public int takeChips(Color color, int amountToTake) {
        int amountInBank = colorToAmount.getOrDefault(color, 0);
        int amountTaken;
        if (amountToTake > amountInBank) {
            colorToAmount.put(color, 0);
            amountTaken = amountInBank;
        }
        else {
            amountTaken = amountToTake;
            colorToAmount.put(color, amountInBank-amountTaken);
        }
        return amountTaken;
    }
}
