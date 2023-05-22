package com.example.android_sw;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class AppHolder {
    static BitmapControl bitmapControl;
    static GameManager gameManager;
    static int SCRN_WIDTH_X;
    static int SCRN_HEIGHT_Y;
    static int gravityPull;
    static int JUMP_VELOCITY;
    static int obstacleGap;
    static int obstacle_numbers;
    static int obstacleVelocity;
    static int minimumObstacleCollection_Y;
    static int maximumObstacleCollection_Y;
    static int obstacleDistance;
    static  Context gameActivityContext;
    static SoundPlayer soundPlay;

    public static void assign(Context context){
        mapScreenSize(context);
        bitmapControl = new BitmapControl(context.getResources());
        holdGameVariables();
        gameManager = new GameManager();
        soundPlay = new SoundPlayer(context);

    }

    public static SoundPlayer getSoundPlay(){
        return soundPlay;
    }

    public static void holdGameVariables(){
        AppHolder.gravityPull = 5;
        AppHolder.JUMP_VELOCITY = -50;
        AppHolder.obstacleGap = 650;
        AppHolder.obstacle_numbers = 2;
        AppHolder.obstacleVelocity = 24;
        AppHolder.minimumObstacleCollection_Y = (int)(AppHolder.obstacleGap / 2.0);
        AppHolder.maximumObstacleCollection_Y = AppHolder.SCRN_HEIGHT_Y - AppHolder.minimumObstacleCollection_Y - AppHolder.obstacleGap;
        AppHolder.obstacleDistance = AppHolder.SCRN_WIDTH_X*2/3;
    }

    public static BitmapControl getBitmapControl(){
        return bitmapControl;
    }
    public static GameManager getGameManager(){
        return gameManager;
    }

    private static void mapScreenSize(Context context){
        WindowManager manager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        Display dsp = manager.getDefaultDisplay();
        DisplayMetrics dMetrics = new DisplayMetrics();
        dsp.getMetrics(dMetrics);
        int width = dMetrics.widthPixels;
        int height = dMetrics.heightPixels;
        AppHolder.SCRN_WIDTH_X = width;
        AppHolder.SCRN_HEIGHT_Y = height;
    }
}
