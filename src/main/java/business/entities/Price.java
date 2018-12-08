package main.java.business.entities;

import main.java.business.Color;

import java.util.Map;

public class Price {
    ColorToAmount pricePerColor;

    public Price(ColorToAmount map) {
        pricePerColor = new ColorToAmount(map);
    }

    public Price() {
        pricePerColor = new ColorToAmount();
    }

    public boolean canPayThePrice(ColorToAmount colorToAmount) {
        return colorToAmount.greaterThanOrEqual(pricePerColor);
    }

    public Price(Map<Color, Integer> map) {
        pricePerColor = new ColorToAmount(map);
    }

}
