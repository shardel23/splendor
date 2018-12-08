package main.java.business.entities;

public class Price {
    ColorToAmount pricePerColor;

    public Price(ColorToAmount map) {
        pricePerColor = new ColorToAmount(map);
    }

    public Price() {
        pricePerColor = new ColorToAmount();
    }

    public boolean canPayThePrice(ColorToAmount colorToAmount){
        return colorToAmount.greaterThanOrEqual(pricePerColor);
    }
}
