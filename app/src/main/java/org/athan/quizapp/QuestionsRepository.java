package org.athan.quizapp;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class QuestionsRepository {

    public static List<Questions> loadQuestions(Context context) {
        List<Questions> questions = new ArrayList<> ();

        try {
            InputStream inputStream = context.getAssets().open("questions.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String json = new String(buffer, "UTF-8");

            JSONObject root = new JSONObject(json);
            JSONArray questionsArray = root.getJSONArray("questions");

            for (int i = 0; i < questionsArray.length(); i++) {

                JSONObject q = questionsArray.getJSONObject(i);

                String text = q.getString("text");

                JSONArray optionsJson = q.getJSONArray("options");
                List<String> options = new ArrayList<>();

                for (int j = 0; j < optionsJson.length(); j++) {
                    options.add(optionsJson.getString(j));
                }

                int correctIndex = q.getInt("correctIndex");

                questions.add(new Questions(text, options, correctIndex));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return questions;
    }

}

