package com.example.miniproject06_01;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.DatePicker;

public class MainActivity extends AppCompatActivity {

    //전역변수 선언
    Chronometer chrono;
    Button btnStart, btnEnd;
    RadioButton rdoCal, rdoTime;
    CalendarView calView;
    TimePicker tPicker;
    TextView tvYear, tvMonth, tvDay, tvHour, tvMinute;
    int selectYear, selectMonth, selectDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //위젯 변수 12개에 위젯 대입
        btnStart=(Button)findViewById(R.id.btnStar);
        btnEnd=(Button) findViewById(R.id.btnEnd);

        chrono=(Chronometer) findViewById(R.id.chronometer1);

        rdoCal=(RadioButton) findViewById(R.id.rdoCal);
        rdoTime=(RadioButton) findViewById(R.id.rdoTime);

        tPicker=(TimePicker) findViewById(R.id.timePicker1);
        calView=(CalendarView) findViewById(R.id.calendarView1);

        tvYear =(TextView) findViewById(R.id.tvYear);
        tvMonth =(TextView) findViewById(R.id.tvMonth);
        tvDay =(TextView) findViewById(R.id.tvDay);
        tvHour =(TextView) findViewById(R.id.tvHour);
        tvMinute =(TextView) findViewById(R.id.tvMinute);

        //처음엔 캘린더 뷰와 타임피커가 보이지 않게 설정하기
        tPicker.setVisibility(View.INVISIBLE);
        calView.setVisibility(View.INVISIBLE);

        //라디오 버튼(날짜 설정 라디오 버튼) 클릭 시 캘린더 뷰만 보이게 클래스 작성
        rdoCal.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){
                tPicker.setVisibility(View.INVISIBLE);
                calView.setVisibility(View.VISIBLE); //캘린더 뷰만 visible로 설정
            }
        });

        //라디오 버튼(시간 설정 라디오 버튼) 클릭 시 타임피커만 보이게 클래스 작성
        rdoTime.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){
                tPicker.setVisibility(View.VISIBLE); //타임피커만 visible로 설정
                calView.setVisibility(View.INVISIBLE);
            }
        });


    }
}











