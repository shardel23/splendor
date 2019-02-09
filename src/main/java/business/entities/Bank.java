package main.java.business.entities;

import main.java.business.enums.Color;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    // TODO: Implement bank initialization (C'tor with num of players)
    // TODO: bank management (borrow/ put back)

    private Map<Color, Integer> chipsBank;

    public Bank() {
        chipsBank = new HashMap<>();
    }

    public Bank(Bank other) {
        chipsBank = new HashMap<>(other.chipsBank);
    }

    public Bank(Map<Color, Integer> map) {
        chipsBank = new HashMap<>(map);
    }

}
