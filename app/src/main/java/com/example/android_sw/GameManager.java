package com.example.android_sw;

import android.app.Activity; // 앱의 화면을 나타내는 컴포넌트 → UI와 상호작용 & LifeCycle 관리
import android.content.Context; // 앱의 현재 상태와 관련된 정보 제공 패키지
import android.content.Intent; // 앱의 컴포넌트 시작을 위한 클래스 → 액티비티, 서비스, 브로드캐스트 리시버 등의 컴포넌트 간 통신 담당
import android.graphics.Canvas; // 2D 그래픽을 그리기 위한 클래스 → 그래픽 그리기 & 조작을 위한 도구 제공
import android.graphics.Color; // 색상 사용을 위한 클래스 → 색상을 나타내고 조작
import android.graphics.Paint; // 그림 그리기 위한 클래스 → 그리기 작업에 필요한 스타일과 정보 포함

import java.util.ArrayList;
import java.util.Random;

public class GameManager {
    BgImage bgImage; // 배경 이미지를 나타내는 클래스 객체
    SwimmingChar swimmingChar; // 수영 캐릭터 객체
    static int gameState; // 게임 상태 변수
    ArrayList<ObstacleCollection> obstacleCollections; // 장애물 저장 배열
    Random rand; // 난수 생성을 위한 변수
    int scoreCount; // 점수 저장 변수
    int winningObstacle; // 통과한 장애물 변수
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

    public void initScoreVariables() {
        scoreCount = 0;
        winningObstacle = 0;
        designPaint = new Paint();
        designPaint.setColor(Color.YELLOW);
        designPaint.setTextSize(250);
        designPaint.setStyle(Paint.Style.FILL);
        designPaint.setFakeBoldText(true);
        designPaint.setShadowLayer(5.0f, 20.0f, 20.0f, Color.BLACK);
    }
  
    /*
    gameState == 0 : 게임 실행 X
    gameState == 1 : 게임 실행 중
    gameState == 2 : 게임 오버
    */

    public void generateTubeObject() {
        for (int j = 0; j < AppHolder.obstacleGap; j++) {
            int obstacleX = AppHolder.SCRN_WIDTH_X + j*AppHolder.obstacleDistance;
            int upObstacleCollectionY = AppHolder.minimumObstacleCollection_Y;
            rand.nextInt(AppHolder.maximumObstacleCollection_Y - AppHolder.minimumObstacleCollection_Y + 1);
            ObstacleCollection obstacleCollection = new ObstacleCollection(obstacleX, upObstacleCollectionY);
            obstacleCollections.add(obstacleCollection);
        }
    }
  
