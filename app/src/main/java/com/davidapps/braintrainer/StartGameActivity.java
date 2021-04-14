package com.davidapps.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class StartGameActivity extends AppCompatActivity {

    CountDownTimer gameTimer;
    TextView problem;
    TextView resultText;
    TextView score;

    Button playAgainButton;

    Button topLeft;
    Button topRight;
    Button botLeft;
    Button botRight;

    private int solution;
    private int correctButtonId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        resultText = findViewById(R.id.resultTextView);

        playAgainButton = findViewById(R.id.playAgainButton);

        topLeft = findViewById(R.id.topLeftButton);
        topRight = findViewById(R.id.topRightButton);
        botLeft = findViewById(R.id.bottomLeftButton);
        botRight = findViewById(R.id.bottomRightButton);

        problem = findViewById(R.id.problemTextView);
        problem.setText(generateRandomProblem());

        score = findViewById(R.id.scoreTextView);

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

        Random rand = new Random();
        int correctAnswerButton = rand.nextInt(4);

        switch (correctAnswerButton) {
            case 0:
                correctButtonId = R.id.topLeftButton;
                topLeft.setText(String.valueOf(solution));
                fillOtherButtons(topRight, botLeft, botRight, rand);
                break;
            case 1:
                correctButtonId = R.id.topRightButton;
                topRight.setText(String.valueOf(solution));
                fillOtherButtons(topLeft, botLeft, botRight, rand);
                break;
            case 2:
                correctButtonId = R.id.bottomLeftButton;
                botLeft.setText(String.valueOf(solution));
                fillOtherButtons(topRight, topLeft, botRight, rand);
                break;
            case 3:
                correctButtonId = R.id.bottomRightButton;
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
                disableOrEnableButtons(false);
                resultText.setText(R.string.game_over);
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    private void disableOrEnableButtons(boolean isEnabled) {
        topLeft.setEnabled(isEnabled);
        topRight.setEnabled(isEnabled);
        botLeft.setEnabled(isEnabled);
        botRight.setEnabled(isEnabled);
    }

    public void checkAnswer(View view) {
        int buttonPressed = view.getId();
        boolean correct = false;
        if (buttonPressed == correctButtonId) {
            //give them + 1 point, display correct in text view
            correct = true;
            resultText.setText(R.string.correct);
        } else {
            resultText.setText(R.string.wrong);
        }

        updateScoreboard(correct);
        problem.setText(generateRandomProblem());
    }

    private void updateScoreboard(boolean correct) {
        TextView score = findViewById(R.id.scoreTextView);

        String currScore = score.getText().toString();
        String[] splitScore = currScore.split("/");
        int currCorrect = Integer.parseInt(splitScore[0]);
        int currTotal = Integer.parseInt(splitScore[1]);

        if (correct) {
            currCorrect += 1;
        }
        ++currTotal;

        score.setText(currCorrect + "/" + currTotal);

    }

    public void playAgain(View view) {
        disableOrEnableButtons(true);
        playAgainButton.setVisibility(View.GONE);
        gameTimer.start();
        resultText.setText(R.string.begin);
        problem.setText(generateRandomProblem());
        score.setText(R.string._0_0);
    }
}