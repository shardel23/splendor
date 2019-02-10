package main.java.business.entities;

import main.java.business.Exceptions.CantPayPriceException;
import main.java.business.enums.Color;

import java.util.HashMap;
import java.util.Map;

public class Wallet {

    private static int MAX_CHIPS = 10;

    private Map<Color, ChipBonusPair> chips;

    public Wallet() {
        chips = new HashMap<>();
    }

    public boolean canPayCardPrice(Price price){
        int missingSum = 0;
        Map<Color, Integer> colorToPrice = price.getColorToPrice();
        for (Color color: Color.getBasicValues()) {
            int diff = getTotal(color) -  colorToPrice.getOrDefault(color, 0);
            if (diff < 0) {
                missingSum += diff;
            }
        }
        return missingSum >= 0 || Math.abs(missingSum) <= getTotal(Color.GOLD);
    }

    public boolean canPayRoyalPrice(Price price) {
        Map<Color, Integer> colorToPrice = price.getColorToPrice();
        for (Color color : Color.getBasicValues()) {
            int diff = getBonus(color) - colorToPrice.getOrDefault(color, 0);
            if (diff < 0) {
                return false;
            }
        }
        return true;
    }

    public void pay(Price price) throws CantPayPriceException {
        if (!canPayCardPrice(price)) throw new CantPayPriceException();
        int missingChips;
        for (Color color: Color.getBasicValues()) {
            missingChips = price.getColorToPrice().getOrDefault(color, 0) - getBonus(color) ;
            if (missingChips > 0){
                payChips(color, missingChips);
            }
        }
    }

    public int getTotal(Color color) {
        ChipBonusPair chipBonusPair = chips.get(color);
        return chipBonusPair == null ? 0 : chipBonusPair.getTotal();
    }

    public int getChips(Color color) {
        ChipBonusPair chipBonusPair = chips.get(color);
        return chipBonusPair == null ? 0 : chipBonusPair.getChips();
    }

    public int getBonus(Color color) {
        ChipBonusPair chipBonusPair = chips.get(color);
        return chipBonusPair == null ? 0 : chipBonusPair.getBonus();
    }

    public void addBonus(Color color, int count) {
        ChipBonusPair chipBonusPair = chips.get(color);
        if (chipBonusPair == null){
            chips.put(color, new ChipBonusPair(0, count));
        }
        else {
            chipBonusPair.addBonus(count);
        }
    }

    private void payChips(Color color, int count) {
        ChipBonusPair chipBonusPair = chips.get(color);
        if (chipBonusPair != null) {
            int handCount = chipBonusPair.getChips();
            if (handCount >= count) { chipBonusPair.payChips(count); }
            else {
                chipBonusPair.setChips(0);
                chips.get(Color.GOLD).payChips(count - handCount);
            }
        }
    }
}
