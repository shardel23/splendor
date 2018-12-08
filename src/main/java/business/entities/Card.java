package main.java.business.entities;

import main.java.business.Color;
import main.java.business.Level;

public class Card {
    Level level;
    Color colorBonus;
    int pointsBonus;
    Price price;

    public Card(Level level, Color colorBonus, int pointsBonus, Price price) {
        this.level = level;
        this.colorBonus = colorBonus;
        this.pointsBonus = pointsBonus;
        this.price = price;
    }

    public boolean canBuyCard(ColorToAmount colorToAmount){
        return price.canPayThePrice(colorToAmount);
    }

    public Level getLevel() {
        return level;
    }

    public int getPointsBonus() {
        return pointsBonus;
    }

    public Price getPrice() {
        return price;
    }
}
