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
    static int scoreCount;
    
    /**
     * 1. 액티비티 초기화
     * 2. 컨텐트 뷰를 'activity_game_over.xml' 레이아웃 파일로 설정 (게임 종료 UI 정의)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) { // 액티비티 생성 시 호출 (초기화 작업)
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over); // 액티비티 레이아웃을 activity_game_over로 설정
        mRestartButton = findViewById(R.id.btnRestart); // btnRestart를 아이디로 가지는 버튼 지정 변수
        scoreCount = getIntent().getExtras().getInt("score"); // 메인 액티비티에서 전달된 score(정수 데이터) 저장 변수

        ScoreCheck.updateBestScore(this, scoreCount); // 최고 점수 업데이트

        int[] bestScores = ScoreCheck.getBestScores(this); // 최고 점수 갱신 리스트

        mRestartButton.setOnClickListener(new View.OnClickListener() { // 클릭 리스너 설정
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(GameOver.this, MainActivity.class); // 게임 오버 → 메인 화면
                startActivity(myIntent);
                finish();
            }
        });

        tScore = findViewById(R.id.scoreDisplay); // 게임 오버 당시 내 점수
        tBest = findViewById(R.id.BestDisplay); // 역대 최고 점수
        tScore.setText(String.valueOf(scoreCount)); // scoreCount에 저장된 데이터를 String으로 형변환 후 텍스트 넘기기

        StringBuilder bestScoresText = new StringBuilder(); // 내용 변경 가능한 String 객체 선언
        bestScoresText.append(bestScores[0]); // 최고점을 해당 객체에 append
        tBest.setText(bestScoresText.toString()); // 점수를 보여주는 리스너에 해당 텍스트 넘기기
    }
}