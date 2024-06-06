package com.example.miniproject10_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("직접 풀어보기 10-01");

        final RadioButton rdoSecond = (RadioButton) findViewById(R.id.rdoSecond);

        //새 화면 열기 버튼 정의 및 기능 코딩
        Button btnNewActivity = (Button) findViewById(R.id.btnNewActivity);
        btnNewActivity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                
                Intent intent;

                if (rdoSecond.isChecked() == true)
                    intent = new Intent(getApplicationContext(),
                            SecondActivity.class);
                else
                    intent = new Intent(getApplicationContext(),
                            ThirdActivity.class);

                startActivity(intent);
            }
        });
    }



}