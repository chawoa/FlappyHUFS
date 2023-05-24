package com.example.android_sw;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class GameActivity extends Activity {
    GamePlay gamePlay;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // 부모 클래스의 onCreate() 메소드 호출
        AppHolder.gameActivityContext = this; // 현재 GameActivity의 컨텍스트를 AppHolder에 할당 → AppHolder를 통해 GameActivity에 접근 가능
        gamePlay = new GamePlay(this); // GamePlay 객체 생성
        setContentView(gamePlay); // 액티비티에 gamePlay를 설정하여 해당 SurfaceView를 화면에 표시 → GamePlay를 게임 플레이를 위한 화면으로 사용
    }
}
