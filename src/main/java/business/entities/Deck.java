package main.java.business.entities;

import main.java.business.Exceptions.EmptyDeckException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> cards;

    public Deck(){
        cards = new ArrayList<>();
    }

    public Deck(Deck deck){
        cards = new ArrayList<>(deck.cards);
    }

    public Deck(List<Card> cards){
        this.cards = new ArrayList<>(cards);
    }

    public boolean isEmpty(){
        return cards.isEmpty();
    }

    public Card draw() throws EmptyDeckException {
        if (isEmpty()) {
            throw new EmptyDeckException("Can't draw new card. " + EmptyDeckException.msg);
        }
        Random rand = new Random();
        int location = rand.nextInt(cards.size());
        return cards.remove(location);
    }
}
