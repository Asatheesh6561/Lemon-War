package android.example.evolve;

import android.widget.Button;

public class AlterEgo {
    private final boolean isProfitMultiplier;
    private final boolean isSpeedMultiplier;
    private final int multiplier;
    private double cost;
    private boolean bought = false;

    public AlterEgo(boolean isProfitMultiplier, boolean isSpeedMultiplier, int multiplier, double cost) {
        this.isProfitMultiplier = isProfitMultiplier;
        this.isSpeedMultiplier = isSpeedMultiplier;
        this.multiplier = multiplier;
        this.cost = cost;
    }
    public boolean isBought() {
        return bought;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
    public void setBought(boolean bought) {
        this.bought = bought;
    }
    public int getMultiplier() {
        return multiplier;
    }
    public boolean getIsProfitMultiplier() {
        return isProfitMultiplier;
    }
    public boolean getIsSpeedMultiplier() {
        return isSpeedMultiplier;
    }
    public double cost() {
        return cost;
    }

}
