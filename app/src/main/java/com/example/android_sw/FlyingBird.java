package com.example.android_sw;

public class FlyingBird {
    private int birdX;
    private int birdY;
    private int currentFrame;
    private int velocity;
    public static int maximumFrame;
    public FlyingBird() {
        birdX = AppHolder.SCRN_WIDTH_X/2 - AppHolder.getBitmapControl().getBirdWidth()/2;
        birdY = AppHolder.SCRN_HEIGHT_Y/2 - AppHolder.getBitmapControl().getBirdHeight()/2;
        currentFrame = 0;
        maximumFrame = 2;
    }
    public int getCurrentFrame() {
        return currentFrame;
    }
    public void setCurrentFrame(int currentFrame){
        this.currentFrame = currentFrame;
    }
    public int getX(){
        return birdX;
    }
    public int getY(){
        return birdY;
    }
    public void setX(int birdX){
        this.birdX = birdX;
    }
    public void setY(int birdY){
        this.birdY = birdY;
    }
    public int getVelocity(){
        return velocity;
    }
    public void setVelocity(int velocity){
        this.velocity = velocity;
    }
}
