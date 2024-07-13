package com.example.miniproject13_01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;

    // 전역 변수
    ListView listViewMP3;
    Button btnPlay, btnStop;
    TextView tvMP3;
    ProgressBar pbMP3;

    ArrayList<String> mp3List;
    String selectedMP3;

    String mp3Path = Environment.getExternalStorageDirectory().getPath() + "/";
    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 MP3 플레이어");

        // 권한이 이미 부여되어 있는지 확인
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // 권한이 부여되지 않았으면 권한 요청
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
        } else {
            // 권한이 이미 부여되어 있으면 초기화
            initializePlayer();
        }

        // XML 파일(activity_main.xml)에서 ListView와 다른 뷰를 찾아 변수에 할당
        listViewMP3 = findViewById(R.id.listViewMP3);
        btnPlay = findViewById(R.id.btnPlay);
        btnStop = findViewById(R.id.btnStop);
        tvMP3 = findViewById(R.id.tvMP3);
        pbMP3 = findViewById(R.id.pbMP3);

        btnStop.setClickable(false); // 초기에는 <중지> 버튼을 클릭할 수 없도록 설정
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 권한이 부여되었으면 초기화
                initializePlayer();
            } else {
                // 권한이 거부된 경우
                Toast.makeText(this, "외부 저장소 접근 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
                finish(); // 앱 종료
            }
        }
    }

    private void initializePlayer() {
        // MP3 파일 이름을 저장할 ArrayList를 초기화
        mp3List = new ArrayList<>();

        // 외부 저장소의 파일 목록을 가져옴. mp3Path: 외부 저장소의 루트 디렉토리
        File[] listFiles = new File(mp3Path).listFiles();
        String fileName, extName;

        if (listFiles != null) {
            // 외부 저장소에서 모든 파일을 검사 -> 확장자가 'mp3'인 파일의 이름을 mp3List에 추가
            for (File file : listFiles) {
                fileName = file.getName(); // 현재 파일 이름 가져옴
                extName = fileName.substring(fileName.length() - 3); // 끝에서 3글자 추출

                // 파일 확장자가 "mp3"인지 확인
                if (extName.equals("mp3")) {
                    // 확장자가 "mp3"라면, 해당 파일 이름을 mp3List에 추가
                    mp3List.add(fileName);
                }
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_single_choice, mp3List); // ArrayAdapter 생성
        listViewMP3.setChoiceMode(ListView.CHOICE_MODE_SINGLE); // ListView의 선택 모드를 단일로 설정
        listViewMP3.setAdapter(adapter); // ListView는 mp3List에 있는 데이터를 표시함
        listViewMP3.setItemChecked(0, true); // 첫번째 항목(인덱스 0)을 선택된 상태로 설정

        // 리스트뷰 클릭할 때마다 selectedMP3 변수에 저장됨
        listViewMP3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                selectedMP3 = mp3List.get(arg2);
            }
        });

        // ListView의 첫 번째 항목을 기본적으로 선택된 항목으로 설정
        // 처음 실행할 때 아무것도 선택하지 않은 상태에서 <듣기>버튼을 누를 수도 있으니까 그런 오류 방지
        if (!mp3List.isEmpty()) {
            selectedMP3 = mp3List.get(0);
        }

        // <듣기> 버튼 클릭했을 때
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mPlayer = new MediaPlayer(); // 새로운 MediaPlayer 객체를 생성
                    mPlayer.setDataSource(mp3Path + selectedMP3); // 선택된 MP3 파일의 경로를 데이터 소스로 설정
                    mPlayer.prepare(); // MediaPlayer를 준비 상태로 만듦. 이때 파일을 로드
                    mPlayer.start(); // MP3 파일을 재생
                    btnPlay.setClickable(false); // <듣기> 버튼을 클릭할 수 없도록 비활성화
                    btnStop.setClickable(true); // <중지> 버튼을 클릭할 수 있도록 활성화
                    tvMP3.setText("실행중인 음악 : " + selectedMP3); // 재생 중인 MP3 파일 이름 표시
                    pbMP3.setVisibility(View.VISIBLE); // ProgressBar 표시
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // <중지> 버튼 클릭했을 때
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    mPlayer.stop(); // 현재 재생 중인 MP3 파일을 중지
                    mPlayer.reset(); // MediaPlayer를 초기 상태로 되돌림
                    btnPlay.setClickable(true); // <듣기> 버튼을 클릭할 수 있도록 활성화
                    btnStop.setClickable(false); // <중지> 버튼을 클릭할 수 없도록 비활성화
                    tvMP3.setText("실행중인 음악 : "); // 현재 재생 중인 음악이 없음을 나타내기 위해 텍스트뷰를 업데이트
                    pbMP3.setVisibility(View.INVISIBLE); // ProgressBar 숨김. 음악 재생이 중지되었음을 나타냄

            }
        });
    }
}
