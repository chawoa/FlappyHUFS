package com.example.android_sw;

import android.content.Context; // 앱의 현재 상태와 관련된 정보 제공 패키지
import android.view.MotionEvent; // 화면에서의 터치, 제스처 등 동작 이벤트 클래스
import android.view.SurfaceHolder; // 서피스에 대한 액세스, 제어를 제공하는 콜백 인터페이스
import android.view.SurfaceView; // 별도 스레드에서 그림을 그리기 위한 전용 그리기 표면 제공 뷰

import androidx.annotation.NonNull; // 해당 요소가 null 값을 허용하지 않음을 나타내는 주석

public class GamePlay extends SurfaceView implements SurfaceHolder.Callback {
    MainThread mainThread; // MainThread 타입 변수
    public GamePlay(Context context) {
        super(context); // Context의 부모 클래스 상속
        SurfaceHolder myHolder = getHolder(); // SurfaceHolder 타입의 myHolder 객체 생성
        myHolder.addCallback(this); // 객체에 대한 콜백을 등록 → 이벤트 수신
        mainThread = new MainThread(myHolder);
    }

    /**
     MainThread의 실행 상태 설정 및 스레드 시작 → (SurfaceView가 생성 되었을 시 호출)
     */
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) { // SurfaceView 최초 생성 시 호출
        mainThread.setIsRunning(true);
        mainThread.start();
    }

    /**
     SurfaceView의 크기나 형식이 변경 되었을 시 호출
     */
    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {}

    /**
     SurfaceView가 파괴되기 전 호출 → 스레드 실행 중단 및 종료될 때까지 대기
     (그리기 작업 중지, 리소스 해제 등)
     */
    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        boolean retry = true; // while문의 실행과 종료를 결정하기 위해 불린값 저장 변수 설정
        while (retry) {
            try {
                mainThread.setIsRunning(false);
                mainThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;

        }
    }

    /**
     * <터치 이벤트 처리 메소드>
     * 1. gameState == 0일 시 → 게임 시작 & 소리 재생
     * 2. gameState != 0일 시 → 플레이어 캐릭터의 속도 설정 & 점프 동작 수행
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (AppHolder.getGameManager().gameState == 0) {
            AppHolder.getGameManager().gameState = 1;
            AppHolder.getSoundPlay().playSwoosh();
        } else {
            AppHolder.getSoundPlay().playArm();
        }

        AppHolder.getGameManager().swimmingChar.setVelocity(AppHolder.JUMP_VELOCITY);
        return true;
    }
}