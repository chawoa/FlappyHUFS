package com.CodeDiversFlappyHUFS.android_sw;

public class SwimmingChar {
    private int charX; // 캐릭터 X좌표
    private int charY; // 캐릭터 Y좌표
    private int currentFrame; // 현재 프레임
    private int velocity; // 속도
    public static int maximumFrame; // 최대 프레임

    public SwimmingChar() { // 캐릭터 생성자
        charX = AppHolder.SCRN_WIDTH_X / 2 - AppHolder.getBitmapControl().getCharWidth() / 2;
        charY = AppHolder.SCRN_HEIGHT_Y / 2 - AppHolder.getBitmapControl().getCharHeight() / 2;
        currentFrame = 0;
        maximumFrame = 2;
    }

    public int getCurrentFrame() { // 현재 프레임 게터 메소드
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) { // 현재 프레임 세터 메소드
        this.currentFrame = currentFrame;
    }

    public int getX() { // 캐릭터의X 좌표 게터 메소드
        return charX;
    }

    public int getY() { // 캐릭터 Y 좌표 게터 메소드
        return charY;
    }

    public void setY(int charY) { // 캐릭터 Y 좌표 세터 메소드
        this.charY = charY;
    }

    public int getVelocity() { // 캐릭터 속도 게터 메소드
        return velocity;
    }

    public void setVelocity(int velocity) { // 캐릭터 속도를 세터 메소드
        this.velocity = velocity;
    }
}