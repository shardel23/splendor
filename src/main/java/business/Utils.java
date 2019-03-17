package main.java.business;

import main.java.business.entities.*;
import main.java.business.enums.Color;
import main.java.business.enums.Level;
import main.java.business.exceptions.MoreThanTenChipsException;

import java.util.HashMap;
import java.util.Map;

import static main.java.business.enums.Color.*;

public class Utils {

    public static final Map<Character, Color> CHAR_TO_COLOR = new HashMap<>() {{
            put('W', WHITE);
            put('B', BLUE);
            put('G', GREEN);
            put('R', RED);
            put('K', BLACK);
            put('J', GOLD);
        }};

    public static Map<Color, Integer> createAmountsMap(String amounts) {
        Map<Color, Integer> colorToAmount = new HashMap<>();
        for (int i=0; i<amounts.length(); i+=2){
            char color = amounts.charAt(i);
            int amount = Character.getNumericValue(amounts.charAt(i+1));
            colorToAmount.put(CHAR_TO_COLOR.get(color), amount);
        }
        return colorToAmount;
    }

    public static Price p(String amounts){
        return new Price(createAmountsMap(amounts));
    }

    public static Bank b(String amounts){
        return new Bank(createAmountsMap(amounts));
    }

    public static Card c(Level l, Color c, int points, Price p) {
        return new Card(l, c, points, p);
    }

    public static Royal r(String price) {
       Price royalPrice = p(price);
        return new Royal(royalPrice);
    }

    public static Wallet w(String chipsAmounts, String bonusAmounts){
        Wallet wallet = new Wallet();
        Map<Color, Integer> chips =  createAmountsMap(chipsAmounts);
        Map<Color, Integer> bonuses =  createAmountsMap(bonusAmounts);
        try {
            wallet = new Wallet(chips, bonuses);
        } catch (MoreThanTenChipsException ignored) {}
        return wallet;
    }
}
