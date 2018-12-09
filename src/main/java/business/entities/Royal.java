package main.java.business.entities;

import main.java.business.Color;

public class Royal {
    private Price price;

    public Royal(){
        price = new Price();
    }

    public Royal(Price price){
        this.price = new Price(price);
    }

    public boolean canGetRoyal(Wallet wallet){
        for (Color color: Color.getBasicValues()) {
            int diff = wallet.getBonus(color) -  price.getColorToPrice().getOrDefault(color, 0);
            if (diff < 0) {
                return false;
            }
        }
        return true;
    }
}
