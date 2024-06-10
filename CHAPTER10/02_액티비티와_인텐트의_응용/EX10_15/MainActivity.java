package com.example.ex10_15;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("메인 액티비티");

        // <더하기>버튼 정의
        Button btnNewActivity = (Button) findViewById(R.id.btnNewActivity);
        btnNewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 변수 선언
                EditText edtNum1 = (EditText) findViewById(R.id.edtNum1);
                EditText edtNum2 = (EditText) findViewById(R.id.edtNum2);

                // SecondActivity를 호출하기 위해 Intent 객체를 생성 후
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);

                // ① putExtra()로 인텐트에 데이터 넣기
                intent.putExtra("Num1", Integer.parseInt(edtNum1.getText().toString()));//parseInt:  문자열을 정수로 변환
                intent.putExtra("Num2", Integer.parseInt(edtNum2.getText().toString()));

                // ② 데이터를 돌려받으려면 0이상을 쓰면 된다.
                startActivityForResult(intent, 0);

            }
        });




    }// onCreate 닫기

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            int hap = data.getIntExtra("Hap", 0);
            Toast.makeText(getApplicationContext(),
                    "합계 :" + hap, Toast.LENGTH_SHORT).show();

        }

    }



}// MainActivity 닫기