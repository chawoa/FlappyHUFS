package com.example.android_sw;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapControl {
    Bitmap background;
    Bitmap[] SwimmingChar;
    Bitmap upObstacle;
    Bitmap downObstacle;
    Bitmap upColoredObstacle, downColoredObstacle;
    public BitmapControl(Resources res) {
        background = BitmapFactory.decodeResource(res, R.drawable.background);
        background = imageScale(background);
        SwimmingChar = new Bitmap[3];
        SwimmingChar[0] = BitmapFactory.decodeResource(res, R.drawable.bird1);
        SwimmingChar[1] = BitmapFactory.decodeResource(res, R.drawable.bird2);
        SwimmingChar[2] = BitmapFactory.decodeResource(res, R.drawable.bird3);
        upObstacle = BitmapFactory.decodeResource(res, R.drawable.up_tube);
        downObstacle = BitmapFactory.decodeResource(res, R.drawable.down_tube);
        upColoredObstacle = BitmapFactory.decodeResource(res,R.drawable.colored_tube_up);
        downColoredObstacle = BitmapFactory.decodeResource(res,R.drawable.colored_tube_bottom);
    }
    public Bitmap getUpColoredObstacle(){
        return upColoredObstacle;
    }
    public Bitmap getDownColoredObstacle(){
        return downColoredObstacle;
    }
    public Bitmap getUpObstacle(){
        return upObstacle;
    }
    public Bitmap getDownObstacle(){
        return downObstacle;
    }
    public int getObstacleWidth(){
        return upObstacle.getWidth();
    }
    public int getObstacleHeight(){
        return upObstacle.getHeight();
    }
    public Bitmap getChar(int frame){
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
