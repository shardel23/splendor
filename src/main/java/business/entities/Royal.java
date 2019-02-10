package main.java.business.entities;

import main.java.business.enums.Color;

import java.util.Map;

public class Royal {
    private Price price;

    public Royal(){
        price = new Price();
    }

    public Royal(Price price){
        this.price = new Price(price);
    }

    public boolean canGetRoyal(Wallet wallet){      // TODO: possible duplication of Wallet.canPayRoyalPrice
        Map<Color, Integer> royalPrice = price.getColorToPrice();
        for (Color color: Color.getBasicValues()) {
            int diff = wallet.getBonus(color) -  royalPrice.getOrDefault(color, 0);
            if (diff < 0) {
                return false;
            }
        }
        return true;
    }
}
