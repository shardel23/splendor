package main.java.business.entities;

public class ChipTotal {

    private int count;
    private int bonus;

    public ChipTotal(int count, int bonus) {
        this.count = count;
        this.bonus = bonus;
    }

    public ChipTotal() {
        this.count = 0;
        this.bonus = 0;
    }

    public int getTotal() {
        return count+bonus;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
}
