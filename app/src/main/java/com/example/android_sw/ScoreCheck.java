package com.example.android_sw;

import androidx.appcompat.app.AppCompatActivity; // 앱의 활동(Activity) 표현 클래스

import android.content.Context; // 앱의 현재 상태와 관련된 정보 제공 패키지
import android.content.Intent; // 앱 구성 요소 간 통신 클래스
import android.content.SharedPreferences; // 데이터 영구 저장 및 검색 기능 제공
import android.os.Bundle; // 데이터 키-값 쌍의 형태로 저장 및 전달 클래스
import android.view.View; // 사용자 인터페이스 클래스
import android.view.WindowManager; // 앱의 창(window) 관리 클래스
import android.widget.TextView; // 텍스트 표현 클래스

public class ScoreCheck extends AppCompatActivity {

    TextView topScoresDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ExpStore.assign(this.getApplicationContext());
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
