package com.example.android_sw;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class GameActivity extends Activity {
    GamePlay gamePlay;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppHolder.gameActivityContext = this;
        gamePlay = new GamePlay(this);
        setContentView(gamePlay);
    }
}
