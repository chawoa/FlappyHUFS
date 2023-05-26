package com.example.android_sw;

import androidx.appcompat.app.AppCompatActivity; // 액션바 기능을 사용하는 액티비티를 위한 기본 클래스

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {
    Button mRestartButton; // Button 타입 변수 선언
    TextView tScore,tBest; // TextView 타입 변수 선언

    /*
    1. 액티비티 초기화
    2. 컨텐트 뷰를 'activity_game_over.xml' 레이아웃 파일로 설정 (게임 종료 UI 정의)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) { // 액티비티 생성 시 호출
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        mRestartButton = findViewById(R.id.btnRestart);
        int scoreCount = getIntent().getExtras().getInt("score");

        ScoreCheck.updateBestScore(this, scoreCount);

        int[] bestScores = ScoreCheck.getBestScores(this);

        mRestartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(GameOver.this, MainActivity.class);
                startActivity(myIntent);
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