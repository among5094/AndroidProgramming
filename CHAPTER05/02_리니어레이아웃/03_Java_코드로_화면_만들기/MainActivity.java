package com.example.miniproject05_03;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main); //이 함수는 xml파일을 화면에 출력하는 역할을 하기 때문에 오류남.

        //객체 params에 대한 설정
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,1);
        //new LinearLayout.LayoutParams(int width, int height, float weight)


        //객체 baseLayout 대한 설정
        LinearLayout baseLayout = new LinearLayout(this); //this: MainActivity의 현재 인스턴스를 참조
        baseLayout.setOrientation(LinearLayout.VERTICAL); //android:orientation="vertical"
        baseLayout.setBackgroundColor(Color.rgb(0,255,0)); //android:background="#00FF00"
        
        //두 객체를 화면에 출력
        setContentView(baseLayout, params);
        //setContentView(View view, ViewGroup.LayoutParams params)이라서 두 객체의 위치가 바뀌면 오류임.
        //baseLayout은 뷰 그 자체이고, params는 뷰의 레이아웃 파라미터를 정의하는 객체이기 때문
        
        //에디트텍스트
        EditText editText = new EditText(this);
        editText.setText("IT Cookbook. Android");
        editText.setTextColor(Color.BLACK);
        editText.setVisibility(View.INVISIBLE); //보이지 않게 설정했다가
        
        //텍스트뷰 생성
        TextView textView = new TextView(this);
        textView.setText("IT Cookbook. Android");
        textView.setTextColor(Color.MAGENTA);
        
        //버튼 객체 생성
        Button btn = new Button(this); //btn객체는 MainActivity의 현재 인스턴스를 참조
        btn.setText("버튼입니다."); //btn에 글자 설정
        btn.setBackgroundColor(Color.YELLOW); //btn에 색상 지정

        //객체를 레이아웃에 출력하기
        baseLayout.addView(editText);
        baseLayout.addView(textView);
        baseLayout.addView(btn);

        //버튼을 클릭했을 때 동작하는 리스너
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                editText.setVisibility(View.VISIBLE); //버튼이 눌리면 에디트텍스트가 보이게 설정
            }
        });
    }
}