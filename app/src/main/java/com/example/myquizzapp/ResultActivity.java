// ResultActivity.java
package com.example.myquizzapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView textViewScore;
    private Button buttonTakeNewQuiz, buttonFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewScore = findViewById(R.id.textViewScore);
        buttonTakeNewQuiz = findViewById(R.id.buttonTakeNewQuiz);
        buttonFinish = findViewById(R.id.buttonFinish);

        // Get the score from the intent
        int score = getIntent().getIntExtra("score", 0);
        int totalQuestions = getIntent().getIntExtra("totalQuestions", 0);

        // Get the user's name from the intent
        final String userName = getIntent().getStringExtra("userName");

        // Display the score
        textViewScore.setText("Congratulations, " + userName + "! Your Score: " + score + "/" + totalQuestions);

        buttonTakeNewQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start MainActivity to take a new quiz and pass the username back
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                intent.putExtra("userName", userName);
                startActivity(intent);
                finish(); // Finish ResultActivity
            }
        });

        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close the app
                finishAffinity(); // Finish all activities in the task
            }
        });
    }
}



