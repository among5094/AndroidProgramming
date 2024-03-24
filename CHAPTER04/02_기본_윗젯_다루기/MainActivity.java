package com.example.miniproject04_03;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    //Step1.변수 선언
    EditText edit1, edit2;
    Button BtnAdd,BtnSub,BtnMul,BtnDiv,BtnRemain;
    TextView TextResult;
    String num1,num2;
    Integer result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("초간단 계산기");

        //Step2.변수에 윗젯 대입
        edit1=(EditText) findViewById(R.id.Edit1);
        edit2=(EditText) findViewById(R.id.Edit2);

        BtnAdd=(Button) findViewById(R.id.BtnAdd);
        BtnSub=(Button) findViewById(R.id.BtnSub);
        BtnMul=(Button) findViewById(R.id.BtnMul);
        BtnDiv=(Button) findViewById(R.id.BtnDiv);
        BtnRemain=(Button) findViewById(R.id.BtnRemain);

        TextResult =(TextView) findViewById(R.id.TextResult);

        //Step3.버튼을 클릭할 때 "동작하는 클래스 정의"
        BtnAdd.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1){
                num1=edit1.getText().toString();//입력을 string으로 받음
                num2=edit2.getText().toString();
                result=Integer.parseInt(num1)+Integer.parseInt(num2);
                TextResult.setText("계산 결과: "+result.toString()); //그래서 출력을 다시 String으로 바꿔야됨
                return false;
            }
        });

        BtnSub.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1){
                num1=edit1.getText().toString();//입력을 string으로 받음
                num2=edit2.getText().toString();
                result=Integer.parseInt(num1)-Integer.parseInt(num2);
                TextResult.setText("계산 결과: "+result.toString()); //그래서 출력을 다시 String으로 바꿔야됨
                return false;
            }
        });

        BtnMul.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1){
                num1=edit1.getText().toString();//입력을 string으로 받음
                num2=edit2.getText().toString();
                result=Integer.parseInt(num1)* Integer.parseInt(num2);
                TextResult.setText("계산 결과: "+result.toString()); //그래서 출력을 다시 String으로 바꿔야됨
                return false;
            }
        });

        BtnDiv.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1){
                num1=edit1.getText().toString();//입력을 string으로 받음
                num2=edit2.getText().toString();
                result=Integer.parseInt(num1)/Integer.parseInt(num2);
                TextResult.setText("계산 결과: "+result.toString()); //그래서 출력을 다시 String으로 바꿔야됨
                return false;
            }
        });

        BtnRemain.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View arg0, MotionEvent arg1){
                num1=edit1.getText().toString();//입력을 string으로 받음
                num2=edit2.getText().toString();
                result=Integer.parseInt(num1)%Integer.parseInt(num2);
                TextResult.setText("계산 결과: "+result.toString()); //그래서 출력을 다시 String으로 바꿔야됨
                return false;
            }
        });


    }
}