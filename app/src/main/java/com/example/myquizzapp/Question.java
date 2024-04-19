package com.example.myquizzapp;

public class Question {
    private String title;
    private String text;
    private String correctAnswer;

    public Question(String title, String text, String correctAnswer) {
        this.title = title;
        this.text = text;
        this.correctAnswer = correctAnswer;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}

