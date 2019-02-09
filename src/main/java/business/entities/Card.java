package main.java.business.entities;

import main.java.business.enums.Color;
import main.java.business.enums.Level;

public class Card {
    // TODO: verify all functionality is implemented and implemented properly

    private Level level;
    private Color colorBonus;
    private int pointsBonus;
    private Price price;

    public Card(Level level, Color colorBonus, int pointsBonus, Price price) {
        this.level = level;
        this.colorBonus = colorBonus;
        this.pointsBonus = pointsBonus;
        this.price = price;
    }

    public boolean canBuyCard(Wallet wallet){
        return price.canPayThePrice(wallet);
    }

    public Level getLevel() {
        return level;
    }

    public Color getColorBonus() { return colorBonus; }
    public int getPointsBonus() {
        return pointsBonus;
    }

    public Price getPrice() {
        return price;
    }
}
