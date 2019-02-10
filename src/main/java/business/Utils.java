package main.java.business;

import main.java.business.entities.Bank;
import main.java.business.entities.Card;
import main.java.business.entities.Price;

import java.util.HashMap;
import java.util.Map;

import static main.java.business.Color.*;

public class Utils {

    private static final Map<Character, Color> CHAR_TO_COLOR = createMap();

    static Map<Character, Color> createMap()
    {
        // W(white) B(black) G(green) R(red) K(black) J(gold)
        Map<Character,Color> myMap = new HashMap<>();
        myMap.put('W', WHITE);
        myMap.put('B', BLUE);
        myMap.put('G', GREEN);
        myMap.put('R', RED);
        myMap.put('K', BLACK);
        myMap.put('J', GOLD);
        return myMap;
    }

    public static Map<Color, Integer> createAmountsMap(String amounts) {
        Map<Color, Integer> colorToAmount = new HashMap<>();
        for (int i=0; i<amounts.length(); i+=2){
            char color = amounts.charAt(i);
            int amount = Character.getNumericValue(amounts.charAt(i+1));
            colorToAmount.put(CHAR_TO_COLOR.get(color), amount);
        }
        return colorToAmount;
    }

    static Price p(String amounts){
        return new Price(createAmountsMap(amounts));
    }

    static Bank b(String amounts){
        return new Bank(createAmountsMap(amounts));
    }

    static Card c(Level l, Color c, int points, Price p) {
        return new Card(l, c, points, p);
    }
}
