package main.java.business;

import java.util.HashSet;
import java.util.Set;

public enum Color {
    RED, GREEN, BLUE, WHITE, BLACK, GOLD;

    public static boolean isBasicColor(Color color){
        return color != Color.GOLD;
    }

    public static Set<Color> getBasicValues(){
        Set<Color> basicColors = new HashSet<>();
        for (Color color : Color.values()) {
            if (isBasicColor(color)) {
                basicColors.add(color);
            }
        }
        return basicColors;
    }
}
