package org.athan.quizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import android.util.Log;




public class QuizActivity extends AppCompatActivity {

    private int selectedAnswerIndex = -1;
    private int currentQuestionIndex = 0;
    private int score = 0;

    private List<Questions> questions;

    private TextView textViewQuestion;
    private RadioGroup radioGroupOptions;
    private Button buttonNext;
    private ImageView imageQuestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.util.Log.e("QUIZ_FORCE", "ONCREATE STARTED");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);

        Log.d("QUIZ_LOG", "after setContentView");


        textViewQuestion = findViewById(R.id.textViewQuestion);
        radioGroupOptions = findViewById(R.id.radioGroupOptions);
        buttonNext = findViewById(R.id.buttonNext);
        imageQuestion = findViewById(R.id.imageQuestion);
        Log.d("QUIZ_LOG", "imageQuestion = " + imageQuestion);



        questions = QuestionsRepository.loadQuestions(this);
        Collections.shuffle (questions);
        if (questions.size() > 6) {
            questions = new ArrayList<> (questions.subList(0, 6));
        }

        if (questions.isEmpty()) {
            textViewQuestion.setText("Δεν υπάρχουν ερωτήσεις");
            return;
        }

        // Show First Question
        showQuestion(questions.get(currentQuestionIndex));


        radioGroupOptions.setOnCheckedChangeListener((group, checkedId) -> {
            selectedAnswerIndex = checkedId;
        });


        buttonNext.setOnClickListener(v -> {

            if (selectedAnswerIndex == -1) {
                Toast.makeText(this, "Διάλεξε απάντηση", Toast.LENGTH_SHORT).show();
                return;
            }

            if (selectedAnswerIndex ==
                    questions.get(currentQuestionIndex).getCorrectIndex()) {
                score++;
            }

            currentQuestionIndex++;

            if (currentQuestionIndex < questions.size()) {
                showQuestion(questions.get(currentQuestionIndex));
            } else {
                Toast.makeText(
                        this,
                        "Τέλος τεστ! Σκορ: " + score + "/" + questions.size(),
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }

    private void showQuestion(Questions question) {
        Log.d("QUIZ_LOG", "showQuestion called");
        Log.d("QUIZ_LOG", "imageQuestion inside showQuestion = " + imageQuestion);
        Log.d("QUIZ_LOG", "image from question = " + question.getImage());


        textViewQuestion.setText(question.getText());

        if (question.getImage() != null && !question.getImage().isEmpty()) {


            int imageResId = getResources().getIdentifier(
                    question.getImage(),
                    "drawable",
                    getPackageName()
            );


            if (imageResId != 0) {
                imageQuestion.setImageResource(imageResId);
                imageQuestion.setVisibility(View.VISIBLE);
            } else {
                imageQuestion.setVisibility(View.INVISIBLE);
            }

        } else {
            imageQuestion.setVisibility(View.INVISIBLE);
        }

        radioGroupOptions.removeAllViews();
        radioGroupOptions.clearCheck();
        selectedAnswerIndex = -1;

        for (int i = 0; i < question.getOptions().size(); i++) {

            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(question.getOptions().get(i));
            radioButton.setId(i);
            radioButton.setTextSize(18);

            radioGroupOptions.addView(radioButton);


        }


    }
}
