package main.java.business.entities;

import main.java.business.enums.Color;

import java.util.HashMap;
import java.util.Map;

public class Wallet {

    private static int MAX_CHIPS = 10;

    private Map<Color, chipBonusPair> chips;

    public Wallet() {
        chips = new HashMap<>();
    }

    public void pay(Price price) {
        // TODO: Implement
    }

    public int getTotal(Color color) {
        chipBonusPair chipBonusPair = chips.get(color);
        return chipBonusPair == null ? 0 : chipBonusPair.getTotal();
    }

    public int getChips(Color color) {
        chipBonusPair chipBonusPair = chips.get(color);
        return chipBonusPair == null ? 0 : chipBonusPair.getCount();
    }

    public int getBonus(Color color) {
        chipBonusPair chipBonusPair = chips.get(color);
        return chipBonusPair == null ? 0 : chipBonusPair.getBonus();    }
}
