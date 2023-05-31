package com.example.android_sw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.res.Resources;
import android.widget.ProgressBar;

public class SelectChar extends AppCompatActivity {
    private ProgressBar expBar;
    private int maxExp = 100; // 최대 경험치 값

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_char); // 현재 액티비티에 표시할 레이아웃 설정
        updateChecking(); // boolean true값을 체크 표시하는 함수

        // expBar 초기화
        expBar = findViewById(R.id.expBar);

        // 경험치 바 업데이트
        updateExpBar();
    }

    private void updateExpBar() {
        float expPercentage = ExpStore.calculateExpPercentage(maxExp);
        int progress = Math.round(expPercentage);

        expBar.setMax(maxExp);
        expBar.setProgress(progress);
    }

    public void BackToMain(View view){ // 메인 화면으로 돌아가는 함수
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    public static boolean Basic_selected = true; // 기본 캐릭터 boolean 값
    public static boolean Glasses_selected; // 수경 쓴 캐릭터 boolean 값
    public static boolean Others_selected; // ... 캐릭터 boolean 값
    public static Bitmap[] SwimChar = new Bitmap[3]; // 캐릭터 저장 할 임시 배열


    // 캐릭터 클릭 시 캐릭터들의 각 boolean 값들을 변경해주는 함수
    public void CheckChar(View view){
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

    // 선택시 체크표시를 하는 함수
    private void updateChecking(){
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
        }else{
            SwimChar[0] = BitmapFactory.decodeResource(res, R.drawable.student_1);
            SwimChar[1] = BitmapFactory.decodeResource(res, R.drawable.student_2);
            SwimChar[2] = BitmapFactory.decodeResource(res, R.drawable.student_3);
        }
    }

    // 배열의 값을 다른 클래스에 전달하기 위한 함수 선언
    public static Bitmap Image_Zero(){
        return SwimChar[0];
    }
    public static Bitmap Image_One(){
        return SwimChar[1];
    }
    public static Bitmap Image_Two(){
        return SwimChar[2];
    }
}