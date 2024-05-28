package com.example.ex08_08;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRead;
        final EditText edtRaw; //edtRaw.setTest(new String(txt));의 내부클래스에 접근하기 위해 final로 선언
        btnRead = (Button) findViewById(R.id.btnRead);
        edtRaw = (EditText) findViewById(R.id.edtRaw);
        
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                try { //raw폴더의 raw_test.txt파일에 접근하기 위해
                    

                    InputStream inputS = getResources().openRawResource(R.raw.raw_test);
                    //getResources()는 현재 패키지의 리소스 반환
                    //openRawResource()는 /res/raw파일을 읽기용으로 연다

                    byte[] txt = new byte[inputS.available()];
                    //inputS.available(): 입력 스트림에서 읽을 수 있는 바이트 수를 반환함.
                    //txt변수는 raw_test.txt 파일의 크기만큼 지정됨

                    inputS.read(txt); //입력 스트림에서 데이터를 읽어 txt 변수에 넣기
                    edtRaw.setText(new String(txt));// txt변수를 문자열로 변환하여 에디트텍스트에 출력
                    inputS.close();// 파일 닫기

                } catch (IOException e) { }
            }
        });


    }
}