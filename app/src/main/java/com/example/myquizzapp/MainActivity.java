// MainActivity.java
package com.example.myquizzapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        buttonStart = findViewById(R.id.buttonStart);

        // Get the username passed from ResultActivity
        String userName = getIntent().getStringExtra("userName");

        // Set the username in the EditText
        editTextName.setText(userName);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the user's name from the EditText
                String userName = editTextName.getText().toString().trim();

                // Check if the name is not empty
                if (!userName.isEmpty()) {
                    // Start the QuizActivity and pass the user's name
                    Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                    intent.putExtra("userName", userName);
                    startActivity(intent);
                }
            }
        });
    }
}












