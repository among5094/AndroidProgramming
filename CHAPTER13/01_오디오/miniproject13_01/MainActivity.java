package com.example.miniproject13_01;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listViewMP3;
    Button btnPlay, btnStop;
    TextView tvMP3;
    ProgressBar pbMP3;

    ArrayList<String> mp3List;
    String selectedMP3;

    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 MP3 플레이어");

        listViewMP3 = findViewById(R.id.listViewMP3);
        btnPlay = findViewById(R.id.btnPlay);
        btnStop = findViewById(R.id.btnStop);
        tvMP3 = findViewById(R.id.tvMP3);
        pbMP3 = findViewById(R.id.pbMP3);

        initializePlayer();

        btnStop.setClickable(false);
    }

    private void initializePlayer() {
        // MP3 파일 이름을 저장할 ArrayList를 초기화
        mp3List = new ArrayList<>();
        // 예제: res/raw 폴더에 있는 파일 이름들
        String[] rawFiles = {"song1", "song2", "song3"};

        for (String file : rawFiles) {
            mp3List.add(file + ".mp3");  // 파일 확장자를 추가하여 사용자에게 보여주기
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_single_choice, mp3List);
        listViewMP3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listViewMP3.setAdapter(adapter);

        listViewMP3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedMP3 = rawFiles[position];
            }
        });

        if (!mp3List.isEmpty()) {
            listViewMP3.setItemChecked(0, true);
            selectedMP3 = rawFiles[0];
        }

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int resId = getResources().getIdentifier(selectedMP3, "raw", getPackageName());
                if (mPlayer != null) {
                    mPlayer.release();
                }
                mPlayer = MediaPlayer.create(MainActivity.this, resId);
                mPlayer.start();
                btnPlay.setClickable(false);
                btnStop.setClickable(true);
                tvMP3.setText("실행중인 음악 : " + selectedMP3 + ".mp3");
                pbMP3.setVisibility(View.VISIBLE);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPlayer != null) {
                    mPlayer.stop();
                    mPlayer.reset();
                }
                btnPlay.setClickable(true);
                btnStop.setClickable(false);
                tvMP3.setText("실행중인 음악 : ");
                pbMP3.setVisibility(View.INVISIBLE);
            }
        });
    }
}
