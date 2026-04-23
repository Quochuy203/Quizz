package com.example.quizz.model;

public class GameState {
    private int currentStep;   // 0=dialogue intro, 1=quiz, 2=dialogue mid, 3=fin
    private int quizScore;     // nombre de bonnes réponses quiz
    private int dialogueIndex; // index du dialogue actuel

    public GameState() {
        this.currentStep = 0;
        this.quizScore = 0;
        this.dialogueIndex = 0;
    }

    // Getters / Setters
    public int getCurrentStep() { return currentStep; }
    public void setCurrentStep(int s) { this.currentStep = s; }

    public int getQuizScore() { return quizScore; }
    public void setQuizScore(int s) { this.quizScore = s; }

    public int getDialogueIndex() { return dialogueIndex; }
    public void setDialogueIndex(int i) { this.dialogueIndex = i; }
}