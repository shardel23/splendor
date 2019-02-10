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
}
