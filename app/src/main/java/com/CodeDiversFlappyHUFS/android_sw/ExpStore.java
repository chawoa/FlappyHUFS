package com.CodeDiversFlappyHUFS.android_sw;

import android.content.Context; // 앱의 현재 상태와 관련된 정보 제공 패키지
import android.content.SharedPreferences; // 데이터 영구 저장 및 검색 기능 제공

public class ExpStore {
    public static int LV = 1;
    private static int MAXLV = 3;
    private static int MaxDefault = 200;
    private static final String PREF_NAME = "expStorePreference";
    private static final String KEY_PREVIOUS_EXP = "previousExp";
    private static SharedPreferences sharedPreferences;

    public static void assign(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static void setPreviousExp(int exp) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_PREVIOUS_EXP, exp);
        editor.apply();
    }

    public static int getPreviousExp() {
        return sharedPreferences.getInt(KEY_PREVIOUS_EXP, 0);
    }

    public static int addExp(int exp) {
        int previousExp = getPreviousExp();
        previousExp += exp;
        setPreviousExp(previousExp);
        return previousExp;
    }

    public static float calculateExpPercentage(int maxExp) {
        float expPercentage = ((float) getPreviousExp() / maxExp) * 100;
        return expPercentage;
    }

    public static int checkLVup(int Maxmax) {
        if(LV < MAXLV) { // 최대 레벨보다 작은 경우
            int previousExp = getPreviousExp();
            if (previousExp >= Maxmax) {
                LV++; // 레벨업
                previousExp = previousExp - Maxmax;
                setPreviousExp(previousExp);
                Maxmax = LV * MaxDefault;
            }

            if (previousExp >= Maxmax) { // 최대 레벨 초과나 달성 시에는 변화 X
                checkLVup(Maxmax);
            }
        }
        return Maxmax;
    }
}