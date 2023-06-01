package com.example.android_sw;

import static com.example.android_sw.ExpStore.LV;
import static com.example.android_sw.ExpStore.getPreviousExp;

import androidx.appcompat.app.AppCompatActivity; // 앱의 활동(Activity) 표현 클래스

import android.content.Intent; // 앱 구성 요소 간 통신 클래스
import android.os.Bundle; // 데이터 키-값 쌍의 형태로 저장 및 전달 클래스
import android.view.View; // 사용자 인터페이스 클래스
import android.view.WindowManager; // 앱의 창(window) 관리 클래스
import android.widget.ImageButton; // 이미지 표시 버튼을 표현하는 안드로이드 UI 요소
import android.graphics.Bitmap; // 비트맵 이미지 표현하는 안드로이드 그래픽스 클래스
import android.graphics.BitmapFactory; // 비트맵 이미지 디코딩하는 유틸리티 클래스
import android.content.res.Resources; // 앱 리소스 접근 클래스
import android.widget.ProgressBar; // 진행 상태를 시각적 표현하는 안드로이드 UI 요소
import android.widget.TextView; // 텍스트 표현 클래스

public class SelectChar extends AppCompatActivity {
    private ProgressBar expBar; // 경험치 바 객체 초기화
    public static int maxExp = 100; // 최대 경험치 값

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_char);
        updateChecking();

        // 전체 화면 모드로 변경
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // 상태 표시줄 숨기기
        getSupportActionBar().hide();

        // 이전 경험치 값 초기화
        int previousExp = getIntent().getIntExtra("previousExp", 0);
        ExpStore.setPreviousExp(previousExp); // 경험치 누적

        // expBar 초기화
        expBar = findViewById(R.id.expBar);

        // 경험치 바 & 레벨 업데이트
        updateExpBar();
        updateLevel();
    }

    private void updateExpBar() {
        float expPercentage = ExpStore.calculateExpPercentage(maxExp);
        int progress = Math.round(expPercentage);

        expBar.setMax(maxExp); // 최대 경험치 설정
        expBar.setProgress(progress); // 현재 경험치 설정 (백분율)
    }

    public void BackToMain(View view) { // 메인 화면으로 돌아가는 함수
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public static boolean Basic_selected = true; // 기본 캐릭터 boolean 값
    public static boolean Glasses_selected; // 수경 쓴 캐릭터 boolean 값
    public static boolean Others_selected; // ... 캐릭터 boolean 값
    public static Bitmap[] SwimChar = new Bitmap[3]; // 캐릭터 저장 할 임시 배열

    public void CheckChar(View view) { // 캐릭터 클릭 시 캐릭터들의 각 boolean 값들을 변경해주는 함수
        ImageButton clickedButton = (ImageButton) view;

        // 클릭된 버튼의 id 확인 후 boolean 값 변경
        if (clickedButton.getId() == R.id.Char_1) {
            Basic_selected = true;
            Glasses_selected = false;
            Others_selected = false;
        } else if (clickedButton.getId() == R.id.Char_2) {
            Basic_selected = false;
            Glasses_selected = true;
            Others_selected = false;
        } else if (clickedButton.getId() == R.id.Char_3) {
            Basic_selected = false;
            Glasses_selected = false;
            Others_selected = true;
        }

        updateChecking();
        Checker(getResources());
    }

    private void updateChecking() { // 선택시 체크 표시를 하는 함수
        // 이미지 버튼 상태 업데이트
        ImageButton button1 = findViewById(R.id.Char_1);
        ImageButton button2 = findViewById(R.id.Char_2);
        ImageButton button3 = findViewById(R.id.Char_3);

        button1.setImageResource(Basic_selected ? R.drawable.checking_img : R.drawable.student_1);
        button2.setImageResource(Glasses_selected ? R.drawable.checking_img : R.drawable.student_2);
        button3.setImageResource(Others_selected ? R.drawable.checking_img : R.drawable.student_3);
    }

    // boolean 값에 따른 캐릭터 변경하는 함수
    public void Checker(Resources res) {
        if (Basic_selected) {
            SwimChar[0] = BitmapFactory.decodeResource(res, R.drawable.student_1);
            SwimChar[1] = BitmapFactory.decodeResource(res, R.drawable.student_2);
            SwimChar[2] = BitmapFactory.decodeResource(res, R.drawable.student_3);
        } else if (Glasses_selected) {
            SwimChar[0] = BitmapFactory.decodeResource(res, R.drawable.student_1);
            SwimChar[1] = BitmapFactory.decodeResource(res, R.drawable.student_2);
            SwimChar[2] = BitmapFactory.decodeResource(res, R.drawable.student_3);
        } else if (Others_selected) {
            SwimChar[0] = BitmapFactory.decodeResource(res, R.drawable.student_1);
            SwimChar[1] = BitmapFactory.decodeResource(res, R.drawable.student_2);
            SwimChar[2] = BitmapFactory.decodeResource(res, R.drawable.student_3);
        } else {
            SwimChar[0] = BitmapFactory.decodeResource(res, R.drawable.student_1);
            SwimChar[1] = BitmapFactory.decodeResource(res, R.drawable.student_2);
            SwimChar[2] = BitmapFactory.decodeResource(res, R.drawable.student_3);
        }
    }

    // 배열의 값을 다른 클래스에 전달하기 위한 함수 선언
    public static Bitmap Image_Zero() {
        return SwimChar[0];
    }
    public static Bitmap Image_One() {
        return SwimChar[1];
    }
    public static Bitmap Image_Two() {
        return SwimChar[2];
    }

    public void updateLevel() {
        TextView nowLevel = findViewById(R.id.nowlevel);
        String nlevel = "Lv." + LV + "(" + getPreviousExp() + "/" + maxExp + ")";
        nowLevel.setText(nlevel);
    }
}