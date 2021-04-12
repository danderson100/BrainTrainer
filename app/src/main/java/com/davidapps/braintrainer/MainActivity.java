package com.davidapps.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button goButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
    }


    public void startGame(View view) {
        Log.i("Go button clicked", "Let's start the game!");
        Intent intent = new Intent(this, StartGameActivity.class);
        startActivity(intent);
    }
}