package main.java.business;

import main.java.business.entities.Card;
import main.java.business.entities.Deck;
import main.java.business.entities.Price;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static main.java.business.Color.*;
import static main.java.business.Level.*;

public class GamePartsGenerator {

    private static final Map<Character, Color> CHAR_TO_COLOR = createMap();
    private static Map<Character, Color> createMap()
    {
        // W(white) B(black) G(green) R(red) K(black)
        Map<Character,Color> myMap = new HashMap<>();
        myMap.put('W', WHITE);
        myMap.put('B', BLUE);
        myMap.put('G', GREEN);
        myMap.put('R', RED);
        myMap.put('K', BLACK);
        return myMap;
    }

    public static Map<Level, Deck> getDecks() {
        Map<Level, Deck> decks = new HashMap<>();
        decks.put(ONE, getLevelOneDeck());
        decks.put(TWO, getLevelTwoDeck());
        decks.put(THREE, getLevelThreeDeck());
        return decks;
    }

    private static Deck getLevelOneDeck(){
        List<Card> cardList = new ArrayList<>();

        // ------------- BLACK
        cardList.add(c(ONE, BLACK, 0, p("W1B1G1R1")));
        cardList.add(c(ONE, BLACK, 0, p("G2R1")));
        cardList.add(c(ONE, BLACK, 0, p("W2G2")));
        cardList.add(c(ONE, BLACK, 0, p("G1R3K1")));
        cardList.add(c(ONE, BLACK, 0, p("G3")));
        cardList.add(c(ONE, BLACK, 0, p("W1B2G1R1")));
        cardList.add(c(ONE, BLACK, 0, p("W2B2R1")));
        cardList.add(c(ONE, BLACK, 1, p("B4")));
        // ------------- WHITE
        cardList.add(c(ONE, WHITE, 0, p("B2G2K1")));
        cardList.add(c(ONE, WHITE, 0, p("R2K1")));
        cardList.add(c(ONE, WHITE, 0, p("B1G1R1K1")));
        cardList.add(c(ONE, WHITE, 0, p("B3")));
        cardList.add(c(ONE, WHITE, 0, p("B2K2")));
        cardList.add(c(ONE, WHITE, 0, p("B1G2R1K1")));
        cardList.add(c(ONE, WHITE, 0, p("W3B1K1")));
        cardList.add(c(ONE, WHITE, 1, p("G4")));
        // ------------- RED
        cardList.add(c(ONE, RED, 0, p("W3")));
        cardList.add(c(ONE, RED, 0, p("W1R1K3")));
        cardList.add(c(ONE, RED, 0, p("B2G1")));
        cardList.add(c(ONE, RED, 0, p("W2G1K2")));
        cardList.add(c(ONE, RED, 0, p("W2B1G1K1")));
        cardList.add(c(ONE, RED, 0, p("W1B1G1K1")));
        cardList.add(c(ONE, RED, 0, p("W2R2")));
        cardList.add(c(ONE, RED, 1, p("W4")));
        // ------------- GREEN
        cardList.add(c(ONE, GREEN, 0, p("W2B1")));
        cardList.add(c(ONE, GREEN, 0, p("B2R2")));
        cardList.add(c(ONE, GREEN, 0, p("W1B3G1")));
        cardList.add(c(ONE, GREEN, 0, p("W1B1R1K1")));
        cardList.add(c(ONE, GREEN, 0, p("W1B1R1K2")));
        cardList.add(c(ONE, GREEN, 0, p("B1R2K2")));
        cardList.add(c(ONE, GREEN, 0, p("R3")));
        cardList.add(c(ONE, GREEN, 1, p("K4")));
        // ------------- BLUE
        cardList.add(c(ONE, BLUE, 0, p("W1K2")));
        cardList.add(c(ONE, BLUE, 0, p("W1G1R2K1")));
        cardList.add(c(ONE, BLUE, 0, p("W1G1R1K1")));
        cardList.add(c(ONE, BLUE, 0, p("B1G3R1")));
        cardList.add(c(ONE, BLUE, 0, p("K3")));
        cardList.add(c(ONE, BLUE, 0, p("W1G2R2")));
        cardList.add(c(ONE, BLUE, 0, p("G2K2")));
        cardList.add(c(ONE, BLUE, 1, p("R4")));

        return new Deck(cardList);
    }

