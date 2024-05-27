package com.example.ex08_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRead, btnWrite;
        btnRead = (Button) findViewById(R.id.btnRead);
        btnWrite = (Button) findViewById(R.id.btnWrite);

        //내장 메모리에 파일 쓰기
        btnWrite.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                try {

                    //file.xtx로 파일을 쓰기 모드로 열기. 파일 경로: /data/data/패키지명/files/file.txt
                    FileOutputStream outFs = openFileOutput("file.txt",
                            Context.MODE_PRIVATE);

                    //str에 들어있는 문자열을 파일에 쓴다.
                    String str = "쿡북 안드로이드";
                    outFs.write(str.getBytes()); //getBytes()를 이용해 문자열을 byte[] 형으로 변경
                    outFs.close();// 파일 닫기


                    Toast.makeText(getApplicationContext(), "file.txt가 생성됨", Toast.
                            LENGTH_SHORT);

                } catch (IOException e) {}

            }
        });

        //내장 메모리에서 파일 읽기
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    //내장 메모리의 /data/data/패키지명/files/file.txt파일 읽어오기
                    FileInputStream inFs = openFileInput("file.txt");

                    byte[] txt = new byte[30];//데이터 형이 'byte'이고 길이 30인 배열 생성
                    inFs.read(txt); //파일에서 16바이트를 읽어 txt 배열의 처음 16칸에 저장하고, 나머지 14칸은 0으로 남
                    String str = new String(txt); //바이트 배열을 문자열로 변환

                    //읽어온 메세지를 토스트 메시지로 출력
                    Toast.makeText(getApplicationContext(), str , Toast.LENGTH_SHORT).show();
                    inFs.close();// 파일 닫기

                } catch(IOException e){
                    Toast.makeText(getApplicationContext(), "파일 없음", Toast.
                            LENGTH_SHORT).show();
                }
            }
        });



    }//onCreate메소드 닫기
}//MainActivity닫기