    /*
    <장애물 스크롤링 게임 로직 처리 메소드>
    1. gameState == 1 일때만 작동 (즉, 게임 진행 중일 때 작동)
    2. 현재 장애물과 캐릭터의 충돌 검사 및 게임 오버 상태로의 전환
    3. 장애물과 캐릭터의 X좌표를 비교하여 점수 증가 & 통과한 장애물 개수 업데이트
    4. 모든 장애물 스크롤링 및 그래픽 그리기
    5. 점수 화면에 출력
     */
    public void scrollingTube(Canvas can) {
        if (gameState == 1) {
            if ((obstacleCollections.get(winningObstacle).getXObstacle() < swimmingChar.getX() + AppHolder.getBitmapControl().getCharWidth())
            && (obstacleCollections.get(winningObstacle).getUpObstacleCollection_Y() > swimmingChar.getY()
            || obstacleCollections.get(winningObstacle).getDownObstacle_Y() < swimmingChar.getY() +
                    AppHolder.getBitmapControl().getCharHeight())) {
                gameState = 2;
                AppHolder.getSoundPlay().playCrash();
                Context mContext = AppHolder.gameActivityContext;
                Intent mIntent = new Intent(mContext, GameOver.class);
                mIntent.putExtra("score", scoreCount);
                mContext.startActivity(mIntent);
                ((Activity)mContext).finish();
            }
          
            if (obstacleCollections.get(winningObstacle).getXObstacle() < swimmingChar.getX() - AppHolder.getBitmapControl().getObstacleWidth()) {
                scoreCount ++;
                winningObstacle ++;
                AppHolder.getSoundPlay().playPing();
                if (winningObstacle > AppHolder.obstacle_numbers - 1) {
                    winningObstacle = 0;
                }
            }
          
            for (int j = 0; j < AppHolder.obstacle_numbers; j++) {
                if (obstacleCollections.get(j).getXObstacle()<-AppHolder.getBitmapControl().getObstacleWidth()) {
                    obstacleCollections.get(j).setXobstacle(obstacleCollections.get(j).getXObstacle()
                    +AppHolder.obstacle_numbers*AppHolder.obstacleDistance);
                    int upTubeCollectionY = AppHolder.minimumObstacleCollection_Y +
                            rand.nextInt(AppHolder.maximumObstacleCollection_Y - AppHolder.minimumObstacleCollection_Y+1);
                    obstacleCollections.get(j).setUpObstacleCollection_Y(upTubeCollectionY);
                    obstacleCollections.get(j).setColorObstacle();
                }
                obstacleCollections.get(j).setXobstacle(obstacleCollections.get(j).getXObstacle() - AppHolder.obstacleVelocity);
                if (obstacleCollections.get(j).getColorObstacle() == 0) {
                   can.drawBitmap(AppHolder.getBitmapControl().getUpObstacle(), obstacleCollections.get(j).getXObstacle(), obstacleCollections.get(j).getUpObstacle_Y(), null);
                   can.drawBitmap(AppHolder.getBitmapControl().getDownObstacle(), obstacleCollections.get(j).getXObstacle(), obstacleCollections.get(j).getDownObstacle_Y(), null);
                } else {
                   can.drawBitmap(AppHolder.getBitmapControl().getUpColoredObstacle(), obstacleCollections.get(j).getXObstacle(), obstacleCollections.get(j).getUpObstacle_Y(), null);
                   can.drawBitmap(AppHolder.getBitmapControl().getDownColoredObstacle(), obstacleCollections.get(j).getXObstacle(), obstacleCollections.get(j).getDownObstacle_Y(), null);
                }
            }
            can.drawText("" + scoreCount, 620, 400, designPaint);
        }
    }
  
    /*
    <캐릭터 애니메이션 처리 메소드>
    1. gameState == 1이면 로직 수행
    2. 캐릭터의 Y좌표를 중력의 영향을 받아 업데이트
    3. 캐릭터 애니메이션 프레임 그리기
     */
    public void birdAnimation(Canvas canvas) {
        if (gameState == 1) {
            if (swimmingChar.getY() < (AppHolder.SCRN_HEIGHT_Y - AppHolder.getBitmapControl().getCharHeight()) || swimmingChar.getVelocity() < 0){
                swimmingChar.setVelocity(swimmingChar.getVelocity() + AppHolder.gravityPull);
                swimmingChar.setY(swimmingChar.getY() + swimmingChar.getVelocity());
            }
        }

        // 캐릭터 애니메이션 프레임 그리기
        int currentFrame = swimmingChar.getCurrentFrame();
        canvas.drawBitmap(AppHolder.getBitmapControl().getChar(currentFrame), swimmingChar.getX(), swimmingChar.getY(), null);
        currentFrame++;

        if(currentFrame > swimmingChar.maximumFrame) {
            currentFrame = 0;
        }
        swimmingChar.setCurrentFrame(currentFrame);
    }

    /*
    <배경 애니메이션 처리 메소드>
    1. 배경의 X 좌표를 업데이트하여 배경 스크롤링
    2. 배경 그리기
    3. 배경이 화면을 벗어나면 반대 쪽에 다시 그려 연속 스크롤링 효과 구현
     */
    public void backgroundAnimation(Canvas canvas) {
        bgImage.setX(bgImage.getX() - bgImage.getVelocity());
        if (bgImage.getX() < -AppHolder.getBitmapControl().getBackgroundWidth()) {
            bgImage.setX(0);
        }
        canvas.drawBitmap(AppHolder.getBitmapControl().getBackground(), bgImage.getX(), bgImage.getY(), null);
        if (bgImage.getX() <-(AppHolder.getBitmapControl().getBackgroundWidth() - AppHolder.SCRN_WIDTH_X)) {
            canvas.drawBitmap(AppHolder.getBitmapControl().getBackground(), bgImage.getX() +
                    AppHolder.getBitmapControl().getBackgroundWidth(), bgImage.getY(), null);
        }
    }
}
