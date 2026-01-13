package org.athan.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CandidateActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        EdgeToEdge.enable (this);
        setContentView (R.layout.activity_candidate);
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextSurName = findViewById(R.id.editTextSurname);
        Button buttonStartTest = findViewById(R.id.button2);
        buttonStartTest.setOnClickListener (v -> {
            String name = editTextName.getText().toString();
            String surname = editTextSurName.getText ().toString ();
            if (name.isEmpty () || surname.isEmpty ()) {
                Toast.makeText (
                        CandidateActivity.this, "Παρακαλώ συμπλήρωσε όνομα και επώνυμο.", Toast.LENGTH_LONG)
                        .show ();
            }

            Intent intent = new Intent(CandidateActivity.this, QuizActivity.class);
            startActivity(intent);
        });
        ViewCompat.setOnApplyWindowInsetsListener (findViewById (R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets (WindowInsetsCompat.Type.systemBars ());
            v.setPadding (systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}