package org.athan.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {

    private MediaPlayer tickTockPlayer;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        EdgeToEdge.enable (this);
        setContentView (R.layout.activity_result);

        TextView textName = findViewById(R.id.textName);
        TextView textScore = findViewById(R.id.textScore);
        TextView textTime = findViewById(R.id.textTime);
        TextView textMessage = findViewById(R.id.textMessage);
        Button buttonRetry = findViewById(R.id.buttonRetry);

        tickTockPlayer = MediaPlayer.create(this, R.raw.results);
        tickTockPlayer.setVolume(0.2f, 0.2f);
        tickTockPlayer.start();




        SharedPreferences prefs = getSharedPreferences("quiz_prefs", MODE_PRIVATE);

        String name = prefs.getString("name", "");
        int score = prefs.getInt("score", 0);
        int total = prefs.getInt("total_questions", 0);
        String time = prefs.getString("time", "");

        textName.setText("Μπράβο, " + name + "!");
        textScore.setText("✅ Σκορ: " + score + " / " + total);
        textTime.setText("✅ Ώρα: " + time);

        if (score >= 5) {
            textMessage.setText("🔥 Εξαιρετική επίδοση!");
        } else if (score >= 3) {
            textMessage.setText("👏 Καλή προσπάθεια!");
        } else {
            textMessage.setText("\uD83D\uDCAA Θέλεις λίγο διάβασμα ακόμα!");
        }

        buttonRetry.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, CandidateActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP  | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener (findViewById (R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets (WindowInsetsCompat.Type.systemBars ());
            v.setPadding (systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}