package com.example.android_sw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

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

    public void CheckChar(View view){
        ImageButton clickedButton = (ImageButton) view;


        // 선택된 버튼 체크 표시
        clickedButton.setImageResource(R.drawable.checking_img);

    }
}