package com.CodeDiversFlappyHUFS.android_sw;

import android.content.res.Resources; // 앱 리소스 접근 클래스
import android.graphics.Bitmap; // 비트맵 이미지 표현하는 안드로이드 그래픽스 클래스
import android.graphics.BitmapFactory; // 비트맵 이미지 디코딩하는 유틸리티 클래스
import com.example.android_sw.R;

public class BitmapControl { // res 파일의 리소스를 객체화 시켜주는 생성자
    Bitmap background; // 배경 객체
    Bitmap[] SwimmingChar; // 캐릭터 수영 모션 저장 배열
    Bitmap upObstacle, downObstacle; // 상단, 하단 장애물 객체
    Bitmap upColoredObstacle, downColoredObstacle; // 상단, 하단 다른 색 장애물 객체

    public BitmapControl(Resources res) { // res 파일의 리소스를 객체화 시켜주는 생성자
        background = BitmapFactory.decodeResource(res, R.drawable.background); // 배경화면으로 사용할 리소스 복호화
        background = imageScale(background); // 배경화면의 스케일

        SelectChar selectChar = new SelectChar(); // SelectChar의 함수들을 사용하기 위한 선언``
        selectChar.Checker(res); // 선택한 캐릭터로 변경하는 Checker 메서드 호출

        SwimmingChar = new Bitmap[3]; // 움직임 설정하는 배열 생성
        SwimmingChar[0] = SelectChar.Image_Zero(); // 첫번째 이미지 설정
        SwimmingChar[1] = SelectChar.Image_One(); // 두번째 이미지 설정
        SwimmingChar[2] = SelectChar.Image_Two(); // 세번째 이미지 설정

        upObstacle = BitmapFactory.decodeResource(res, R.drawable.column); // 상단 장애물 리소스 복호화
        downObstacle = BitmapFactory.decodeResource(res, R.drawable.bus1); // 하단 장애물 리소스 복호화
        upColoredObstacle = BitmapFactory.decodeResource(res, R.drawable.statue); // 상단 두 번째 장애물 복호화
        downColoredObstacle = BitmapFactory.decodeResource(res,R.drawable.bus2); // 하단 두 번째 장애물 복호화
    }
    public Bitmap getUpColoredObstacle(){
        return upColoredObstacle;
    } // 채색된 상단 장애물 게터 메소드
    public Bitmap getDownColoredObstacle(){
        return downColoredObstacle;
    } // 채색된 하단 장애물 게터 메소드
    public Bitmap getUpObstacle(){
        return upObstacle;
    } // 상단 장애물 게터 메소드
    public Bitmap getDownObstacle(){
        return downObstacle;
    } // 하단 장애물 게터 메소드
    public int getObstacleWidth(){
        return upObstacle.getWidth();
    } // 장애물 너비 게터 메소드
    public int getObstacleHeight(){
        return upObstacle.getHeight();
    } // 장애물 높이 게터 메소드
    public Bitmap getChar(int frame){
        return SwimmingChar[frame];
    } // 캐릭터 동작 게터 메소드
    public int getCharWidth(){
        return SwimmingChar[0].getWidth();
    } // 캐릭터 너비 게터 메소드
    public int getCharHeight(){
        return SwimmingChar[0].getHeight();
    } // 캐릭터 높이 게터 메소드
    public Bitmap getBackground(){
        return background;
    } // 배경화면 게터 메소드
    public int getBackgroundWidth(){ // 배경화면 너비 게터 메소드
        return background.getWidth();
    }
    public int getBackgroundHeight(){ // 배경화면 높이 게터 메소드
        return background.getHeight();
    }
    public Bitmap imageScale(Bitmap bitmap) { // 배경화면 범위 설정 메소드
        float width_heightRatio = getBackgroundWidth() / getBackgroundHeight();
        int bgScaleWidth = (int) width_heightRatio*AppHolder.SCRN_WIDTH_X; // 실수형을 정수형으로 casting하여 변수에 대입
        return Bitmap.createScaledBitmap(bitmap, bgScaleWidth, AppHolder.SCRN_HEIGHT_Y, false); // 비트맵 객체의 인자의 정보에 따라 설정된 비트맵 반환
    }
}
