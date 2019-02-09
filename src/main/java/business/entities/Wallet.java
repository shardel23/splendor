package main.java.business.entities;

import main.java.business.Exceptions.CantPayPriceException;
import main.java.business.enums.Color;

import java.util.HashMap;
import java.util.Map;

public class Wallet {

    private static int MAX_CHIPS = 10;

    private Map<Color, chipBonusPair> chips;

    public Wallet() {
        chips = new HashMap<>();
    }

    public boolean canPayPrice(Price price){
        return price.canPayThePrice(this);
    }

    public void pay(Price price) throws CantPayPriceException {
        if (!canPayPrice(price)) throw new CantPayPriceException();
        int missingChips;
        for (Color color: Color.getBasicValues()) {
            missingChips = price.getColorToPrice().getOrDefault(color, 0) - getBonus(color) ;
            if (missingChips > 0){
                payChips(color, missingChips);
            }
        }
    }

    public int getTotal(Color color) {
        chipBonusPair chipBonusPair = chips.get(color);
        return chipBonusPair == null ? 0 : chipBonusPair.getTotal();
    }

    public int getChips(Color color) {
        chipBonusPair chipBonusPair = chips.get(color);
        return chipBonusPair == null ? 0 : chipBonusPair.getChips();
    }

    public int getBonus(Color color) {
        chipBonusPair chipBonusPair = chips.get(color);
        return chipBonusPair == null ? 0 : chipBonusPair.getBonus();
    }

    public void addBonus(Color color, int count) {
        chipBonusPair chipBonusPair = chips.get(color);
        if (chipBonusPair == null){
            chips.put(color, new chipBonusPair(0, count));
        }
        else {
            chipBonusPair.addBonus(count);
        }
    }

    private void payChips(Color color, int count) {
        chipBonusPair chipBonusPair = chips.get(color);
        if (chipBonusPair != null) { chipBonusPair.payChips(count); }
    }
}
