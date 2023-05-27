package com.example.android_sw;

import android.content.Context;
import android.media.MediaPlayer;

public class SoundPlayer { // 게임 내 효과음 클래스
    Context context;
    MediaPlayer move, score, crash, jump;

    public SoundPlayer(Context context) { // 효과음 객체 생성자
        this.context = context;
        move = MediaPlayer.create(context, R.raw.swoosh);
        crash = MediaPlayer.create(context, R.raw.hit);
        jump = MediaPlayer.create(context,R.raw.wing);
        score = MediaPlayer.create(context, R.raw.ping);

    }
    public void playSwoosh() {
        if (move != null) {
            move.start();
        }
    }
    public void playPing() {
        if (score != null) {
            score.start();
        }
    }
    public void playCrash() {
        if (crash != null) {
            crash.start();
        }
    }
    public void playArm() {
        if (jump != null) {
            jump.start();
        }
    }
}