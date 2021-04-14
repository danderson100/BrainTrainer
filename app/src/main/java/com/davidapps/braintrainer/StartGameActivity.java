package com.davidapps.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

public class StartGameActivity extends AppCompatActivity {

    CountDownTimer gameTimer;
  //  TextView timerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        TextView timerText = findViewById(R.id.timerTextView);



        startCountdown(timerText);

    }

    private void startCountdown(TextView timerText) {

        gameTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.i("ticking", "one second has passed!");
                long secondsLeft = millisUntilFinished / 1000;

                String timeString = String.valueOf(secondsLeft);

                if (secondsLeft < 10) {
                    timerText.setText(String.format("0:0%s", timeString));
                } else {
                    timerText.setText(String.format("0:%s", timeString));
                }

            }

            @Override
            public void onFinish() {
                Log.i("all finished", "reveal play again button and the textview");
            }
        }.start();
    }
}