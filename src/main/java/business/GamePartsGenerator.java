package main.java.business;

import main.java.business.entities.Card;
import main.java.business.entities.Price;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static main.java.business.Color.BLACK;
import static main.java.business.Level.ONE;

public class GamePartsGenerator {

    public static List<Card> getCards() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(c(ONE, BLACK, 0, p(1, 1, 1, 1, 0)));
        cardList.add(c(ONE, BLACK, 0, p(0, 0, 2, 1, 0)));
        cardList.add(c(ONE, BLACK, 0, p(2, 0, 2, 0, 0)));
        cardList.add(c(ONE, BLACK, 0, p(0, 0, 1, 3, 1)));
        cardList.add(c(ONE, BLACK, 0, p(0, 0, 3, 0, 0)));
        cardList.add(c(ONE, BLACK, 0, p(1, 2, 1, 1, 0)));
        cardList.add(c(ONE, BLACK, 0, p(2, 2, 0, 1, 0)));
        cardList.add(c(ONE, BLACK, 1, p(0, 4, 0, 0, 0)));
        // TODO: Finish all initializations...
        return cardList;
    }

    private static Card c(Level l, Color c, int points, Price p) {
        return new Card(l, c, points, p);
    }

    private static Price p(int white, int blue, int green, int red, int black) {
        return new Price(map(a(red, green, blue, black, white)));
    }

    private static int[] a(int white, int blue, int green, int red, int black) {
        return new int[]{red, green, blue, black, white};
    }

    /**
     * @param amounts is in the order: white, blue, green, red, black
     * @return
     */
    private static Map<Color, Integer> map(int[] amounts) {
        Map<Color, Integer> map = new HashMap<>();
        int index = 0;
        for (Color color : Color.values()) { // TODO: Change values to basicValues
            map.put(color, amounts[index]);
            index++;
        }
        return map;
    }
}
