package com.sdaacademy.zientara.rafal.drums;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener {

    HashMap<Integer, MediaPlayer> musicsHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        musicsHashMap = new HashMap<>();
    }

    private void putMusicPlayer(int viewId) {
        musicsHashMap.put(viewId, MediaPlayer.create(this, getMusicIdByViewId(viewId)));
    }

    public void playSound(View view) {
        MediaPlayer sound = musicsHashMap.get(view.getId());
        if (sound != null) {
            seekToBeggingAndPlay(sound);
        } else {
            putMusicPlayer(view.getId());
            seekToBeggingAndPlay(musicsHashMap.get(view.getId()));
        }

        //old
        /*int musicId = getMusicIdByViewId(view.getId());
        MediaPlayer sound = MediaPlayer.create(this, musicId);
        sound.setOnCompletionListener(this);
        sound.start();*/
    }

    private void seekToBeggingAndPlay(MediaPlayer sound) {
        sound.seekTo(0);
        if (!sound.isPlaying())
            sound.start();
    }

    private int getMusicIdByViewId(int viewId) {
        switch (viewId) {
            case R.id.imageView:
                return R.raw.cymbal;
            case R.id.imageView2:
                return R.raw.bass;
            case R.id.imageView3:
                return R.raw.drum1;
            case R.id.imageView4:
                return R.raw.paleczki;
            case R.id.imageView5:
                return R.raw.drum1;
            case R.id.imageView6:
                return R.raw.gong;
            default:
                return R.raw.gong;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mp.release();
    }
}
