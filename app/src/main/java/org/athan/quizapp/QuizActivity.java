package org.athan.quizapp;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        EdgeToEdge.enable (this);
        setContentView (R.layout.activity_quiz);

        List<Questions> questions = QuestionsRepository.loadQuestions (this);
        if (!questions.isEmpty ()) {
            Questions firstQuestion = questions.get(0);

            TextView textView = findViewById(R.id.textViewQuestion);
            textView.setText(firstQuestion.getText());

            RadioGroup radioGroup = findViewById(R.id.radioGroupOptions);
            radioGroup.removeAllViews();

            for (int i = 0; i < firstQuestion.getOptions().size(); i++) {
                RadioButton radioButton = new RadioButton(this);
                radioButton.setText(firstQuestion.getOptions().get(i));
                radioGroup.addView(radioButton);
            }

        }

        ViewCompat.setOnApplyWindowInsetsListener (findViewById (R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets (WindowInsetsCompat.Type.systemBars ());
            v.setPadding (systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}