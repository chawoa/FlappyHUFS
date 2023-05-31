package com.example.android_sw;

import android.widget.ProgressBar;

public class ExpStore {
    public static void updateExpBar(ProgressBar expBar, int score) {
        // 점수에 따른 경험치 계산 로직
        int exp = calculateExp(score);

        // 경험치 바 업데이트
        expBar.setProgress(exp);
    }

    private static int calculateExp(int score) {
        // 경험치 계산 로직 구현
        // 예시: 점수가 0 ~ 100 사이이면 점수를 경험치로 사용
        return Math.min(score, 100);
    }
}

