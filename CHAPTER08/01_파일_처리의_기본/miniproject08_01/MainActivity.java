package com.example.miniproject08_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DatePicker dp;
    EditText edtDiary;
    Button btnWrite;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 일기장");

        dp = (DatePicker) findViewById(R.id.datePicker1);
        edtDiary = (EditText) findViewById(R.id.edtDiary);
        btnWrite = (Button) findViewById(R.id.btnWrite);

        //java.util.Calendar 클래스를 이용하여 현재 연,월,일 구하기
        Calendar cal = Calendar.getInstance();
        int cYear = cal.get((Calendar.YEAR));
        int cMonth = cal.get((Calendar.MONTH));
        int cDay = cal.get((Calendar.DAY_OF_MONTH));

        //데이트피커를 init() 메소드로 초기화
        dp.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener(){
            @Override
            //날짜가 변경되었을 때 동작함
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                //현재 날짜에 해당하는 파일 일므을 '연_월_일.txt'로 만들기
                fileName = Integer.toString(year) + "_"
                        + Integer.toString(monthOfYear + 1) + "_"
                        + Integer.toString(dayOfMonth) + ".txt";

                String str = readDiary(fileName); //현재 날짜의 일기 파일을 읽어들이기
                //읽어온 내용을 에디트텍스트에 출력하기
                edtDiary.setText(str);
                btnWrite.setEnabled(true);

            }
        });

        btnWrite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try{
                    //일기 파일(연_월_일.txt)을 쓰기 모드로 열기
                    FileOutputStream outFs = openFileOutput(fileName,
                            Context.MODE_PRIVATE);
                    String str = edtDiary.getText().toString();

                    outFs.write(str.getBytes());
                    outFs.close();

                    //저장된 파일 이름을 토스트 메시지로 출력하기
                    Toast.makeText(getApplicationContext(), fileName+"이 저장됨", Toast.
                            LENGTH_SHORT).show();

                } catch (IOException e) { }

            }
        });


    }//onCreate닫기


    //현재 날짜의 파일(연_월_일.txt)을 읽어 일기의 내용을 반환하는 메소드
    String readDiary(String fName) { //파일 이름(연_월_일.txt)을 파라미터로 받음

        //읽어온 일기를 저장할 문자열 변수와 입력 파일 스트림 변수 선언
        String diaryStr = null;
        FileInputStream inFs;

        try {
            inFs = openFileInput(fName); //일기 파일을 열어 입력 파일 스트림에 저장
            byte[] txt = new byte[500]; //byte형 변수를 선언

            inFs.read(txt); // 파일 내용을 txt에 읽은 후
            inFs.close();// 파일 닫기

            //읽어온 txt를 문자열로 변경한 후, trim() 메소드로 앞뒤 공백 제거 후, 반환할 diaryStr 변수에 대입함.
            diaryStr = (new String(txt)).trim();
            btnWrite.setText("수정하기"); //일기가 있는 상태니까 버튼의 글자를 '수정하기'로 변경

        } catch (IOException e) { //파일이 없다면 catch구문 실행

            edtDiary.setHint("일기 없음");
            btnWrite.setText("새로 저장");
        }

        return diaryStr;
    }//readDiary닫기

}//MainActivity닫기