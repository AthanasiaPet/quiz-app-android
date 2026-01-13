package org.athan.quizapp;

import java.util.List;

public class Questions {

    private String text;
    private List<String> options;
    private int correctIndex;

    public Questions(String text, List<String> options, int correctIndex) {
        this.text = text;
        this.options = options;
        this.correctIndex = correctIndex;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }
}

