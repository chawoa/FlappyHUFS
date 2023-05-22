package com.example.android_sw;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapControl {
    Bitmap background;
    Bitmap[] SwimmingChar;
    Bitmap upTube;
    Bitmap downTube;
    Bitmap upColoredTube, downColoredTube;
    public BitmapControl(Resources res) {
        background = BitmapFactory.decodeResource(res, R.drawable.background);
        background = imageScale(background);
        SwimmingChar = new Bitmap[3];
        SwimmingChar[0] = BitmapFactory.decodeResource(res, R.drawable.bird1);
        SwimmingChar[1] = BitmapFactory.decodeResource(res, R.drawable.bird2);
        SwimmingChar[2] = BitmapFactory.decodeResource(res, R.drawable.bird3);
        upTube = BitmapFactory.decodeResource(res, R.drawable.up_tube);
        downTube = BitmapFactory.decodeResource(res, R.drawable.down_tube);
        upColoredTube = BitmapFactory.decodeResource(res,R.drawable.colored_tube_up);
        downColoredTube = BitmapFactory.decodeResource(res,R.drawable.colored_tube_bottom);
    }
    public Bitmap getUpColoredTube(){
        return upColoredTube;
    }
    public Bitmap getDownColoredTube(){
        return downColoredTube;
    }
    public Bitmap getUpTube(){
        return upTube;
    }
    public Bitmap getDownTube(){
        return downTube;
    }
    public int getTubeWidth(){
        return upTube.getWidth();
    }
    public int getTubeHeight(){
        return upTube.getHeight();
    }
    public Bitmap getBird(int frame){
        return SwimmingChar[frame];
    }
    public int getCharWidth(){
        return SwimmingChar[0].getWidth();
    }
    public int getCharHeight(){
        return SwimmingChar[0].getHeight();
    }
    public Bitmap getBackground(){
        return background;
    }
    public int getBackgroundWidth(){
        return background.getWidth();
    }
    public int getBackgroundHeight(){
        return background.getHeight();
    }
    public Bitmap imageScale(Bitmap bitmap){
        float width_heightRatio = getBackgroundWidth() / getBackgroundHeight();
        int bgScaleWidth = (int)width_heightRatio*AppHolder.SCRN_WIDTH_X;
        return Bitmap.createScaledBitmap(bitmap, bgScaleWidth, AppHolder.SCRN_HEIGHT_Y, false);
    }
}
