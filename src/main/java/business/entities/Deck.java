package main.java.business.entities;

import main.java.business.Exceptions.EmptyDeckException;

import java.util.*;

public class Deck {
    List<Card> cards;

    public Deck(){
        cards = new ArrayList<>();
    }

    public Deck(Deck deck){
        cards = new ArrayList<>(deck.cards);
    }

    public Deck(ArrayList<Card> cards){
        cards = new ArrayList<>(cards);
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
