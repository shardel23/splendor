package main.java.business.entities;

import java.util.*;

public class Player {
    private static int MAX_GOLDEN = 3;
    private static int MAX_CHIPS = 10;

    String name;
    int id;
    Date dateOfBirth;

    ColorToAmount chipsInHand;
    List<Card> cardsInHand;
    List<Card> goldenCards;
    ColorToAmount colorBonuses;
    Integer points;
    List<Royal> royalsInHand;

    private void initializePlayerHand(){
        chipsInHand = new ColorToAmount();
        cardsInHand = new ArrayList<>();
        goldenCards = new ArrayList<>(MAX_GOLDEN);
        colorBonuses = new ColorToAmount();
        points = 0;
        royalsInHand = new ArrayList<>();
    }

    public Player(String name, int id, Date Bday){
        this.name = name;
        this.id = id;
        dateOfBirth = Bday;
        initializePlayerHand();
    }
}
