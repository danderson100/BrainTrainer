package com.davidapps.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class StartGameActivity extends AppCompatActivity {

    CountDownTimer gameTimer;
    TextView problem;
    private int solution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);


        problem = findViewById(R.id.problemTextView);
        problem.setText(generateRandomProblem());

        TextView timerText = findViewById(R.id.timerTextView);
        startCountdown(timerText);

    }

    private String generateRandomProblem() {
        Random rand = new Random();
        int upperBound = 30;

        int randNum1 = rand.nextInt(upperBound);
        int randNum2 = rand.nextInt(upperBound);
        solution = randNum1 + randNum2;

        createAnswerChoices();

        return randNum1 + " + " + randNum2 + " = ?";
    }

    private void createAnswerChoices() {
        Button topLeft = findViewById(R.id.topLeftButton);
        Button topRight = findViewById(R.id.topRightButton);
        Button botLeft = findViewById(R.id.bottomLeftButton);
        Button botRight = findViewById(R.id.bottomRightButton);

        Random rand = new Random();
        int correctAnswerButton = rand.nextInt(4);

        switch (correctAnswerButton) {
            case 0:
                topLeft.setText(String.valueOf(solution));
                fillOtherButtons(topRight, botLeft, botRight, rand);
                break;
            case 1:
                topRight.setText(String.valueOf(solution));
                fillOtherButtons(topLeft, botLeft, botRight, rand);
                break;
            case 2:
                botLeft.setText(String.valueOf(solution));
                fillOtherButtons(topRight, topLeft, botRight, rand);
                break;
            case 3:
                botRight.setText(String.valueOf(solution));
                fillOtherButtons(topRight, botLeft, topLeft, rand);
                break;
        }
    }

    private void fillOtherButtons(Button btn1, Button btn2, Button btn3, Random rand) {
        int upperBound = 70;

        btn1.setText(String.valueOf(rand.nextInt(upperBound)));
        btn2.setText(String.valueOf(rand.nextInt(upperBound)));
        btn3.setText(String.valueOf(rand.nextInt(upperBound)));
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