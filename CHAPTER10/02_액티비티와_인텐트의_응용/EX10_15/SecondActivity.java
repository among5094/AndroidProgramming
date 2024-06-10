package com.example.ex10_15;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        setTitle("Second 액티비티");

        //   MainActivity에서 startActivityForResult(intent, 0);로 호출한 인텐트를 수신함
        Intent inIntent = getIntent();

        // 인텐트에 포함된 "Num1"과 "Num2" 값을 추출한 후
        // 추출된 두 값은 각각 정수형으로 변환되어 hapValue 변수에 더한 결과로 저장됨.
        final int hapValue = inIntent.getIntExtra("Num1", 0)
                + inIntent.getIntExtra("Num2", 0);

        // <돌아가기> 버튼 코딩
        Button btnReturn = (Button) findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // ③ MainActivity로 결과를 전달하기 위해 새로운 인텐트 객체(outIntent) 생성
                Intent outIntent = new Intent(getApplicationContext(),
                        MainActivity.class);

                // ④ putExtra()로 데이터를 넣은 다음
                // hapValue 값을 인텐트에 추가하여 "Hap"이라는 이름으로 전달
                outIntent.putExtra("Hap", hapValue);

                // ⑤ setResult()로 MainActivity에 돌려준다.
                setResult(RESULT_OK, outIntent);
                finish();// ⑥ 현재 액티비티를 종료하여 MainActivity로 돌아감
            }
        });
    }
}
