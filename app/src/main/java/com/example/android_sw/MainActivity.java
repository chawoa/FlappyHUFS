package com.example.android_sw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/*
1. 부모 클래스의 onCreate() 메소드 호출
2. 현재 액티비티에 표시할 레이아웃 설정 → activity_main 레이아웃 사용
3. 어플리케이션 컨텍스트를 AppHolder에 할당하는 메소드 호출 → 애플리케이션 젠체에 공유되는 객체와 데이터 관리
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppHolder.assign(this.getApplicationContext());
    }

    /*
    StartGame 버튼 클릭 시 호출되는 메소드 → GameActivity를 시작하고 현재의 MainActivity를 종료하는 동작 수행
     */
    public void startGame(View view){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();
    }
}