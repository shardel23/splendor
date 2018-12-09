package main.java.business.entities;

import main.java.business.Color;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    Map<Color, Integer> colorToPrice;

    public Bank() {
        colorToPrice = new HashMap<>();
    }

    public Bank(Price other) {
        colorToPrice = new HashMap<>(other.colorToPrice);
    }

    public Bank(Map<Color, Integer> map) {
        colorToPrice = new HashMap<>(map);
    }

}
