package main.java.business.entities;

import main.java.business.enums.Color;

import java.util.HashMap;
import java.util.Map;

public class Price {
    // TODO: Verify all functionality is implemented and implemented properly

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

    public Map<Color, Integer> getColorToPrice() {
        return colorToPrice;
    }
}
