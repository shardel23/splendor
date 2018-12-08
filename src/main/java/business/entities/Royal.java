package main.java.business.entities;

public class Royal {
    Price price;

    public Royal(){
        price = new Price();
    }

    public Royal(ColorToAmount colorToAmount){
        price = new Price(colorToAmount);
    }

    public boolean canGetRoyal(ColorToAmount cardsColorToAmount){
        return price.canPayThePrice(cardsColorToAmount);
    }
}
