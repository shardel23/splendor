package main.java.business.entities;

import main.java.business.exceptions.CantPayPriceException;
import main.java.business.exceptions.MoreThanTenChipsException;
import main.java.business.enums.Color;

import java.util.HashMap;
import java.util.Map;

public class Wallet {

    private final static int MAX_CHIPS = 10;

    private Map<Color, ChipBonusPair> wallet;

    public Wallet() {
        wallet = new HashMap<>();
    }

    public Wallet(Map<Color, ChipBonusPair> wallet) {
        this.wallet = new HashMap<>(wallet);
    }

    public Wallet(Map<Color, Integer> chips, Map<Color, Integer> bonuses) throws MoreThanTenChipsException {
        int sum = chips.values().stream().reduce(0, Integer::sum);
        if (sum > 10) {
            throw new MoreThanTenChipsException();
        }
        wallet = new HashMap<>();
        for (Color color: Color.values()) {
            int chipNum = chips.getOrDefault(color,0);
            int bonusNum = bonuses.getOrDefault(color, 0);
            ChipBonusPair colorAmounts = new ChipBonusPair(chipNum, bonusNum);
            wallet.put(color, colorAmounts);
        }
    }

    public boolean canPayPrice(Price price) {
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

    public boolean canBuyCard(Card card){
        return canPayPrice(card.getPrice());
    }

    public boolean canGetRoyal(Royal royal) {      // TODO: possible duplication of Royal.canGetRoyal
        Price price = royal.getPrice();
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
        ChipBonusPair chipBonusPair = wallet.get(color);
        return chipBonusPair == null ? 0 : chipBonusPair.getTotal();
    }

    public int getChips(Color color) {
        ChipBonusPair chipBonusPair = wallet.get(color);
        return chipBonusPair == null ? 0 : chipBonusPair.getChips();
    }

    public int getBonus(Color color) {
        ChipBonusPair chipBonusPair = wallet.get(color);
        return chipBonusPair == null ? 0 : chipBonusPair.getBonus();
    }

    public void addBonus(Color color, int count) {
        ChipBonusPair chipBonusPair = wallet.get(color);
        if (chipBonusPair == null){
            wallet.put(color, new ChipBonusPair(0, count));
        }
        else {
            chipBonusPair.addBonus(count);
        }
    }

    private void payChips(Color color, int count) {
        ChipBonusPair chipBonusPair = wallet.get(color);
        if (chipBonusPair != null) {
            int handCount = chipBonusPair.getChips();
            if (handCount >= count) { chipBonusPair.payChips(count); }
            else {
                chipBonusPair.setChips(0);
                wallet.get(Color.GOLD).payChips(count - handCount);
            }
        }
    }

    public int getTotalAmountOfChips(){
        int totalChips = 0;
        for (Color color: Color.values()) {
            totalChips += getChips(color);
        }
        return totalChips;
    }
}
