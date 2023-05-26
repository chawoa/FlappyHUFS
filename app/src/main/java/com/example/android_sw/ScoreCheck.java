package com.example.android_sw;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreCheck extends AppCompatActivity {

    TextView topScoresDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_check);

        topScoresDisplay = findViewById(R.id.topScoresDisplay);

        StringBuilder scores = new StringBuilder("Top Scores:\n\n");
        SharedPreferences pref = getSharedPreferences("myStoragePreference", 0);

        for (int i = 1; i <= 5; i++) {
            int score = pref.getInt("score" + i, 0);
            scores.append(i).append(". ").append(score).append("\n");
        }

        topScoresDisplay.setText(scores.toString());
    }

    public static void updateBestScore(Context context, int score) {
        SharedPreferences pref = context.getSharedPreferences("myStoragePreference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        int[] scores = getBestScores(context);

        for (int i = 0; i < scores.length; i++) {
            if (score > scores[i]) {
                for (int j = scores.length - 1; j > i; j--) {
                    scores[j] = scores[j - 1];
                }
                scores[i] = score;
                break;
            }
        }

        for (int i = 0; i < scores.length; i++) {
            editor.putInt("score" + (i + 1), scores[i]);
        }

        editor.apply();
    }

    public static int[] getBestScores(Context context) {
        SharedPreferences pref = context.getSharedPreferences("myStoragePreference", Context.MODE_PRIVATE);
        int[] scores = new int[5];

        for (int i = 0; i < scores.length; i++) {
            scores[i] = pref.getInt("score" + (i + 1), 0);
        }

        return scores;
    }

    public void BackToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}