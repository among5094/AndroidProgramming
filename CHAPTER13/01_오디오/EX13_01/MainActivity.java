package com.example.ex13_01;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //MediaPlayer 객체 생성
        final MediaPlayer mPlayer;
        mPlayer = MediaPlayer.create(this, R.raw.song1); //로컬 리소스인 song1 파일로 초기화

        //레이아웃 파일에서 ID가 switch1인 스위치 버튼을 찾음
        final Switch switch1 = (Switch) findViewById(R.id.switch1);

        //스위치 버튼에 클릭 리스너를 설정
        switch1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(switch1.isChecked()==true)
                    mPlayer.start(); // 스위치가 켜지면 음악 재생
                else
                    mPlayer.stop(); // 스위치가 꺼지면 음악 중지
            }
        });
    }
}