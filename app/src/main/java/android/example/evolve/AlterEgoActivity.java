package android.example.evolve;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AlterEgoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_ego);
        Button back = findViewById(R.id.back_alter_ego_button);
        Button[] alter_ego_buttons = {findViewById(R.id.lemon_alter_ego_button), findViewById(R.id.lemon_plant_alter_ego_button), findViewById(R.id.lemon_tree_alter_ego_button), findViewById(R.id.lemon_farm_alter_ego_button), findViewById(R.id.lemon_village_alter_ego_button), findViewById(R.id.lemon_state_alter_ego_button), findViewById(R.id.lemon_country_alter_ego_button), findViewById(R.id.lemon_world_alter_ego_button)};
        String[] sendBack = new String[8];
        String[] sendTimesBack = new String[8];
        boolean[] sendBoosts = new boolean[8];
        boolean[] sendBoughtAlterEgos = new boolean[8];
        final double[] currentMoney = {0};
        AlterEgo[] alterEgos = new AlterEgo[8];
        for(int i = 0; i < alterEgos.length; i++) {
            if(i % 4 == 3) alterEgos[i] = new AlterEgo(false, true, 5, Math.pow(10, i+1));
            if(i % 4 == 2) alterEgos[i] = new AlterEgo(true, false, 2, Math.pow(10, i+1));
            if(i % 4 == 1) alterEgos[i] = new AlterEgo(false, true, 2, Math.pow(10, i+1));
            if(i % 4 == 0) alterEgos[i] = new AlterEgo(true, false, 2, Math.pow(10, i+1));
        }
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            sendBack = extras.getStringArray("key1");
            currentMoney[0] = Double.parseDouble(extras.getString("key2"));
            sendTimesBack = extras.getStringArray("key5");
            sendBoosts = extras.getBooleanArray("key8");
            sendBoughtAlterEgos = extras.getBooleanArray("key9");
        }
        String[] finalSendBack = sendBack;
        final double[] finalCurrentMoney = {currentMoney[0]};
        for(int i = 0; i < alter_ego_buttons.length; i++) {
            int finalI = i;
            boolean[] finalSendBoosts1 = sendBoosts;
            boolean[] finalSendBoughtAlterEgos = sendBoughtAlterEgos;
            alter_ego_buttons[i].setOnClickListener(view -> {
                String alter_ego_cost = alter_ego_buttons[finalI].getText().toString();
                double value;
                if(alter_ego_cost.contains("K")) {
                    value = Double.parseDouble(alter_ego_cost.substring(7, alter_ego_cost.length()-1));
                    value*=1000;
                }
                else if(alter_ego_cost.contains("M")) {
                    value = Double.parseDouble(alter_ego_cost.substring(7, alter_ego_cost.length()-1));
                    value *= 1000000;
                }
                else {
                    value = Double.parseDouble(alter_ego_cost.substring(7));
                    value *=1;
                }
                if(finalCurrentMoney[0] >= value) {
                    finalCurrentMoney[0] -= value;
                    finalSendBoughtAlterEgos[finalI] = true;
                    alter_ego_buttons[finalI].setClickable(!finalSendBoughtAlterEgos[finalI]);
                    alter_ego_buttons[finalI].setEnabled(!finalSendBoughtAlterEgos[finalI]);
                    finalSendBoosts1[finalI] = true;
                }
                else Toast.makeText(getApplicationContext(), "Not Enough Money!", Toast.LENGTH_SHORT).show();
            });
        }
        Log.d("boosts", "boosts are ready");
        String[] finalSendTimesBack = sendTimesBack;
        boolean[] finalSendBoosts = sendBoosts;
        boolean[] finalSendBoughtAlterEgos1 = sendBoughtAlterEgos;
        back.setOnClickListener(view -> {
            Intent intent2 = new Intent(getApplicationContext(), GameActivity.class);
            intent2.putExtra("key3", finalSendBack);
            intent2.putExtra("key4", finalCurrentMoney[0]+"");
            intent2.putExtra("key6", finalSendTimesBack);
            intent2.putExtra("key7", finalSendBoosts);
            intent2.putExtra("key10", finalSendBoughtAlterEgos1);
            startActivity(intent2);
        });

    }


}