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

public class updown extends AppCompatActivity {

    private EditText guessNumberEditText;
    private Button submitButton;
    private TextView resultTextView;
    private Button backButton;
    private ImageView updownImage;

    private int answerNumber;
    private int attemptCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updown);

        guessNumberEditText = findViewById(R.id.guess_number_edittext2);
        submitButton = findViewById(R.id.submit_button2);
        backButton = findViewById(R.id.back_button);
        updownImage = findViewById(R.id.updown_image);

        generateUpDownAnswer();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String guessNumberStr = guessNumberEditText.getText().toString();
                if (guessNumberStr.isEmpty()) {
                    Toast.makeText(updown.this, "숫자를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                int guessNumber = Integer.parseInt(guessNumberStr);
                checkUpDownGuess(guessNumber);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent mainIntent = new Intent(updown.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });
    }

    private void generateUpDownAnswer() {
        Random random = new Random();
        answerNumber = random.nextInt(100) + 1;
    }

    private void checkUpDownGuess(int guessNumber) {
        attemptCount++;

        if (guessNumber < answerNumber) {
            Toast.makeText(updown.this, "Up!", Toast.LENGTH_SHORT).show();
        } else if (guessNumber > answerNumber) {
            Toast.makeText(updown.this, "Down!", Toast.LENGTH_SHORT).show();
        } else {
            AlertDialog.Builder correctDialog = new AlertDialog.Builder(updown.this);
            correctDialog.setTitle("정답입니다!");
            correctDialog.setMessage("시도 횟수: " + attemptCount);
            correctDialog.setPositiveButton("확인", null);
            ImageView correctImage = new ImageView(updown.this);
            correctImage.setImageResource(R.drawable.jungdap);
            correctDialog.setView(correctImage);

            correctDialog.show();
            resetGame();
        }

        if (attemptCount >= 10) {
            AlertDialog.Builder gameOverDialog = new AlertDialog.Builder(updown.this);
            gameOverDialog.setTitle("게임 오버!");
            gameOverDialog.setMessage("정답은 " + answerNumber + "입니다.");
            gameOverDialog.setPositiveButton("확인", null);

            ImageView gameOverImage = new ImageView(updown.this);
            gameOverImage.setImageResource(R.drawable.ohdap);
            gameOverDialog.setView(gameOverImage);

            gameOverDialog.show();
            resetGame();
        }

    }

    private void resetGame() {
        guessNumberEditText.setText("");
        attemptCount = 0;
    }
}
