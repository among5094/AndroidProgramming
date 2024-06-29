package com.example.miniproject11_01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("그리드뷰 영화 포스터");

        final GridView gv = (GridView) findViewById(R.id.gridView1);
        MyGridAdapter gAdapter = new MyGridAdapter(this);
        gv.setAdapter(gAdapter);

    }


    // BaseAdapter를 상속받는 MyGridAdapter를 정의함
    public class MyGridAdapter extends BaseAdapter{
        Context context; //위에서 넘긴 this 컨텍스트를 생성자로 받은 다음 context 변수에 대입한다.
        public MyGridAdapter(Context c){
            context = c;
        }

        //
        public int getCount(){
            return posterID.length;
        }

        public Object getItem(int arg0){
            return null;
        }

        public long getItemId(int arg0){
            return 0;
        }

        Integer[] posterID = { R.drawable.mov01, R.drawable.mov02,
                R.drawable.mov03, R.drawable.mov04, R.drawable.mov05,
                R.drawable.mov06, R.drawable.mov07, R.drawable.mov08,
                R.drawable.mov09, R.drawable.mov10, R.drawable.mov01,
                R.drawable.mov02, R.drawable.mov03, R.drawable.mov04,
                R.drawable.mov05, R.drawable.mov06, R.drawable.mov07,
                R.drawable.mov08, R.drawable.mov09, R.drawable.mov10,
                R.drawable.mov01, R.drawable.mov02, R.drawable.mov03,
                R.drawable.mov04, R.drawable.mov05, R.drawable.mov06,
                R.drawable.mov07, R.drawable.mov08, R.drawable.mov09,
                R.drawable.mov10 };


        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(context); //이미지 뷰 생성
            imageView.setLayoutParams(new ViewGroup.LayoutParams(200,300)); //이미지뷰 크기를 200x300으로설정
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER); //이미지뷰를 각 그리드뷰 칸의 중앙에 배치
            imageView.setPadding(5,5,5,5); // 보는 눈이 편하도록 간격 설정

            // 이미지뷰에 영화 포스터 1개를 적용
            imageView.setImageResource(posterID[position]);

            // getView() 메소드의 파라미터인 position을 클릭 리스너 안에서 사용하기 위해 pos 변수에 대입
            final int pos = position;

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    
                    // dialog.xml 파일을 인플레이트하여 diglogView에 대입하기
                    View dialogView = (View) View.inflate(MainActivity.this,
                            R.layout.dialog, null);
                    
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);

                    // dialog.xml의 이미지뷰에 접근하고, 이미지뷰에 클릭한 영화 포스터의 아이디를 설정한다.
                    ImageView ivPoster = (ImageView) dialogView.findViewById(R.id.ivPoster);
                    ivPoster.setImageResource(posterID[pos]);
                    dlg.setTitle("큰 포스터");
                    dlg.setIcon(R.drawable.ic_launcher);
                    dlg.setView(dialogView); // 다이얼로그 보여주기
                    dlg.setNegativeButton("닫기", null); // 닫기 생성
                    dlg.show();
                }
            });

            return imageView;
        }

    }
}