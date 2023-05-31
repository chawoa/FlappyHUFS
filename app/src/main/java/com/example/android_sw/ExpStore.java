package com.example.android_sw;

public class ExpStore {
    private static int previousExp = 0; // 기존 점수 저장 변수
    public static int LV = 1;

    public static void setPreviousExp(int exp) {
        previousExp = exp; // 이전 경험치 값을 직접 설정
    }

    public static int getPreviousExp() {
        return previousExp;
    }

    public static int addExp(int exp) {
        previousExp += exp;
        return previousExp;
    }

    public static float calculateExpPercentage(int maxExp) {
        float expPercentage = ((float) getPreviousExp() / maxExp) * 100;
        return expPercentage;
    }
}
