package com.nrv773.rocket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {

    private Button arcadeModeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        arcadeModeButton = findViewById(R.id.arcadeButton);

        setContentView(R.layout.activity_game);
    }

    /**
     * Start the arcade activity when the arcadeModeButton is pressed.
     * note: this is handled in the activity_main_menu.xml button definition
     * @param view the current view.
     */
    public void startArcadeActivity(View view){
        Intent intent = new Intent(this, FlightActivity.class);

        startActivity(intent);
    }
}