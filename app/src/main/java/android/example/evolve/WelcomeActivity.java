package android.example.evolve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.Inet4Address;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Button startButton = findViewById(R.id.start_button);
        Button howToPlayButton = findViewById(R.id.how_to_play_button);
        startButton.setOnClickListener(view -> goToMainActivity());
        howToPlayButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), HowToPlayActivity.class);
            startActivity(intent);
        });
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}