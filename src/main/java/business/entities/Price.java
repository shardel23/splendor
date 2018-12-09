package main.java.business.entities;

import main.java.business.Color;

import java.util.HashMap;
import java.util.Map;

public class Price {

    private Map<Color, Integer> colorToPrice;

    public Price() {
        colorToPrice = new HashMap<>();
    }

    public Price(Price other) {
        colorToPrice = new HashMap<>(other.colorToPrice);
    }

    public Price(Map<Color, Integer> map) {
        colorToPrice = new HashMap<>(map);
    }

    public boolean canPayThePrice(Wallet wallet) {
        int missingSum = 0;
        for (Color color: Color.getBasicValues()) {
            int diff = wallet.getTotal(color) -  colorToPrice.getOrDefault(color, 0);
            if (diff < 0) {
                missingSum += diff;
            }
        }
        return missingSum >= 0 || Math.abs(missingSum) <= wallet.getTotal(Color.GOLD);
    }

    public Map<Color, Integer> getColorToPrice() {
        return colorToPrice;
    }
}
