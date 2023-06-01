package com.example.android_sw;

import android.content.Context; // 앱의 현재 상태와 관련된 정보 제공 패키지
import android.media.MediaPlayer;

public class SoundPlayer { // 게임 내 효과음 클래스
    Context context;
    MediaPlayer move, score, crash, jump;

    public SoundPlayer(Context context) { // 효과음 객체 생성자
        this.context = context;
        move = MediaPlayer.create(context, R.raw.swoosh);
        crash = MediaPlayer.create(context, R.raw.hit);
        jump = MediaPlayer.create(context,R.raw.arm);
        score = MediaPlayer.create(context, R.raw.ping);

    }
    public void playSwoosh() { // 캐릭터 움직임 효과음
        if (move != null) {
            move.start();
        }
    }
    public void playPing() { // 장애물 통과 후 점수 획득 시 효과음
        if (score != null) {
            score.start();
        }
    }
    public void playCrash() { // 장애물 충돌 시 효과음
        if (crash != null) {
            crash.start();
        }
    }
    public void playArm() { // 터치 시 발생하는 수영 효과음
        if (jump != null) {
            jump.start();
        }
    }
}