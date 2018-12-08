package main.java.business.entities;

import main.java.business.Color;

import java.util.HashMap;
import java.util.Map;

public class Money {
    Map<Color, Integer> colorToAmount;

    public Money(Map<Color, Integer> map) {
        colorToAmount = new HashMap<>(map);
    }

    public Money(Money money) {
        colorToAmount = new HashMap<>(money.colorToAmount);
    }

    public Money() {
        colorToAmount = new HashMap<>();
        for (Color color : Color.values()) {
            colorToAmount.put(color, 0);
        }
    }

    public boolean greaterThanOrEqual(Money other){
        int missingSum = 0;
        for (Color color: Color.getBasicValues()) {
            int diff = colorToAmount.getOrDefault(color, 0) -  other.colorToAmount.getOrDefault(color, 0);
            if (diff < 0) {
                missingSum += diff;
            }
        }
        return missingSum >= 0 || Math.abs(missingSum) <= colorToAmount.getOrDefault(Color.GOLD, 0);
    }
}
