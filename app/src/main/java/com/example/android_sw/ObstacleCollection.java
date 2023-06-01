package com.example.android_sw;

import java.util.Random;

public class ObstacleCollection { // 장애물의 설정값 저장 클래스
    private int xObstacle; // 장애물 X 좌표
    private int upObstacleCollection_Y; // 상단 장애물의 Y좌표
    private Random rand; // 난수 설정 변수
    private int colorObstacle; // 색칠된 장애물 변수

    /**
     * 장애물을 저장하는 객체 → 생성자로 초기 X, Y 좌표를 받고, Random 객체로 임의 값 생성을 통한
     * 무작위 장애물 반환
     */
    public ObstacleCollection(int xObstacle, int upObstacleCollection_Y) {
        this.xObstacle = xObstacle; // 장애물의 X좌표
        this.upObstacleCollection_Y = upObstacleCollection_Y; // 상단 장애물의 Y좌표
        rand = new Random(); // 난수 설정 변수
    }

    public void setColorObstacle() { // 색칠된 장애물 세터 메소드
        colorObstacle = rand.nextInt(2);
    }

    public int getColorObstacle() { // 색칠된 장애물 게터 메소드
        return colorObstacle;
    }

    public int getUpObstacleCollection_Y() { // 상단 장애물 Y좌표 반환 메소드
        return upObstacleCollection_Y;
    }

    public int getXObstacle() { // 장애물 X좌표 게터 메소드
        return xObstacle;
    }

    public int getUpObstacle_Y() { // 상단 장애물의 하부 Y좌표 게터 메소드
        return upObstacleCollection_Y - AppHolder.getBitmapControl().getObstacleHeight();
    }

    public int getDownObstacle_Y() { // 하단 장애물의 상부 Y좌표 게터 메소드
        return upObstacleCollection_Y + AppHolder.obstacleGap;
    }

    public void setXobstacle(int x_Obstacle) { // 장애물의 X좌표 세터 메소드
        this.xObstacle = x_Obstacle;
    }

    public void setUpObstacleCollection_Y(int upTubeCollection_Y) { // 상단 장애물의 Y좌표 세터 메소드
        this.upObstacleCollection_Y = upTubeCollection_Y;
    }
}