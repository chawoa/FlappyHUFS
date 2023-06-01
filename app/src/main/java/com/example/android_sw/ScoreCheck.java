package com.example.android_sw;

import android.content.Context;
import android.content.Intent;

/**
 * 안드로이드에서 데이터 영구 저장 및 검색 기능 제공 → 키-값 쌍으로 데이터 저장 및 다른 구성 요소 간 데이터 공유 가능
 * 주로 사용자 설정, 게임 점수, 애플리케이션 상태 등의 저장에 사용
 */
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreCheck extends AppCompatActivity {

    TextView topScoresDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_check);

        // 전체 화면 모드로 변경
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // 상태 표시줄 숨기기
        getSupportActionBar().hide();

        topScoresDisplay = findViewById(R.id.topScoresDisplay);

        StringBuilder scores = new StringBuilder("Top Scores:\n");
        SharedPreferences pref = getSharedPreferences("myStoragePreference", 0);

        for (int i = 1; i <= 5; i++) {
            int score = pref.getInt("score" + i, 0);
            scores.append(i).append(". ").append(score).append("\n\n");
        }

        topScoresDisplay.setText(scores.toString());
    }

    /**
     * 최고 점수 업데이트 메소드
     * @param context 애플리케이션 컨텍스트
     * @param score 업데이트할 점수
     */
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

    /**
     * 최고 점수 배열 게터 메소드
     * @param context 애플리케이션 컨텍스트
     * @return 최고 점수 배열
     */
    public static int[] getBestScores(Context context) {
        SharedPreferences pref = context.getSharedPreferences("myStoragePreference", Context.MODE_PRIVATE);
        int[] scores = new int[5];

        for (int i = 0; i < scores.length; i++) {
            scores[i] = pref.getInt("score" + (i + 1), 0);
        }

        return scores;
    }

    /**
     * 메인 화면으로 돌아가는 메소드
     * @param view 클릭된 뷰
     */
    public void BackToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
