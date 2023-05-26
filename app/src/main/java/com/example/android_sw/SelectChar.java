package com.example.android_sw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.res.Resources;

public class SelectChar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_char);
    }

    public void BackToMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    /*여기서 부터 작성중------------------------------------------------*/
    public static boolean Basic_selected;
    public static boolean Glasses_selected = true;
    public static boolean Others_selected;
    public static Bitmap[] SwimChar = new Bitmap[3];


    public void CheckChar(View view){
        ImageButton clickedButton = (ImageButton) view;

        // 클릭된 버튼의 id 확인 후 boolean 값 변경
        if (clickedButton.getId() == R.id.Char_1) {
            Basic_selected = true;
            Glasses_selected = false;
            Others_selected = false;
        } else if (clickedButton.getId() == R.id.Char_2) {
            Basic_selected = false;
            Glasses_selected = true;
            Others_selected = false;
        } else if (clickedButton.getId() == R.id.Char_3) {
            Basic_selected = false;
            Glasses_selected = false;
            Others_selected = true;
        }
        updateChecking();
        Checker(getResources());

    }

    private void updateChecking(){
        // 이미지 버튼 상태 업데이트
        ImageButton button1 = findViewById(R.id.Char_1);
        ImageButton button2 = findViewById(R.id.Char_2);
        ImageButton button3 = findViewById(R.id.Char_3);

        button1.setImageResource(Basic_selected ? R.drawable.checking_img : R.drawable.bird1);
        button2.setImageResource(Glasses_selected ? R.drawable.checking_img : R.drawable.bird2);
        button3.setImageResource(Others_selected ? R.drawable.checking_img : R.drawable.bird3);
    }
    public void Checker(Resources res) {
        if (Basic_selected) {
            SwimChar[0] = BitmapFactory.decodeResource(res, R.drawable.bird1);
            SwimChar[1] = BitmapFactory.decodeResource(res, R.drawable.bird2);
            SwimChar[2] = BitmapFactory.decodeResource(res, R.drawable.bird3);
        } else if (Glasses_selected) {
            SwimChar[0] = BitmapFactory.decodeResource(res, R.drawable.student_1);
            SwimChar[1] = BitmapFactory.decodeResource(res, R.drawable.student_2);
            SwimChar[2] = BitmapFactory.decodeResource(res, R.drawable.student_3);
        } else if (Others_selected) {
            SwimChar[0] = BitmapFactory.decodeResource(res, R.drawable.bird1);
            SwimChar[1] = BitmapFactory.decodeResource(res, R.drawable.bird2);
            SwimChar[2] = BitmapFactory.decodeResource(res, R.drawable.bird3);
        }else{
            SwimChar[0] = BitmapFactory.decodeResource(res, R.drawable.bird1);
            SwimChar[1] = BitmapFactory.decodeResource(res, R.drawable.bird2);
            SwimChar[2] = BitmapFactory.decodeResource(res, R.drawable.bird3);
        }
    }

    public static Bitmap Image_Zero(){
        return SwimChar[0];
    }
    public static Bitmap Image_One(){
        return SwimChar[1];
    }
    public static Bitmap Image_Two(){
        return SwimChar[2];
    }
}