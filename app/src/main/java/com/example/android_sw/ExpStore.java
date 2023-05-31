package com.example.android_sw;

public class ExpStore {
    private static int totalExp = 0;

    public static int getTotalExp() {
        return totalExp;
    }

    public static void addExp(int exp) {
        totalExp += exp;
    }

    public static float calculateExpPercentage(int maxExp) {
        // 경험치 계산 로직 구현
        // 예시로 현재 점수를 50이라고 가정하여 경험치 비율을 계산합니다.
        int currentScore = GameOver.scoreCount;
        float expPercentage = ((float) currentScore / maxExp) * 100;

        return expPercentage;
    }
}

