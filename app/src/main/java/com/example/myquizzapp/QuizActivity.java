package com.example.myquizzapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.graphics.Color;
import android.content.Intent;



import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    private TextView textViewQuestionNumber, textViewQuestionTitle, textViewQuestion;
    private ProgressBar progressBar;
    private Button buttonSubmit, buttonNext;
    private RadioButton radioButtonAnswer1, radioButtonAnswer2, radioButtonAnswer3; // Declare answer radio buttons

    private Question[] questions;
    private int currentQuestionIndex;
    private int score = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Initialize views
        textViewQuestionNumber = findViewById(R.id.textViewQuestionNumber);
        textViewQuestionTitle = findViewById(R.id.textViewQuestionTitle);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        progressBar = findViewById(R.id.progressBar);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonNext = findViewById(R.id.buttonNext); // Initialize the Next button
        radioButtonAnswer1 = findViewById(R.id.radioButtonAnswer1); // Initialize answer radio buttons
        radioButtonAnswer2 = findViewById(R.id.radioButtonAnswer2);
        radioButtonAnswer3 = findViewById(R.id.radioButtonAnswer3);

        // Retrieve the user's name from the intent extras
        String userName = getIntent().getStringExtra("userName");

        // Initialize questions
        questions = new Question[]{
                new Question("Question 1 Title", "Question 1 Text", "Answer 1"),
                new Question("Question 2 Title", "Question 2 Text", "Answer 2"),
                new Question("Question 3 Title", "Question 3 Text", "Answer 3")
        };

        // Set up initial question
        currentQuestionIndex = 0;
        updateCurrentQuestion();

        // Set up onClickListener for submit button
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the selected answer
                String userAnswer = "";
                int selectedRadioButtonId = ((RadioGroup) findViewById(R.id.radioGroupAnswers)).getCheckedRadioButtonId();
                if (selectedRadioButtonId != -1) {
                    RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                    userAnswer = selectedRadioButton.getText().toString();
                }

                // Get the correct answer for the current question
                String correctAnswer = questions[currentQuestionIndex].getCorrectAnswer();

                // Check if the user's answer is correct
                if (userAnswer.equals(correctAnswer)) {
                    // Increment the score for correct answer
                    score++;
                }

                // Highlight correct answer in green and incorrect answers in red
                if (userAnswer.equals(correctAnswer)) {
                    // Highlight the correct answer in green
                    switch (userAnswer) {
                        case "Answer 1":
                            radioButtonAnswer1.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                            break;
                        case "Answer 2":
                            radioButtonAnswer2.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                            break;
                        case "Answer 3":
                            radioButtonAnswer3.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                            break;
                    }
                } else {
                    // Highlight the selected answer in red
                    switch (userAnswer) {
                        case "Answer 1":
                            radioButtonAnswer1.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                            break;
                        case "Answer 2":
                            radioButtonAnswer2.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                            break;
                        case "Answer 3":
                            radioButtonAnswer3.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                            break;
                    }
                    // Highlight the correct answer in green
                    switch (correctAnswer) {
                        case "Answer 1":
                            radioButtonAnswer1.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                            break;
                        case "Answer 2":
                            radioButtonAnswer2.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                            break;
                        case "Answer 3":
                            radioButtonAnswer3.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                            break;
                    }
                }

                // Disable further interaction with answer buttons and the submit button
                disableAnswerButtons();
                buttonSubmit.setEnabled(false);

                // Show the Next button
                buttonNext.setVisibility(View.VISIBLE);
            }
        });

        // Set up onClickListener for next button
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Reset UI for the next question
                resetUIForNextQuestion();
                // Move to the next question
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.length) {
                    updateCurrentQuestion();
                } else {
                    // Handle end of quiz
                    // All questions answered, show the final score
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    intent.putExtra("userName", userName); // Pass the user's name
                    intent.putExtra("score", score);
                    intent.putExtra("totalQuestions", questions.length);
                    startActivity(intent);
                    finish(); // Finish QuizActivity
                }
            }
        });


    }

    // Method to disable answer buttons
    private void disableAnswerButtons() {
        radioButtonAnswer1.setEnabled(false);
        radioButtonAnswer2.setEnabled(false);
        radioButtonAnswer3.setEnabled(false);
    }


    private void updateCurrentQuestion() {
        // Update UI with current question details
        Question currentQuestion = questions[currentQuestionIndex];
        updateQuestionUI(currentQuestionIndex + 1, questions.length, currentQuestion.getTitle(), currentQuestion.getText());
    }

    private void updateQuestionUI(int currentQuestion, int totalQuestions, String title, String question) {
        // Update question number
        textViewQuestionNumber.setText("Question " + currentQuestion + "/" + totalQuestions);

        // Update question title and text
        textViewQuestionTitle.setText(title);
        textViewQuestion.setText(question);

        // Update progress bar
        int progress = (int) (((float) currentQuestion / totalQuestions) * 100);
        progressBar.setProgress(progress);
    }

    // Method to reset UI for the next question
    private void resetUIForNextQuestion() {
        // Enable answer buttons for the next question
        radioButtonAnswer1.setEnabled(true);
        radioButtonAnswer2.setEnabled(true);
        radioButtonAnswer3.setEnabled(true);

        // Clear the selection in the RadioGroup
        ((RadioGroup) findViewById(R.id.radioGroupAnswers)).clearCheck();

        // Hide the Next button again
        buttonNext.setVisibility(View.GONE);

        // Enable the submit button
        buttonSubmit.setEnabled(true);

        // Remove highlighting of answers
        radioButtonAnswer1.setBackgroundColor(Color.TRANSPARENT);
        radioButtonAnswer2.setBackgroundColor(Color.TRANSPARENT);
        radioButtonAnswer3.setBackgroundColor(Color.TRANSPARENT);

        // Update UI with the next question details (replace this with your logic to fetch the next question)
        int currentQuestion = 2; // Assuming the next question index is 2
        int totalQuestions = 5; // Update with the total number of questions
        String questionTitle = "Sample Question Title 2"; // Update with the next question title
        String questionText = "Sample Question Text 2"; // Update with the next question text
        updateQuestionUI(currentQuestion, totalQuestions, questionTitle, questionText);
    }
}








