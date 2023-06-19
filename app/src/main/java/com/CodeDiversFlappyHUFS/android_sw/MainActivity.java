package com.CodeDiversFlappyHUFS.android_sw;

import androidx.appcompat.app.AlertDialog; // 대화상자(dialog) 표현 클래스
import androidx.appcompat.app.AppCompatActivity; // 앱의 활동(Activity) 표현 클래스

import android.content.DialogInterface; // 다이얼로그 상호작용에 대한 콜백 메서드 정의 인터페이스
import android.content.Intent; // 앱 구성 요소 간 통신 클래스
import android.graphics.Bitmap; // 비트맵 이미지 표현하는 안드로이드 그래픽스 클래스
import android.os.Bundle; // 데이터 키-값 쌍의 형태로 저장 및 전달 클래스
import android.view.View; // 사용자 인터페이스 클래스
import android.view.WindowManager; // 앱의 창(window) 관리 클래스
import android.widget.Button; // 앱의 버튼 클래스
import android.widget.ImageView; // Android 이미지 표시 위젯

import com.example.android_sw.R;

/**
 * 1. 부모 클래스의 onCreate() 메소드 호출
 * 2. 현재 액티비티에 표시할 레이아웃 설정 → activity_main 레이아웃 사용
 * 3. 경험치 컨텍스트를 AppHolder에 할당하는 메소드 호출 → 애플리케이션 전체에 공유되는 객체와 데이터 관리
 * 4. 어플리케이션 컨텍스트를 AppHolder에 할당하는 메소드 호출 → 애플리케이션 전체에 공유되는 객체와 데이터 관리
 */
public class MainActivity extends AppCompatActivity {
    Button exitBtn; // 종료 버튼 객체 초기화
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExpStore.assign(this.getApplicationContext());
        AppHolder.assign(this.getApplicationContext());

        // 전체 화면 모드로 변경
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        // 상태 표시줄 숨기기
        getSupportActionBar().hide();

        exitBtn = (Button) findViewById(R.id.exitBtn); // xml파일의 종료 버튼과 연결
        exitBtn.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("정말로 종료하시겠습니까?");
            builder.setTitle("종료 알림창")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() { // 'Yes' 버튼 클릭 시 앱 종료
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() { // 'No' 버튼 클릭 시 앱 종료 X
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.setTitle("종료 알림창");
            alert.show();
        });

        updateMainChar();
    }

    public void updateMainChar() { // 선택된 캐릭터 메인 화면에 업데이트
        ImageView mainchar = findViewById(R.id.mainChar);
        Bitmap currchar = SelectChar.Image_Zero();
        mainchar.setImageBitmap(currchar);
    }

    /**
     * StartGame 버튼 클릭 시 호출되는 메소드 → GameActivity를 시작하고 현재의 MainActivity를 종료하는 동작 수행
     */
    public void startGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 기록 버튼 클릭 시 호출되는 메소드 → GameActivity를 시작하고 현재의 MainActivity를 종료하는 동작 수행
     */
    public void scoreCheck(View view) {
        Intent intent = new Intent(this, ScoreCheck.class);
        startActivity(intent);
        finish();
    }

    /**
     * 캐릭터 선택 버튼 클릭 시 호출되는 메소드 → GameActivity를 시작하고 현재의 MainActivity를 종료하는 동작 수행
     */
    public void selectCharacter(View view) {
        Intent intent = new Intent(this, SelectChar.class);
        intent.putExtra("previousExp", ExpStore.getPreviousExp());
        startActivity(intent);
        finish();
    }
}