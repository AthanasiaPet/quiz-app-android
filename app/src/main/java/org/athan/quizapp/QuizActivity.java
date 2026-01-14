package org.athan.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Collections;
import java.util.Locale;

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
    private TextView textViewTimer;
    private CountDownTimer countDownTimer;
    private static final long TOTAL_TIME = 60000;
    private String candidateName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.util.Log.e("QUIZ_FORCE", "ONCREATE STARTED");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);




        textViewQuestion = findViewById(R.id.textViewQuestion);
        radioGroupOptions = findViewById(R.id.radioGroupOptions);
        buttonNext = findViewById(R.id.buttonNext);
        imageQuestion = findViewById(R.id.imageQuestion);
        textViewTimer = findViewById(R.id.textViewTimer);
        candidateName = getIntent().getStringExtra("candidate_name");


        startTimer();


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
                finishQuiz ();
            }


        });
    }

    private void showQuestion(Questions question) {

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

    private void startTimer() {

        countDownTimer = new CountDownTimer(TOTAL_TIME, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000; //millisecond to seconds
                textViewTimer.setText("Χρόνος: " + seconds);
            }

            @Override
            public void onFinish() {
                textViewTimer.setText("Τέλος χρόνου!");
                Toast.makeText(QuizActivity.this,
                        "Τέλος χρόνου!",
                        Toast.LENGTH_LONG).show();

                finishQuiz();
            }
        };

        countDownTimer.start();
    }

    private void finishQuiz() {
        SharedPreferences prefs = getSharedPreferences("quiz_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("name", candidateName);
        editor.putInt("score", score);
        editor.putString("time", new SimpleDateFormat ("HH:mm", Locale.getDefault()).format(new Date ()));
        editor.putInt("total_questions", questions.size());


        editor.apply();

        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
        startActivity(intent);


        finish();
    }

}
