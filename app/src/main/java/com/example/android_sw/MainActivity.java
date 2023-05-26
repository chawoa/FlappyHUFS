package com.example.android_sw;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
1. 부모 클래스의 onCreate() 메소드 호출
2. 현재 액티비티에 표시할 레이아웃 설정 → activity_main 레이아웃 사용
3. 어플리케이션 컨텍스트를 AppHolder에 할당하는 메소드 호출 → 애플리케이션 젠체에 공유되는 객체와 데이터 관리
 */
public class MainActivity extends AppCompatActivity {
    Button exitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppHolder.assign(this.getApplicationContext());

        exitBtn = (Button) findViewById(R.id.exitBtn);
        exitBtn.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("정말로 종료하시겠습니까?");
            builder.setTitle("종료 알림창")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.setTitle("종료 알림창");
            alert.show();
        });
    }

    /*
    StartGame 버튼 클릭 시 호출되는 메소드 → GameActivity를 시작하고 현재의 MainActivity를 종료하는 동작 수행
     */
    public void startGame(View view){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();
    }

    public void startCharacter(View view) {
        Intent intent = new Intent(this, SelectChar.class);
        startActivity(intent);
        finish();
    }

    public void scoreCheck(View view) {
        Intent intent = new Intent(this, ScoreCheck.class);
        startActivity(intent);
        finish();
    }
}