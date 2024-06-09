package com.example.ex09_07;

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
        //onDraw() : 뷰가 화면에 표시될 때 어떻게 그려질지 결정하는 역할
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);

            //res/drawable의 이미지에 접근
            Bitmap picture = BitmapFactory.decodeResource(getResources(),
                    R.drawable.small);


            int cenX = this.getWidth()/2;
            int cenY = this.getHeight()/2;

            int picX = (this.getWidth() - picture.getWidth())/2; //현재 뷰의 넓이 - 이미지의 넓이
            int picY = (this.getHeight() - picture.getHeight())/2; // 현재 뷰의 높이 - 이미지의 높이

            //이미지 회전: rotate(회전각도 설정, 회전 중심점의 x좌표, 회전 중심점 y좌표)
            canvas.rotate(45,cenX,cenY);
            canvas.drawBitmap(picture, picX, picY, null);

            //이미지 이동: translate(x축으로 얼만큼 이동할지, y축으로 얼만큼 이동할지)
            canvas.translate(-150, 200);
            canvas.drawBitmap(picture, picX, picY, null);

            //이미지 확대or축소: scale(x축 및 y축 방향으로의 확대/축소 비율, 확대/축소의 중심점 좌표)
            canvas.scale(2, 2, cenX, cenY);
            canvas.drawBitmap(picture, picX, picY, null);

            //이미지 기울임: skew(x축 방향의 기울임 각도의 탄젠트, y축 방향의 기울임 각도의 탄젠트)
            canvas.skew(0.3f, 0.3f);
            canvas.drawBitmap(picture, picX, picY, null);

            picture.recycle(); //비트맵 리소스 해제
            /*
            *  비트맵 객체는 화면에 표시되는 이미지 데이터를 저장하기 때문에 메모리를 사용함.
            *  이미지 데이터는 픽셀 정보로 구성되어 있고,이미지의 해상도가 높을수록, 즉 픽셀 수가 많을수록 더 많은 메모리가 필요함.
            *  비트맵 객체는 이미지 데이터를 직접 저장하기 때문에 메모리 관리를 위해서 해제도 해줘야됨*/

        }
    }


}