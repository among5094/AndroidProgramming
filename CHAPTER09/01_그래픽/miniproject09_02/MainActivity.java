package com.example.miniproject09_02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // 전역변수 선언 -> 메뉴에서 선택한 것이 선인지, 원인지, 사각형인지 구분하기 위해 사용됨
    final static int LINE = 1, CIRCLE = 2, RECTANGLE = 3;
    static int curShape = LINE; // 현재 선택된 도형의 디폴트를 선으로 설정
    static int curColor = Color.GREEN; // 기본 색상을 녹색으로 설정

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this)); // MyGraphicView를 화면에 설정
        setTitle("간단 일기장"); // 앱 타이틀 설정
    }

    @Override
    // 메뉴 항목을 추가
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, "선 그리기"); // 첫 번째 메뉴 항목 추가
        menu.add(0, 2, 0, "원 그리기"); // 두 번째 메뉴 항목 추가
        menu.add(0, 3, 0, "사각형 그리기"); // 세 번째 메뉴 항목 추가

        // 색상 선택 서브 메뉴 추가
        Menu colorMenu = menu.addSubMenu("색상 선택");
        colorMenu.add(0, 4, 0, "빨강"); // 서브 메뉴 항목 추가 (빨강)
        colorMenu.add(0, 5, 0, "초록"); // 서브 메뉴 항목 추가 (초록)
        colorMenu.add(0, 6, 0, "파랑"); // 서브 메뉴 항목 추가 (파랑)

        return true;
    }

    @Override
    // 사용자가 메뉴 항목을 선택했을 때 호출될 메서드
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case 1:
                curShape = LINE; // 선 그리기를 선택했을 때의 동작
                return true;
            case 2:
                curShape = CIRCLE; // 원 그리기를 선택했을 때의 동작
                return true;
            case 3:
                curShape = RECTANGLE; // 사각형 그리기를 선택했을 때의 동작
                return true;
            case 4:
                curColor = Color.RED; // 빨간색 선택
                return true;
            case 5:
                curColor = Color.GREEN; // 초록색 선택
                return true;
            case 6:
                curColor = Color.BLUE; // 파란색 선택
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // MyGraphicView 클래스 정의 - 도형을 그리는 뷰
    private static class MyGraphicView extends View {

        int startX = -1, startY = -1, stopX = -1, stopY = -1; // 시작점과 끝점 좌표를 저장하기 위한 변수 4개

        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: // 터치 시작
                    startX = (int) event.getX();
                    startY = (int) event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_UP: // 터치 끝
                    stopX = (int) event.getX();
                    stopY = (int) event.getY();
                    this.invalidate(); // 화면을 갱신하여 onDraw를 호출
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
            paint.setColor(curColor); // 현재 선택된 색상으로 설정

            // 현재 선택된 도형에 따라 그리기
            switch (curShape) {
                case LINE:
                    canvas.drawLine(startX, startY, stopX, stopY, paint); // 선 그리기
                    break;
                case CIRCLE:
                    int radius = (int) Math.sqrt(Math.pow(stopX - startX, 2)
                            + Math.pow(stopY - startY, 2)); // 시작점과 끝점의 거리를 계산하여 반지름으로 사용
                    canvas.drawCircle(startX, startY, radius, paint); // 원 그리기
                    break;
                case RECTANGLE:
                    canvas.drawRect(startX, startY, stopX, stopY, paint); // 사각형 그리기
                    break;
            }
        }
    } // MyGraphicView 클래스 닫기

} // MainActivity 클래스 닫기