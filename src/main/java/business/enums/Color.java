package main.java.business.enums;

import java.util.HashSet;
import java.util.Set;

public enum Color {
    WHITE, BLUE, GREEN, RED, BLACK, GOLD;

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

    public String getShortName() {
        if (this.equals(WHITE)) {
            return "W";
        }
        if (this.equals(BLACK)) {
            return "K";
        }
        if (this.equals(BLUE)) {
            return "B";
        }
        if (this.equals(GREEN)) {
            return "G";
        }
        if (this.equals(RED)) {
            return "R";
        }
        if (this.equals(GOLD)) {
            return "J";
        }
        return "";
    }
}
