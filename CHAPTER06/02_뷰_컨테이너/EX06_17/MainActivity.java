package com.example.ex06_17;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = getTabHost(); //1. 탭호스트 변수 생성

        TabSpec tabSpecSong=tabHost.newTabSpec("SONG").setIndicator("음악별"); //2. 탭스펙 생성
        tabSpecSong.setContent(R.id.tabSong); //3. 탭스펙을 탭에 붙이기
        tabHost.addTab(tabSpecSong); //4. 탭을 탭호스트에 부착하기

        TabSpec tabSpecArtist = tabHost.newTabSpec("ARTIST").setIndicator("가수별"); //2. 탭스펙 생성
        tabSpecArtist.setContent(R.id.tabArtist); //3. 탭스펙을 탭에 붙이기
        tabHost.addTab(tabSpecArtist);

        TabSpec tabSpecAlbum = tabHost.newTabSpec("ALBUM").setIndicator("앨범별"); //2. 탭스펙 생성
        tabSpecAlbum.setContent(R.id.tabAlbum); //탭스펙을 탭에 붙이기
        tabHost.addTab(tabSpecAlbum);

        tabHost.setCurrentTab(0);

    }
}