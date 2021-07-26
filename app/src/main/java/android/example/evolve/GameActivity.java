package android.example.evolve;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class GameActivity extends AppCompatActivity {
    @SuppressLint("StaticFieldLeak")
    public static TextView total;
    public static double[] previousCost = new double[8];
    public static DecimalFormat df = new DecimalFormat("0.00");
    public static double totalMoney = 2000000000;
    public static double[] currentCosts = new double[8];
    public static int[] currentBoughtTimes = new int[8];
    public static boolean[] boosts = new boolean[8];
    public static boolean[] boughtAlterEgos = new boolean[8];
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        total = findViewById(R.id.amount_of_lemons);
        total.setText("Total amount of Money: $" + df.format(totalMoney));

        Button alter_ego_button = findViewById(R.id.alter_egos_button);
        alter_ego_button.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AlterEgoActivity.class);
            String[] sendCosts = new String[8];
            String[] sendTimes = new String[8];
            String currentMoney = totalMoney + "";
            for(int i = 0; i < sendCosts.length; i++) {
                sendCosts[i] = (currentCosts[i] + "");
                sendTimes[i] = (currentBoughtTimes[i] + "");
            }
            intent.putExtra("key1", sendCosts);
            intent.putExtra("key2", currentMoney);
            intent.putExtra("key5", sendTimes);
            intent.putExtra("key8", boosts);
            intent.putExtra("key9", boughtAlterEgos);
            startActivity(intent);
        });

        Button[] clickButtons = {findViewById(R.id.lemon_click), findViewById(R.id.lemon_plant_click), findViewById(R.id.lemon_tree_click), findViewById(R.id.lemon_farm_click), findViewById(R.id.lemon_village_click), findViewById(R.id.lemon_state_click), findViewById(R.id.lemon_country_click), findViewById(R.id.lemon_world_click)};
        ProgressBar[] progressBars = {findViewById(R.id.lemon_progress_bar), findViewById(R.id.lemon_plant_progress_bar), findViewById(R.id.lemon_tree_progress_bar), findViewById(R.id.lemon_farm_progress_bar), findViewById(R.id.lemon_village_progress_bar), findViewById(R.id.lemon_state_progress_bar), findViewById(R.id.lemon_country_progress_bar), findViewById(R.id.lemon_world_progress_bar)};
        Button[] bought_buttons = {findViewById(R.id.lemon_bought), findViewById(R.id.lemon_plant_bought), findViewById(R.id.lemon_tree_bought), findViewById(R.id.lemon_farm_bought), findViewById(R.id.lemon_village_bought), findViewById(R.id.lemon_state_bought), findViewById(R.id.lemon_country_bought), findViewById(R.id.lemon_world_bought)};
        AlterEgo[] alterEgos = new AlterEgo[8];
        LemonItem[] lemonItems = new LemonItem[8];
        for(int i = 0; i < lemonItems.length; i++) {
            clickButtons[i].setClickable(false);
            clickButtons[i].setEnabled(false);
            if(i % 4 == 3) alterEgos[i] = new AlterEgo(false, true, 5, Math.pow(10, i+1));
            if(i % 4 == 2) alterEgos[i] = new AlterEgo(true, false, 2, Math.pow(10, i+1));
            if(i % 4 == 1) alterEgos[i] = new AlterEgo(false, true, 2, Math.pow(10, i+1));
            if(i % 4 == 0) alterEgos[i] = new AlterEgo(true, false, 2, Math.pow(10, i+1));
            lemonItems[i] = new LemonItem(clickButtons[i], progressBars[i], bought_buttons[i], i, 10000*Math.pow(2, i), Math.pow(10, i), alterEgos[i]);
            currentCosts[i] = lemonItems[i].getStarting_cost();
            if(i < 3) bought_buttons[i].setText("Bought: " + 0 + "\n" + "Next: $" + df.format(currentCosts[i]));
            else if(i < 6) bought_buttons[i].setText("Bought: " + 0 + "\n" + "Next: $" + df.format(currentCosts[i]/1000) + "K");
            else bought_buttons[i].setText("Bought: " + 0 + "\n" + "Next: $" + df.format(currentCosts[i]/1000000) + "M");
            clickBuyButton(lemonItems[i]);
            startProgressBar(lemonItems[i]);
        }

        Bundle extra = getIntent().getExtras();
        if(extra != null) {
            String[] setCosts = extra.getStringArray("key3");
            String[] setBoughtTimes = extra.getStringArray("key6");
            boolean[] getBoosts = extra.getBooleanArray("key7");
            boolean[] getBoughtAlterEgos = extra.getBooleanArray("key10");
            for (int i = 0; i < bought_buttons.length; i++) {
                currentCosts[i] = Double.parseDouble(setCosts[i]);
                boosts[i] = getBoosts[i];
                boughtAlterEgos[i] = getBoughtAlterEgos[i];
                System.out.print(boosts[i] + " ");
                currentBoughtTimes[i] = Integer.parseInt(setBoughtTimes[i]);
                if(i < 3) bought_buttons[i].setText("Bought: " + currentBoughtTimes[i] + "\n" + "Next: $" + df.format(currentCosts[i]));
                else if(i < 6) bought_buttons[i].setText("Bought: " + currentBoughtTimes[i] + "\n" + "Next: $" + df.format(currentCosts[i]/1000) + "K");
                else bought_buttons[i].setText("Bought: " + currentBoughtTimes[i] + "\n" + "Next: $" + df.format(currentCosts[i]/1000000) + "M");
                if (currentBoughtTimes[i] > 0) {
                    clickButtons[i].setEnabled(true);
                    clickButtons[i].setClickable(true);
                }
            }
            totalMoney = Double.parseDouble(extra.getString("key4"));
            total.setText("Total Amount of Money: $" + df.format(totalMoney));
        }
    }
    public void startProgressBar(LemonItem lemonItem) {
        Button button = lemonItem.getLemon_click_button();
        ProgressBar progressBar = lemonItem.getLemon_progress_bar();
        progressBar.setProgress(0);
        button.setOnClickListener((View.OnClickListener) view -> {
            final int[] i = {0};
            button.setClickable(false);
            CountDownTimer count = new CountDownTimer((long)lemonItem.getTime(), 1000  ) {
                @Override
                public void onTick(long l) {
                    i[0]++;
                    progressBar.setProgress(((int)i[0] *100/((int)lemonItem.getTime()/1000)));
                }
                @SuppressLint("SetTextI18n")
                @Override
                public void onFinish() {
                    progressBar.setProgress(0);
                    button.setClickable(true);
                    System.out.println(lemonItem.getAlterEgo().getIsProfitMultiplier());
                    if(boosts[lemonItem.getPosition()]) {
                        totalMoney+=(previousCost[lemonItem.getPosition()]*1.15*lemonItem.getAlterEgo().getMultiplier());
                        makeText(getApplicationContext(), "You have received $" + df.format(Math.pow(1.15, currentBoughtTimes[lemonItem.getPosition()])*lemonItem.getAlterEgo().getMultiplier()), LENGTH_SHORT).show();
                    }
                    else {
                        totalMoney+=(previousCost[lemonItem.getPosition()]*1.15);
                        makeText(getApplicationContext(), "You have received $" + df.format(Math.pow(1.15, currentBoughtTimes[lemonItem.getPosition()])), LENGTH_SHORT).show();
                    }
                    total.setText("Total amount of Money: $" + df.format(totalMoney));
                }
            };
            count.start();
        });
    }
    @SuppressLint("SetTextI18n")
    public void clickBuyButton(LemonItem lemonItem) {
        Button buy_button = lemonItem.getLemon_bought_button();
        buy_button.setOnClickListener(view -> {
            previousCost[lemonItem.getPosition()] = lemonItem.getStarting_cost();
            if(lemonItem.getStarting_cost() > totalMoney) {
                makeText(getApplicationContext(), "Not Enough Money!", LENGTH_SHORT).show();
            }else {
                currentBoughtTimes[lemonItem.getPosition()]+=1;
                currentCosts[lemonItem.getPosition()]*=1.25;
                if(lemonItem.getPosition() < 3) buy_button.setText("Bought: " + currentBoughtTimes[lemonItem.getPosition()]+ "\n" + "Next: $" + df.format(currentCosts[lemonItem.getPosition()]));
                else if(lemonItem.getPosition() < 6) buy_button.setText("Bought: " + currentBoughtTimes[lemonItem.getPosition()] + "\n" + "Next: $" + df.format(currentCosts[lemonItem.getPosition()]/1000)+"K");
                else buy_button.setText("Bought: " + currentBoughtTimes[lemonItem.getPosition()] + "\n" + "Next: $" + df.format(currentCosts[lemonItem.getPosition()]/1000000)+"M");
                lemonItem.getLemon_click_button().setEnabled(true);
                lemonItem.getLemon_click_button().setClickable(true);
                totalMoney -= previousCost[lemonItem.getPosition()];
                if(gameOver()) {
                    Intent intent = new Intent(getApplicationContext(), GameOverActivity.class);
                    startActivity(intent);
                }
                else total.setText("Total Amount of Money: $" + df.format(totalMoney));

            }
        });
    }
    public static boolean gameOver() {
        for(int i: currentBoughtTimes) {
            if(i < 50) return false;
        }
        System.out.println("Game over");
        return true;
    }
}
