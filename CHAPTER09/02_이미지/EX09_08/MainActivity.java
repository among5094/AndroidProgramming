package com.example.ex09_08;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
    }

    private static class MyGraphicView extends View {

        // MyGraphicView 클래스의 생성자임.
        // 역할: 새로운 MyGraphicView 객체를 초기화함
        public MyGraphicView(Context context){
            super(context);
        }

        @Override
        // 역할: 뷰의 캔버스에 그래픽을 그림
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);
            Bitmap picture = BitmapFactory.decodeResource(getResources(),
                    R.drawable.lena256);

            // 화면 중앙값 구하기
            int picX = (this.getWidth() - picture.getWidth()) /2;
            int picY = (this.getHeight() - picture.getHeight()) /2;


            Paint paint = new Paint(); // Paint 객체 선언 및 생성
            BlurMaskFilter bMask; // BlurMaskFilter 객체 선언
            bMask = new BlurMaskFilter(30, BlurMaskFilter.Blur.NORMAL); // BlurMaskFilter 객체 생성

            paint.setMaskFilter(bMask); // Paint 객체에 BlurMaskFilter 설정
            canvas.drawBitmap(picture, picX, picY, paint); // picture 비트맵을 picX, picY 위치에 그림
            picture.recycle(); // picture 비트맵 메모리 해

        }
    }

}