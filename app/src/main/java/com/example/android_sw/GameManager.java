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
    ArrayList<TubeCollection> tubeCollections;
    Random rand;
    int scoreCount; //this will be used to store the score
    int winningTube; //this will used to determine the winning tube obstacle
    Paint designPaint;
    public GameManager() {
        bgImage = new BgImage();
        swimmingChar = new SwimmingChar();
        gameState = 0;
        tubeCollections = new ArrayList<>();
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
        for(int j = 0; j < AppHolder.tubeGap; j++){
            int tubeX = AppHolder.SCRN_WIDTH_X + j*AppHolder.tubeDistance;
            int upTubeCollectionY = AppHolder.minimumTubeCollection_Y;
            rand.nextInt(AppHolder.maximumTubeCollection_Y - AppHolder.minimumTubeCollection_Y + 1);
            TubeCollection tubeCollection = new TubeCollection(tubeX, upTubeCollectionY);
            tubeCollections.add(tubeCollection);
        }
    }

    public void scrollingTube(Canvas can){
        if(gameState == 1){

            if((tubeCollections.get(winningTube).getXtube() < swimmingChar.getX() + AppHolder.getBitmapControl().getCharWidth())
            &&(tubeCollections.get(winningTube).getUpTubeCollection_Y() > swimmingChar.getY()
            ||tubeCollections.get(winningTube).getDownTube_Y() < swimmingChar.getY() +
                    AppHolder.getBitmapControl().getCharHeight())){
                gameState = 2;
                AppHolder.getSoundPlay().playCrash();
                Context mContext = AppHolder.gameActivityContext;
                Intent mIntent = new Intent(mContext, GameOver.class);
                mIntent.putExtra("score", scoreCount);
                mContext.startActivity(mIntent);
                ((Activity)mContext).finish();
            }

            if(tubeCollections.get(winningTube).getXtube() < swimmingChar.getX() - AppHolder.getBitmapControl().getTubeWidth()){
                scoreCount ++;
                winningTube ++;
                AppHolder.getSoundPlay().playPing();
                if(winningTube > AppHolder.tube_numbers - 1){
                    winningTube = 0;
                }
            }
            for(int j = 0; j < AppHolder.tube_numbers; j++){
                if(tubeCollections.get(j).getXtube()<-AppHolder.getBitmapControl().getTubeWidth()){
                    tubeCollections.get(j).setXtube(tubeCollections.get(j).getXtube()
                    +AppHolder.tube_numbers*AppHolder.tubeDistance);
                    int upTubeCollectionY = AppHolder.minimumTubeCollection_Y +
                            rand.nextInt(AppHolder.maximumTubeCollection_Y - AppHolder.minimumTubeCollection_Y+1);
                    tubeCollections.get(j).setUpTubeCollection_Y(upTubeCollectionY);
                    tubeCollections.get(j).setColorTube();
                }
                tubeCollections.get(j).setXtube(tubeCollections.get(j).getXtube() - AppHolder.tubeVelocity);
               if(tubeCollections.get(j).getColorTube() == 0){
                   can.drawBitmap(AppHolder.getBitmapControl().getUpTube(), tubeCollections.get(j).getXtube(), tubeCollections.get(j).getUpTube_Y(), null);
                   can.drawBitmap(AppHolder.getBitmapControl().getDownTube(), tubeCollections.get(j).getXtube(), tubeCollections.get(j).getDownTube_Y(), null);
               }else{
                   can.drawBitmap(AppHolder.getBitmapControl().getUpColoredTube(), tubeCollections.get(j).getXtube(), tubeCollections.get(j).getUpTube_Y(), null);
                   can.drawBitmap(AppHolder.getBitmapControl().getDownColoredTube(), tubeCollections.get(j).getXtube(), tubeCollections.get(j).getDownTube_Y(), null);
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
        canvas.drawBitmap(AppHolder.getBitmapControl().getBird(currentFrame), swimmingChar.getX(), swimmingChar.getY(), null);
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
