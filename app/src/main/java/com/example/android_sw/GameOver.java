package com.example.android_sw;

import androidx.appcompat.app.AppCompatActivity; // 액션바 기능을 사용하는 액티비티를 위한 기본 클래스

import android.content.Intent;
import android.content.SharedPreferences;
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
        int scoreCount = getIntent().getExtras().getInt("score"); // 현 게임에서 달성한 점수
        SharedPreferences pref = getSharedPreferences("myStoragePreference", 0);
        int scoreBest = pref.getInt("scoreBest", 0); // 게임에서 달성한 최고 점수
        SharedPreferences.Editor edit = pref.edit();
        if(scoreCount > scoreBest){
            scoreBest = scoreCount;
            edit.putInt("scoreBest", scoreBest);
            edit.apply();
        }

        // 재시작 버튼 (클릭 시 메인화면으로 돌아감)
        mRestartButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent myIntent = new Intent(GameOver.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

        tScore = findViewById(R.id.scoreDisplay); // 점수를 보여주는 변수
        tBest = findViewById(R.id.BestDisplay); // 최고 점수를 보여주는 변수
        tScore.setText(""+scoreCount); // 현 점수 텍스트화
        tBest.setText(""+scoreBest); // 최고 점수 텍스트화
    }
}