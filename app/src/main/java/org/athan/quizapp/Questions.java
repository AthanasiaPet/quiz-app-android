package org.athan.quizapp;

import java.util.List;

public class Questions {

    private String text;
    private List<String> options;
    private int correctIndex;
    private String image;


    public Questions(String text, List<String> options, int correctIndex, String image) {
        this.text = text;
        this.options = options;
        this.correctIndex = correctIndex;
        this.image = image;
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

    public String getImage() {
        return image;
    }

}

