package main.java.business.entities;

import main.java.business.Color;

import java.util.HashMap;
import java.util.Map;

public class Price {
    Money pricePerColor;

    public Price(Money map) {
        pricePerColor = new Money(map);
    }

    public Price() {
        pricePerColor = new Money();
    }

    public Price(Map<Color, Integer> map) {
        pricePerColor = new Money(map);
    }

    public boolean canPayThePrice(Money money){
        return money.greaterThanOrEqual(pricePerColor);
    }
}
