package com.example.android_sw;

import androidx.appcompat.app.AppCompatActivity; // 액션바 기능을 사용하는 액티비티를 위한 기본 클래스

import android.content.Intent; // 앱 구성 요소 간 통신 클래스
import android.os.Bundle; // 데이터 키-값 쌍의 형태로 저장 및 전달 클래스
import android.view.View; // 사용자 인터페이스 클래스
import android.view.WindowManager; // 앱의 창(window) 관리 클래스
import android.widget.Button; // 앱의 버튼 클래스
import android.widget.TextView; // 텍스트 표현 클래스

public class GameOver extends AppCompatActivity { // 게임 오버 액티비티
    Button mRestartButton; // 메인 화면으로 돌아가는 버튼
    TextView tScore, tBest;
    static int scoreCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        ExpStore.assign(this.getApplicationContext());
        mRestartButton = findViewById(R.id.btnRestart); // 메인 화면으로 돌아가는 버튼과 연결
        scoreCount = getIntent().getExtras().getInt("score"); // 현 점수 인텐트를 받아 변수에 저장

        // 전체 화면 모드로 변경
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // 상태 표시줄 숨기기
        getSupportActionBar().hide();

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
