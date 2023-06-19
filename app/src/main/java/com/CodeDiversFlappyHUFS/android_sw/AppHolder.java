package com.CodeDiversFlappyHUFS.android_sw;

import android.content.Context; // 앱의 현재 상태와 관련된 정보를 제공하는데 사용하는 패키지
import android.util.DisplayMetrics; // 디바이스의 디스플레이 메트릭스 정보를 사용하기 위한 패키지 (화면 크기, 해상도, DPI)
import android.view.Display; // 현재 디스플레이에 대한 정보와 설정을 가져오는 클래스
import android.view.WindowManager; // 디바이스의 윈도우 관련 정보 제공 및 윈도우 크기, 레이아웃 매개변수 설정 수행 클래스

public class AppHolder { // 앱 실행을 위한 주요 정보 제공 클래스
    static BitmapControl bitmapControl; // BitmapControl 클래스 객체
    static GameManager gameManager; // GameManager 클래스 객체
    static int SCRN_WIDTH_X; // 게임 화면 너비 객체
    static int SCRN_HEIGHT_Y; // 게임 화면 높이 객체
    static int gravityPull; // 새가 떨어지는 정도
    static int JUMP_VELOCITY; // 점프 속도 설정 변수
    static int obstacleGap; // 상하단 장애물 사이의 거리
    static int obstacle_numbers; // 장애물 개수
    static int obstacleVelocity; // 장애물 이동 속도
    static int minimumObstacleCollection_Y; // 최소 크기 장애물 설정 변수
    static int maximumObstacleCollection_Y; // 최대 크기 장애물 설정 변수
    static int obstacleDistance; // 좌우 장애물 사이의 거리
    static Context gameActivityContext; // 게임 화면에 보이는 텍스트 객체
    static SoundPlayer soundPlay; // 캐릭터 효과음 클래스 객체

    public static void assign(Context context) { // 앱에 필요한 정보를 불러오는 메소드
        mapScreenSize(context);
        bitmapControl = new BitmapControl(context.getResources());
        holdGameVariables();
        gameManager = new GameManager();
        soundPlay = new SoundPlayer(context);

    }

    public static SoundPlayer getSoundPlay(){ // 게임 내 효과음 게터 메소드
        return soundPlay;
    }


    public static void holdGameVariables() { // 게임에 사용되는 상수 변수 생성자 (AppHolder 클래스 객체 내부 변수)
        AppHolder.gravityPull = 5;
        AppHolder.JUMP_VELOCITY = -50;
        AppHolder.obstacleGap = 700;
        AppHolder.obstacle_numbers = 2;
        AppHolder.obstacleVelocity = 24;
        AppHolder.minimumObstacleCollection_Y = (int)(AppHolder.obstacleGap / 2.0);
        AppHolder.maximumObstacleCollection_Y = AppHolder.SCRN_HEIGHT_Y - AppHolder.minimumObstacleCollection_Y - AppHolder.obstacleGap;
        AppHolder.obstacleDistance = AppHolder.SCRN_WIDTH_X * 5 / 6;
    }

    public static BitmapControl getBitmapControl() { // 비트맵 제어 변수 게터 메소드
        return bitmapControl;
    }
    public static GameManager getGameManager() { // 게임 관리 객체 게터 메소드
        return gameManager;
    }

    private static void mapScreenSize(Context context) { // 화면 크기 설정 생성자
        WindowManager manager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE); // 윈도우 관리자를 가져와 manager라는 변수에 저장
        Display dsp = manager.getDefaultDisplay(); // 기본 디스플레이 호출 후 dsp라는 Display 객체 반환
        DisplayMetrics dMetrics = new DisplayMetrics(); // DisplayMatrics 객체인 dMetrics 반환
        dsp.getMetrics(dMetrics); // dMetrics 호출 후 해당 정보를 dsp 객체에 저장
        int width = dMetrics.widthPixels; // 화면의 너비 데이터
        int height = dMetrics.heightPixels; // 화면의 높이 데이터
        AppHolder.SCRN_WIDTH_X = width; // 위의 width 변수의 데이터를 화면 너비로 사용
        AppHolder.SCRN_HEIGHT_Y = height; // 위의 height 변수의 데이터를 화면 높이로 사용
    }
}