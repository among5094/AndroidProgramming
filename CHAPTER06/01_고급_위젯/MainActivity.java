package com.example.miniproject06_01;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
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

        
        /*라디오 버튼을 클릭했을 때의 이벤트 리스너*/
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

        //예약 시작을 클릭하면 크로노미터가 시작되도록 클릭 리스너 작성
        btnStart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                chrono.setBase(SystemClock.elapsedRealtime()); //크로노미터가 현재 시간부터 시간을 측정하기 시작하도록 설정(크로노미터 초기화)
                chrono.start();
                chrono.setTextColor(Color.RED); //시작을 알리기 위한 red
            }
        });

        //예약 완료를 클릭하면 정지되도록 클릭 리스너 작성
        btnEnd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                chrono.stop();
                chrono.setTextColor(Color.BLUE);

            }
        });

        //사용자가 캘린더 뷰에서 날짜를 선택할 때마다 선택된 연도, 월, 일이 저장됨
        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth){
                selectYear=year;
                selectMonth=month+1; //1월부터 시작하니까 1더해주기
                selectDay=dayOfMonth;

            }
        });

        // 텍스트 뷰에 표시하기
        //int year, int month, int dayOfMonth 이기 때문에 전부 int로 선언되어서, 텍스트 뷰에 표시하려면 toString해야됨
        tvYear.setText(Integer.toString(selectYear));
        tvMonth.setText(Integer.toString(selectMonth));
        tvDay.setText(Integer.toString(selectDay));

        tvHour.setText(Integer.toString(tPicker.getCurrentHour()));
        tvMinute.setText(Integer.toString(tPicker.getCurrentMinute()));


    }
}

