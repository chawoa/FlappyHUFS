package com.example.android_sw;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapControl {
    /*
    res 파일의 리소스를 객체화 시켜주는 생성자
     */
    Bitmap background; // 배경 객체
    Bitmap[] SwimmingChar; // 캐릭터 수영 모션 저장 배열
    Bitmap upObstacle, downObstacle; // 상단, 하단 장애물 객체
    Bitmap upColoredObstacle, downColoredObstacle; // 상단, 하단 다른 색 장애물 객체

    /*
    res 파일의 리소스를 객체화 시켜주는 생성자
     */
    public BitmapControl(Resources res) {
        background = BitmapFactory.decodeResource(res, R.drawable.background); // 배경화면으로 사용할 리소스 복호화
        background = imageScale(background); // 배경화면의 스케일
        SelectChar selectChar = new SelectChar();
        selectChar.Checker(res);
        SwimmingChar = new Bitmap[3];
        SwimmingChar[0] = SelectChar.Image_Zero();
        SwimmingChar[1] = SelectChar.Image_One();
        SwimmingChar[2] = SelectChar.Image_Two();
        upObstacle = BitmapFactory.decodeResource(res, R.drawable.up_tube); // 상단 장애물 리소스 복호화
        downObstacle = BitmapFactory.decodeResource(res, R.drawable.down_tube); // 하단 장애물 리소스 복호화
        upColoredObstacle = BitmapFactory.decodeResource(res,R.drawable.colored_tube_up); // 상단 채색된 장애물 복호화
        downColoredObstacle = BitmapFactory.decodeResource(res,R.drawable.colored_tube_bottom); // 하단 채색된 장애물 복호화
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
