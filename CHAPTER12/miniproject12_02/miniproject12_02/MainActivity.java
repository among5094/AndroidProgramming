package com.example.miniproject12_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    myDBHelper myHelper; // 새로 생성한 myDBHelper 클래스 변수
    EditText edtName, edtNumber, edtNameResult, edtNumberResult;
    Button btnInit, btnInsert, btnSelect;
    SQLiteDatabase sqlDB; // SQLiteDatabase 클래스 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("가수 그룹 관리 DB");

        edtName = (EditText) findViewById(R.id.edtName);
        edtNumber = (EditText) findViewById(R.id.edtNumber);
        edtNameResult = (EditText) findViewById(R.id.edtNameResult);
        edtNumberResult = (EditText) findViewById(R.id.edtNumberResult);
        btnInit = (Button) findViewById(R.id.btnInit);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnSelect = (Button) findViewById(R.id.btnSelect);

        // <초기화>를 클릭했을 때 동작하는 리스너 코딩
        myHelper = new myDBHelper(this); //myDBHelper 인스턴스 생성
        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getWritableDatabase(); //데이터베이스를 쓰기 모드로 연다
                myHelper.onUpgrade(sqlDB,1,2); 
                //onUpgrade 메서드를 직접 호출하여 데이터베이스의 버전을 업그레이드함.
                sqlDB.close(); // DB 닫기
            }
        });

    }

    // SQLiteOpenHelper 클래스 상속받는 myDBHelper
    public class myDBHelper extends SQLiteOpenHelper {
        
        // 생성자 정의
        public myDBHelper(Context context){
            super(context, "groupDB", null, 1);
        }

        // 테이블을 생성하는 기능 코딩
        public void onCreate(SQLiteDatabase db){
            // SQLiteDatabase 클래스의 execSQL() 메소드로 실행
            db.execSQL("create table groupTBL (gName CHAR(20) primary key," +
                    "gNumber INTEGER); ");
        }

        // 테이블을 삭제한 후 다시 생성
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("drop table if exists groupTBL");// 테이블을 삭제하고 새로 생성한다
            onCreate(db); // 초기화할 때 호출함
        }
    }
}

