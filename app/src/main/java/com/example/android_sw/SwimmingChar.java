package com.example.android_sw;

public class SwimmingChar {
    private int charX; // 캐릭터 X좌표
    private int charY; // 캐릭터 Y좌표
    private int currentFrame; // 현재 프레임
    private int velocity; // 속도
    public static int maximumFrame; // 최대 프레임

    public SwimmingChar() {
        charX = AppHolder.SCRN_WIDTH_X/2 - AppHolder.getBitmapControl().getCharWidth()/2;
        charY = AppHolder.SCRN_HEIGHT_Y/2 - AppHolder.getBitmapControl().getCharHeight()/2;
        currentFrame = 0;
        maximumFrame = 2;
    }

    /**
     * 현재 프레임 게터 메소드
     * @return 현재 프레임
     */
    public int getCurrentFrame() {
        return currentFrame;
    }

    /**
     * 현재 프레임 세터 메소드
     * @param currentFrame 설정할 현재 프레임
     */
    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    /**
     * 캐릭터의X 좌표 게터 메소드
     * @return 캐릭터 X 좌표
     */
    public int getX() {
        return charX;
    }

    /**
     * 캐릭터 Y 좌표 게터 메소드
     * @return 캐릭터 Y 좌표
     */
    public int getY() {
        return charY;
    }

    /**
     * 캐릭터 X 좌표 세터 메소드
     * @param charX 설정할 캐릭터 X 좌표
     */
    public void setX(int charX) {
        this.charX = charX;
    }

    /**
     * 캐릭터 Y 좌표 세터 메소드
     * @param charY 설정할 캐릭터 Y 좌표
     */
    public void setY(int charY) {
        this.charY = charY;
    }

    /**
     * 캐릭터 속도 게터 메소드
     * @return 캐릭터의 속도
     */
    public int getVelocity() {
        return velocity;
    }

    /**
     * 캐릭터 속도를 세터 메소드
     * @param velocity 설정할 캐릭터 속도
     */
    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }
}