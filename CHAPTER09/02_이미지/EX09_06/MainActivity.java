package com.example.ex09_06;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));

    }


    private static class MyGraphicView extends View {
        public MyGraphicView(Context context){ //Context: 어플리케이션의 현재 상태
            super(context);
        }

        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);

            //res/drawable의 이미지에 접근
            Bitmap picture = BitmapFactory.decodeResource(getResources(),
                    R.drawable.jeju14);

            // 그림을 중앙에 출력하기 위해 좌표 구하기
            int picX = (this.getWidth() - picture.getWidth())/2; //현재 뷰의 넓이 - 이미지의 넓이
            int picY = (this.getHeight() - picture.getHeight())/2;

            canvas.drawBitmap(picture, picX, picY, null); //위에서 설정한 값을 화면에 출력하기
            picture.recycle(); //비트맵 리소스 해제

        }
    }

}

