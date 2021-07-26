package android.example.evolve;

import android.widget.Button;
import android.widget.ProgressBar;

public class LemonItem {
    private Button lemon_click_button;
    private ProgressBar lemon_progress_bar;
    private Button lemon_bought_button;
    private int position;
    private double starting_cost;
    private double time;
    private AlterEgo alterEgo;
    private int boughtTimes;
    public LemonItem(Button lemon_click_button, ProgressBar lemon_progress_bar, Button lemon_bought_button, int position, double time, double starting_cost, AlterEgo alterEgo) {
        this.lemon_click_button = lemon_click_button;
        this.lemon_progress_bar = lemon_progress_bar;
        this.lemon_bought_button = lemon_bought_button;
        this.position = position;
        this.time = time;
        this.starting_cost = starting_cost;
        this.alterEgo = alterEgo;
    }

    public Button getLemon_click_button() {
        return lemon_click_button;
    }

    public ProgressBar getLemon_progress_bar() {
        return lemon_progress_bar;
    }

    public Button getLemon_bought_button() {
        return lemon_bought_button;
    }

    public int getPosition() {
        return position;
    }

    public double getStarting_cost() {
        return starting_cost;
    }
    public void setStarting_cost(double starting_cost) {this.starting_cost = starting_cost;}
    public double getTime() {
        return time;
    }
    public AlterEgo getAlterEgo() {
        return alterEgo;
    }
    public int getBoughtTimes() {return this.boughtTimes;}
    public void setBoughtTimes(int boughtTimes) {this.boughtTimes = boughtTimes;}
}
