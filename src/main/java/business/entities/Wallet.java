package main.java.business.entities;

import main.java.business.Color;

import java.util.Map;

public class Wallet {

    private static int MAX_CHIPS = 10;

    Map<Color, ChipTotal> chips;

    public Wallet() {

    }

    public void pay(Price price) {
        // TODO: Implement
    }

    public int getTotal(Color color) {
        ChipTotal chipTotal = chips.get(color);
        return chipTotal == null ? 0 : chipTotal.getTotal();
    }

    public int getChips(Color color) {
        ChipTotal chipTotal = chips.get(color);
        return chipTotal == null ? 0 : chipTotal.getCount();
    }

    public int getBonus(Color color) {
        ChipTotal chipTotal = chips.get(color);
        return chipTotal == null ? 0 : chipTotal.getBonus();    }
}