    private static Deck getLevelTwoDeck() {
        List<Card> cardList = new ArrayList<>();

        // ------------- BLACK
        cardList.add(c(TWO, BLACK, 1, p("W3B2G2")));
        cardList.add(c(TWO, BLACK, 1, p("W3G3K2")));
        cardList.add(c(TWO, BLACK, 2, p("B1G4R2")));
        cardList.add(c(TWO, BLACK, 2, p("W5")));
        cardList.add(c(TWO, BLACK, 2, p("G5R3")));
        cardList.add(c(TWO, BLACK, 3, p("K6")));
        // ------------- WHITE
        cardList.add(c(TWO, WHITE, 1, p("G3R2K2")));
        cardList.add(c(TWO, WHITE, 1, p("W2B3R3")));
        cardList.add(c(TWO, WHITE, 2, p("G1R4K2")));
        cardList.add(c(TWO, WHITE, 2, p("R5")));
        cardList.add(c(TWO, WHITE, 2, p("R5K3")));
        cardList.add(c(TWO, WHITE, 3, p("W6")));
        // ------------- RED
        cardList.add(c(TWO, RED, 1, p("B3R2K3")));
        cardList.add(c(TWO, RED, 1, p("W2R2K3")));
        cardList.add(c(TWO, RED, 2, p("W1B4G2")));
        cardList.add(c(TWO, RED, 2, p("W3K5")));
        cardList.add(c(TWO, RED, 2, p("K5")));
        cardList.add(c(TWO, RED, 3, p("R6")));
        // ------------- GREEN
        cardList.add(c(TWO, GREEN, 1, p("W3G2R3")));
        cardList.add(c(TWO, GREEN, 1, p("W2B3K2")));
        cardList.add(c(TWO, GREEN, 2, p("W4B2K1")));
        cardList.add(c(TWO, GREEN, 2, p("G5")));
        cardList.add(c(TWO, GREEN, 2, p("B5G3")));
        cardList.add(c(TWO, GREEN, 3, p("G6")));
        // ------------- BLUE
        cardList.add(c(TWO, BLUE, 1, p("B2G2R3")));
        cardList.add(c(TWO, BLUE, 1, p("B2G3K3")));
        cardList.add(c(TWO, BLUE, 2, p("W5B3")));
        cardList.add(c(TWO, BLUE, 2, p("B5")));
        cardList.add(c(TWO, BLUE, 2, p("W2R1K4")));
        cardList.add(c(TWO, BLUE, 3, p("B6")));

        return new Deck(cardList);
    }

    private static Deck getLevelThreeDeck() {
        List<Card> cardList = new ArrayList<>();

        // ------------- BLACK
        cardList.add(c(THREE, BLACK, 3, p("W3B3G5R3")));
        cardList.add(c(THREE, BLACK, 4, p("R7")));
        cardList.add(c(THREE, BLACK, 4, p("G3R6K3")));
        cardList.add(c(THREE, BLACK, 5, p("R7K3")));
        // ------------- WHITE
        cardList.add(c(THREE, WHITE, 3, p("B3G3R5K3")));
        cardList.add(c(THREE, WHITE, 4, p("K7")));
        cardList.add(c(THREE, WHITE, 4, p("W3R3K6")));
        cardList.add(c(THREE, WHITE, 5, p("W3K7")));
        // ------------- RED
        cardList.add(c(THREE, RED, 3, p("W3B5G3K3")));
        cardList.add(c(THREE, RED, 4, p("G7")));
        cardList.add(c(THREE, RED, 4, p("B3G6R3")));
        cardList.add(c(THREE, RED, 5, p("G7R3")));
        // ------------- GREEN
        cardList.add(c(THREE, GREEN, 3, p("W5B3R3K3")));
        cardList.add(c(THREE, GREEN, 4, p("W3B6G3")));
        cardList.add(c(THREE, GREEN, 4, p("B7")));
        cardList.add(c(THREE, GREEN, 5, p("B7G3")));
        // ------------- BLUE
        cardList.add(c(THREE, BLUE, 3, p("W3G3R3K5")));
        cardList.add(c(THREE, BLUE, 4, p("W7")));
        cardList.add(c(THREE, BLUE, 4, p("W6B3K3")));
        cardList.add(c(THREE, BLUE, 5, p("W7B3")));

        return new Deck(cardList);
    }

    private static Price p(String price){
        Map<Color, Integer> amounts = new HashMap<>();
        for (int i=0; i<price.length(); i+=2){
            char color = price.charAt(i);
            int amount = Character.getNumericValue(price.charAt(i+1));
            amounts.put(CHAR_TO_COLOR.get(color), amount);
        }
        return new Price(amounts);
    }

    private static Card c(Level l, Color c, int points, Price p) {
        return new Card(l, c, points, p);
    }
}
