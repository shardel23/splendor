package main.java.business.entities;

public class ChipBonusPair {

    private int chips;
    private int bonus;

    public ChipBonusPair(int chips, int bonus) {
        this.chips = chips;
        this.bonus = bonus;
    }

    public ChipBonusPair() {
        this.chips = 0;
        this.bonus = 0;
    }

    public int getTotal() {
        return chips + bonus;
    }

    public int getChips() {
        return chips;
    }

    public void setChips(int chips) {
        this.chips = chips;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public void addBonus(int bonus) {
        this.bonus += bonus;
    }

    public void payChips(int chipsPrice){
        this.chips -= chipsPrice;
    }

    public void addChips(int amount) {
        this.chips += amount;
    }
}
