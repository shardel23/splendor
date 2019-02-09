package main.java.business.entities;

public class chipBonusPair {

    private int chips;
    private int bonus;

    public chipBonusPair(int count, int bonus) {
        this.chips = count;
        this.bonus = bonus;
    }

    public chipBonusPair() {
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
}
