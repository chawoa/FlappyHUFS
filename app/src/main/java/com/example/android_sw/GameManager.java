package com.example.android_sw;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Random;

public class GameManager {
    BgImage bgImage;
    SwimmingChar swimmingChar;
    static int gameState;
    ArrayList<ObstacleCollection> obstacleCollections;
    Random rand;
    int scoreCount; //this will be used to store the score
    int winningTube; //this will used to determine the winning tube obstacle
    Paint designPaint;
    public GameManager() {
        bgImage = new BgImage();
        swimmingChar = new SwimmingChar();
        gameState = 0;
        obstacleCollections = new ArrayList<>();
        rand = new Random();
        generateTubeObject();
        initScoreVariables();

    }

    public void initScoreVariables(){
        scoreCount = 0;
        winningTube = 0;
        designPaint = new Paint();
        designPaint.setColor(Color.YELLOW);
        designPaint.setTextSize(250);
        designPaint.setStyle(Paint.Style.FILL);
        designPaint.setFakeBoldText(true);
        designPaint.setShadowLayer(5.0f, 20.0f, 20.0f, Color.BLACK);

    }

    /*
    gameState == 0 : not running
    gameState == 1 : the game is running
    gameState == 2 : The game is over
     */

    public void generateTubeObject(){
        for(int j = 0; j < AppHolder.obstacleGap; j++){
            int obstacleX = AppHolder.SCRN_WIDTH_X + j*AppHolder.obstacleDistance;
            int upObstacleCollectionY = AppHolder.minimumObstacleCollection_Y;
            rand.nextInt(AppHolder.maximumObstacleCollection_Y - AppHolder.minimumObstacleCollection_Y + 1);
            ObstacleCollection obstacleCollection = new ObstacleCollection(obstacleX, upObstacleCollectionY);
            obstacleCollections.add(obstacleCollection);
        }
    }

    public void scrollingTube(Canvas can){
        if(gameState == 1){

            if((obstacleCollections.get(winningTube).getXObstacle() < swimmingChar.getX() + AppHolder.getBitmapControl().getCharWidth())
            &&(obstacleCollections.get(winningTube).getUpObstacleCollection_Y() > swimmingChar.getY()
            ||obstacleCollections.get(winningTube).getDownObstacle_Y() < swimmingChar.getY() +
                    AppHolder.getBitmapControl().getCharHeight())){
                gameState = 2;
                AppHolder.getSoundPlay().playCrash();
                Context mContext = AppHolder.gameActivityContext;
                Intent mIntent = new Intent(mContext, GameOver.class);
                mIntent.putExtra("score", scoreCount);
                mContext.startActivity(mIntent);
                ((Activity)mContext).finish();
            }

            if(obstacleCollections.get(winningTube).getXObstacle() < swimmingChar.getX() - AppHolder.getBitmapControl().getObstacleWidth()){
                scoreCount ++;
                winningTube ++;
                AppHolder.getSoundPlay().playPing();
                if(winningTube > AppHolder.obstacle_numbers - 1){
                    winningTube = 0;
                }
            }
            for(int j = 0; j < AppHolder.obstacle_numbers; j++){
                if(obstacleCollections.get(j).getXObstacle()<-AppHolder.getBitmapControl().getObstacleWidth()){
                    obstacleCollections.get(j).setXobstacle(obstacleCollections.get(j).getXObstacle()
                    +AppHolder.obstacle_numbers*AppHolder.obstacleDistance);
                    int upTubeCollectionY = AppHolder.minimumObstacleCollection_Y +
                            rand.nextInt(AppHolder.maximumObstacleCollection_Y - AppHolder.minimumObstacleCollection_Y+1);
                    obstacleCollections.get(j).setUpObstacleCollection_Y(upTubeCollectionY);
                    obstacleCollections.get(j).setColorObstacle();
                }
                obstacleCollections.get(j).setXobstacle(obstacleCollections.get(j).getXObstacle() - AppHolder.obstacleVelocity);
               if(obstacleCollections.get(j).getColorObstacle() == 0){
                   can.drawBitmap(AppHolder.getBitmapControl().getUpObstacle(), obstacleCollections.get(j).getXObstacle(), obstacleCollections.get(j).getUpObstacle_Y(), null);
                   can.drawBitmap(AppHolder.getBitmapControl().getDownObstacle(), obstacleCollections.get(j).getXObstacle(), obstacleCollections.get(j).getDownObstacle_Y(), null);
               }else{
                   can.drawBitmap(AppHolder.getBitmapControl().getUpColoredObstacle(), obstacleCollections.get(j).getXObstacle(), obstacleCollections.get(j).getUpObstacle_Y(), null);
                   can.drawBitmap(AppHolder.getBitmapControl().getDownColoredObstacle(), obstacleCollections.get(j).getXObstacle(), obstacleCollections.get(j).getDownObstacle_Y(), null);
               }
            }
            can.drawText("" + scoreCount, 620, 400, designPaint);
        }
    }

    public void birdAnimation(Canvas canvas){
        if(gameState == 1){
            if(swimmingChar.getY() < (AppHolder.SCRN_HEIGHT_Y - AppHolder.getBitmapControl().getCharHeight()) || swimmingChar.getVelocity() < 0){
                swimmingChar.setVelocity(swimmingChar.getVelocity() + AppHolder.gravityPull);
                swimmingChar.setY(swimmingChar.getY() + swimmingChar.getVelocity());
            }
        }

        int currentFrame = swimmingChar.getCurrentFrame();
        canvas.drawBitmap(AppHolder.getBitmapControl().getChar(currentFrame), swimmingChar.getX(), swimmingChar.getY(), null);
        currentFrame++;
        if(currentFrame > swimmingChar.maximumFrame){
            currentFrame = 0;
        }
        swimmingChar.setCurrentFrame(currentFrame);
    }

    public void backgroundAnimation(Canvas canvas){
        bgImage.setX(bgImage.getX() - bgImage.getVelocity());
        if(bgImage.getX() < -AppHolder.getBitmapControl().getBackgroundWidth()){
            bgImage.setX(0);
        }
        canvas.drawBitmap(AppHolder.getBitmapControl().getBackground(), bgImage.getX(), bgImage.getY(), null);
        if (bgImage.getX() <-(AppHolder.getBitmapControl().getBackgroundWidth() - AppHolder.SCRN_WIDTH_X)){
            canvas.drawBitmap(AppHolder.getBitmapControl().getBackground(), bgImage.getX() +
                    AppHolder.getBitmapControl().getBackgroundWidth(), bgImage.getY(), null);
        }
    }
}
