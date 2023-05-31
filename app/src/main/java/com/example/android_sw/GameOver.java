package com.example.android_sw;

import androidx.appcompat.app.AppCompatActivity; // 액션바 기능을 사용하는 액티비티를 위한 기본 클래스

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class GameOver extends AppCompatActivity {
    Button mRestartButton;
    TextView tScore, tBest;
    static int scoreCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        mRestartButton = findViewById(R.id.btnRestart);
        scoreCount = getIntent().getExtras().getInt("score");

        // 이전 경험치에 점수를 누적시킴
        ExpStore.addExp(scoreCount);

        ScoreCheck.updateBestScore(this, scoreCount);

        int[] bestScores = ScoreCheck.getBestScores(this);

        mRestartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(GameOver.this, MainActivity.class);
                startActivity(myIntent);
                finish();
            }
        });

        tScore = findViewById(R.id.scoreDisplay);
        tBest = findViewById(R.id.BestDisplay);
        tScore.setText(String.valueOf(scoreCount));

        StringBuilder bestScoresText = new StringBuilder();
        bestScoresText.append(bestScores[0]);
        tBest.setText(bestScoresText.toString());
    }
}
