package main.java.business.entities;

import main.java.business.Color;

import java.util.HashMap;
import java.util.Map;

public class ColorToAmount {
    Map<Color, Integer> colorToAmount;

    public ColorToAmount(Map<Color, Integer> map) {
        colorToAmount = new HashMap<>(map);
    }

    public ColorToAmount(ColorToAmount colorToAmount) {
        this.colorToAmount = new HashMap<>(colorToAmount.colorToAmount);
    }

    public ColorToAmount() {
        colorToAmount = new HashMap<>();
        for (Color color : Color.values()) {
            colorToAmount.put(color, 0);
        }
    }

    public boolean greaterThanOrEqual(ColorToAmount other){
        int missingSum = 0;
        for (Color color: Color.getBasicValues()) {
            int diff = colorToAmount.getOrDefault(color, 0) -  other.colorToAmount.getOrDefault(color, 0);
            if (diff < 0) {
                missingSum += diff;
            }
        }
        return missingSum >= 0 || Math.abs(missingSum) <= colorToAmount.getOrDefault(Color.GOLD, 0);
    }

    public ColorToAmount merge(ColorToAmount other){
        ColorToAmount merged = new ColorToAmount(this);
        for (Color color: Color.values()) {
            int currAmount = merged.colorToAmount.getOrDefault(color, 0);
            int otherAmount = other.colorToAmount.getOrDefault(color, 0);
            merged.colorToAmount.put(color, currAmount + otherAmount);
        }
        return merged;
    }
}
