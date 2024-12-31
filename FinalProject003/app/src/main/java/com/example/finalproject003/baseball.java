package com.example.finalproject003;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Random;

public class baseball extends AppCompatActivity {

    private EditText guessNumberEditText;
    private Button submitButton;
    private TextView resultTextView;
    private Button backButton;
    private ImageView baseballImage;

    private int answerNumber;
    private int attemptCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baseball);

        guessNumberEditText = findViewById(R.id.guess_number_edittext1);
        submitButton = findViewById(R.id.submit_button1);
        backButton = findViewById(R.id.back_button);
        baseballImage = findViewById(R.id.baseball_image);

        generateBaseballAnswer(); // 숫자 생성

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String guessNumberStr = guessNumberEditText.getText().toString();
                if (guessNumberStr.isEmpty()) {
                    Toast.makeText(baseball.this, "숫자를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                int guessNumber = Integer.parseInt(guessNumberStr);
                checkBaseballGuess(guessNumber);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent mainIntent = new Intent(baseball.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });
    }

    private void generateBaseballAnswer() {
        Random random = new Random();
        answerNumber = random.nextInt(1000) + 100;
    }

    private void checkBaseballGuess(int guessNumber) {
        String guessStr = String.valueOf(guessNumber);
        String answerStr = String.valueOf(answerNumber);
        int strikeCount = 0;
        int ballCount = 0;

        for (int i = 0; i < guessStr.length(); i++) {
            char guessDigit = guessStr.charAt(i);
            for (int j = 0; j < answerStr.length(); j++) {
                char answerDigit = answerStr.charAt(j);
                if (guessDigit == answerDigit) {
                    if (i == j) {
                        strikeCount++;
                    } else {
                        ballCount++;
                    }
                    break;
                }
            }
        }

        attemptCount++;

        String result = "Strike: " + strikeCount + "  Ball: " + ballCount;
        Toast.makeText(baseball.this, result , Toast.LENGTH_SHORT).show();

        if (strikeCount == 3) {
            showWinDialog();
            resetGame();
        } else if (attemptCount >= 10) {
            showGameOverDialog();
            resetGame();
        } else {
            guessNumberEditText.setText("");
        }
    }

    private void showWinDialog() {
        AlertDialog.Builder homerun = new AlertDialog.Builder(baseball.this);
        homerun.setTitle("정답입니다.");
        homerun.setMessage(attemptCount + " 번 시도만에 맞추셨습니다");
        homerun.setIcon(R.drawable.homerun);
        homerun.setPositiveButton("확인", null);
        ImageView homerunImage = new ImageView(baseball.this);
        homerunImage.setImageResource(R.drawable.homerun);
        homerun.setView(homerunImage);

        homerun.show();
    }

    private void showGameOverDialog() {
        AlertDialog.Builder out = new AlertDialog.Builder(baseball.this);
        out.setTitle("게임 오버!");
        out.setIcon(R.drawable.out);
        out.setMessage(attemptCount + " 다시 도전해 보세요.");
        out.setPositiveButton("확인", null);
        ImageView gameOverImage = new ImageView(baseball.this);
        gameOverImage.setImageResource(R.drawable.out);
        out.setView(gameOverImage);

        out.show();
    }

    private void resetGame() {
        generateBaseballAnswer();
        attemptCount = 0;
    }
}
