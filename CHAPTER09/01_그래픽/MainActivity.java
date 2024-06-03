package com.example.miniproject09_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 전역변수 선언 -> 메뉴에서 선택한 것이 선인지, 원인지 구분하기 위해 사용됨
    final static int LINE = 1, CIRCLE = 2;
    static int curShape = LINE; //현재 선택된 도형의 디폴트를 line으로 설정

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
        setTitle("간단 일기장");

    }

    @Override
    // 메뉴 항목을 추가
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0,1,0,"선 그리기"); // 첫 번째 메뉴 항목 추가
        menu.add(0,2,0,"원 그리기"); // 두 번째 메뉴 항목 추가
        return true;
    }

    @Override
    // 사용자가 메뉴 항목을 선택했을 때 호출될 메서드
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case 1:
                curShape = LINE; //// 선 그리기를 선택했을 때의 동작
                return true;

            case  2:
                curShape = CIRCLE; // 원 그리기를 선택했을 때의 동작
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private static class MyGraphicView extends View {

        int startX = -1, startY = -1, stopX = -1, stopY= -1; //시작점과 끝점 좌표를 저장하기 위한 변수 4개

        public MyGraphicView(Context context){
            super(context);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    startX = (int) event.getX();
                    startY = (int) event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_UP:
                    stopX = (int) event.getX();
                    stopY = (int) event.getY();
                    this.invalidate();
                    break;
            }
            return true;
        }

        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setAntiAlias(true); // 선이 부드럽게 보이게 하는 속성 활성화
            paint.setStrokeWidth(10); // 선의 두께
            paint.setStyle(Paint.Style.STROKE); // 도형의 외곽선만 그리도록 설정
            paint.setColor(Color.GREEN); // 색상 설정

            switch (curShape){
                case LINE:
                    canvas.drawLine(startX,startY,stopX,stopY,paint);
                    break;
                case CIRCLE:
                    int radius = (int) Math.sqrt(Math.pow(stopX - startX, 2)
                    + Math.pow(stopY - startY, 2)); //시작점과 끝점의 거리를 계산하여 반지름으로 사용한다.
                canvas.drawCircle(startX, startY, radius, paint);
                break;
            }
        }
    }//MyGraphicView닫기
    
    
}//MainActivity닫기