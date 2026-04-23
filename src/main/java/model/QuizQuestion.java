package com.example.quizz.model;

import java.util.List;

public class QuizQuestion {
    private String question;
    private String correctAnswer;
    private List<String> incorrectAnswers;
    private List<String> allAnswers; // mélangées

    public QuizQuestion(String question, String correctAnswer, List<String> incorrectAnswers) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;

        // Mélanger toutes les réponses
        this.allAnswers = new java.util.ArrayList<>(incorrectAnswers);
        this.allAnswers.add(correctAnswer);
        java.util.Collections.shuffle(this.allAnswers);
    }

    public String getQuestion() { return question; }
    public String getCorrectAnswer() { return correctAnswer; }
    public List<String> getAllAnswers() { return allAnswers; }
}