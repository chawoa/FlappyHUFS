package com.CodeDiversFlappyHUFS.android_sw;

import android.graphics.Canvas;
import android.os.SystemClock;
import android.view.SurfaceHolder;

public class MainThread extends Thread {
    SurfaceHolder mySurfaceHolder;
    long timeSpent;
    long kickOffTime;
    long WAIT = 31;
    boolean Running;
    private static Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder) {
        this.mySurfaceHolder = surfaceHolder;
        Running = true;
    }

    /**
     * 1. kickOffTime 현재 시간으로 설정
     * 2. canvas 변수 초기화
     * 3. mySurfaceHolder에서 canvas 호출
     * 4. mySurfaceHolder 동기화 후 AppHolder의 게임 매니저를 통해 배경, 캐릭터 애니메이션 그리고, 장애물 스크롤링
     * 5. canvas 해제
     * 6. 경과 시간 계산 후 지정된 대기 시간(WAIT)과 차이 만큼 스레드 일시 정지
     * 7. 다시 1단계로 돌아가서 다음 루프 실행
     */
    @Override
    public void run() {
        while (Running) {
            kickOffTime = SystemClock.uptimeMillis();
            canvas = null;
            try {
                canvas = mySurfaceHolder.lockCanvas(); // mySurfaceHolder에서 canvas 호출
                synchronized (mySurfaceHolder){ // mySurfaceHolder 동기화
                    AppHolder.getGameManager().backgroundAnimation(canvas); // AppHolder의 게임 매니저를 통해 배경 그리기
                    AppHolder.getGameManager().charAnimation(canvas); // AppHolder의 게임 매니저를 통해 캐릭터 애니메이션 그리기
                    AppHolder.getGameManager().scrollingObstacle(canvas); // AppHolder의 게임 매니저를 통해 장애물 스크롤링
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            /**
             * 1. 잠금 상태로 유지 → 다른 스레드가 접근 불가능 (동시에 여러 그래픽 작업 충돌 혹은 동기화 문제 발생 가능)
             * 따라서 작업이 끝나면 unlockCanvasAndPost() 호출을 통해 필히 해제해야 함

             * 2. unlockCanvasAndPost() 호출 → canvas를 다른 스레드가 접근 가능해짐 (해제 → 그려진 그래픽이 실제 화면에 표시됨)
             * 즉, canvas를 사용하는 다른 부분이나 스레드에서 사용할 수 있도록 해주는 역할
             */
            finally {
                if (canvas != null) {
                    try {
                        mySurfaceHolder.unlockCanvasAndPost(canvas); // canvas 해제

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            timeSpent = SystemClock.uptimeMillis() - kickOffTime; // 경과 시간 계산
            if (timeSpent < WAIT) {
                try {
                    Thread.sleep(WAIT - timeSpent); // 지정된 대기 시간(WAIT)과 차이 만큼 스레드 일시 정지
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void setIsRunning(boolean state) { // 작동 여부를 부여하는 세터 메소드
        Running = state; // 실행 여부 저장 변수 (불린)
    }
}