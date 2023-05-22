package com.example.android_sw;

public class SwimmingChar {
    private int charX;
    private int charY;
    private int currentFrame;
    private int velocity;
    public static int maximumFrame;
    public SwimmingChar() {
        charX = AppHolder.SCRN_WIDTH_X/2 - AppHolder.getBitmapControl().getCharWidth()/2;
        charY = AppHolder.SCRN_HEIGHT_Y/2 - AppHolder.getBitmapControl().getCharHeight()/2;
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
        return charX;
    }
    public int getY(){ return charY; }
    public void setX(int birdX){
        this.charX = birdX;
    }
    public void setY(int birdY){
        this.charY = birdY;
    }
    public int getVelocity(){
        return velocity;
    }
    public void setVelocity(int velocity){
        this.velocity = velocity;
    }
}
