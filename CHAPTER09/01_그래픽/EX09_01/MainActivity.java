package com.example.ex09_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
    }

    private static class MyGraphicView extends View{
        public MyGraphicView(Context context){
            
            super(context);
        }

        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();

            // 빨간색 선
            paint.setColor(Color.RED);
            paint.setStrokeWidth(20);
            canvas.drawLine(20, 20, 600, 20, paint);
            //(20,20)에서 시작해서 (600,20)에서 끝남

            // 노란색 선
            paint.setColor(Color.YELLOW);
            paint.setStrokeWidth(10);
            canvas.drawLine(20, 60, 600, 60, paint);
            
            // 초록색 선
            paint.setAntiAlias(true);
            paint.setColor(Color.GREEN);
            canvas.drawLine(20, 120, 600, 120, paint);
            //(20,20)에서 시작해서 (600,20)에서 끝남
            
            // 파란색 선
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(10);
            canvas.drawLine(20, 160, 600, 160, paint);



            // 빨간색 정사각형
            paint.setColor(Color.RED);
            paint.setStrokeWidth(0);

            // 첫번째 정사각형
            paint.setStyle(Paint.Style.FILL);
            Rect rect1 = new Rect(20,160,20 + 200, 160 + 200);
            canvas.drawRect(rect1, paint);

            // 두번째 정사각형
            paint.setStyle(Paint.Style.STROKE);
            Rect rect2 = new Rect(260,160,260 + 200, 160 + 200);
            canvas.drawRect(rect2, paint);

            // 세번째 정사각형
            RectF rect3 = new RectF(500, 160, 500+200, 160+200);
            canvas.drawRoundRect(rect3, 40, 40, paint);
            
            // 빨간색 동그라미
            canvas.drawCircle(220, 540, 100, paint);

            // 지그재그 선
            paint.setStrokeWidth(10);
            Path path1 = new Path();
            path1.moveTo(20, 580);
            path1.lineTo(20 + 100, 580 + 100);
            path1.lineTo(20 + 200, 580);
            path1.lineTo(20 + 300, 580 + 100);
            path1.lineTo(20 + 400, 580);
            canvas.drawPath(path1, paint);

            // 글자
            paint.setStrokeWidth(0);
            paint.setTextSize(60);
            canvas.drawText("안드로이드", 20, 780, paint);
            
        }
    }

    
    

}