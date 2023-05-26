package com.example.android_sw;

import java.util.Random;

public class ObstacleCollection {
    private int xObstacle;
    private int upObstacleCollection_Y;
    private Random rand;
    private int colorObstacle;
    public ObstacleCollection(int xTube, int upTubeCollection_Y) {
        this.xObstacle = xTube;
        this.upObstacleCollection_Y = upTubeCollection_Y;
        rand = new Random();
    }
    public void setColorObstacle(){
        colorObstacle = rand.nextInt(2);
    }
    public int getColorObstacle(){
        return colorObstacle;
    }
    public int getUpObstacleCollection_Y(){
        return upObstacleCollection_Y;
    }
    public int getXObstacle(){
        return xObstacle;
    }
    public int getUpObstacle_Y() {
        return upObstacleCollection_Y - AppHolder.getBitmapControl().getObstacleHeight();
    }
    public int getDownObstacle_Y(){
        return upObstacleCollection_Y + AppHolder.obstacleGap;
    }
    public void setXobstacle(int x_Obstacle){
        this.xObstacle = x_Obstacle;
    }
    public void setUpObstacleCollection_Y(int upTubeCollection_Y) {
        this.upObstacleCollection_Y = upTubeCollection_Y;
    }
}
