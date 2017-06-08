package com.sdaacademy.zientara.rafal.playerpro;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindDrawable(android.R.drawable.ic_media_play)
    Drawable playImage;

    @BindDrawable(android.R.drawable.ic_media_pause)
    Drawable pauseImage;

    @BindView(R.id.volumeSeekBar)
    SeekBar volumeSeekBar;

    @BindView(R.id.play_pauseButton)
    ImageView playPauseButton;

    private MediaPlayer mediaPlayerDubstep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mediaPlayerDubstep = MediaPlayer.create(this, R.raw.bensound_dubstep);
        initVolumeSeekBar();
        prepareVolumeSeekBarListener();
    }

    private void initVolumeSeekBar() {
        mediaPlayerDubstep.setVolume(1, 1);
        volumeSeekBar.setProgress(100);
    }

    private void prepareVolumeSeekBarListener() {
        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("SeekBar", "progress : " + progress);
                float volume = (float) progress / 100;
                Log.d("SeekBar", "volume : " + volume);
                mediaPlayerDubstep.setVolume(volume, volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayerDubstep.isPlaying())
            mediaPlayerDubstep.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!mediaPlayerDubstep.isPlaying())
            mediaPlayerDubstep.start();

        refreshPlayPauseIcon();
    }

    @OnClick(R.id.play_pauseButton)
    public void playSound() {
        playOrPauseDubstep();
        refreshPlayPauseIcon();
    }

    @OnClick(R.id.rewindButton)
    public void rewindDubstep() {
        mediaPlayerDubstep.seekTo(0);
    }

    @OnClick(R.id.stopButton)
    public void stopDubstep() {
        mediaPlayerDubstep.pause();
        mediaPlayerDubstep.seekTo(0);
        refreshPlayPauseIcon();
    }

    private void playOrPauseDubstep() {
        if (mediaPlayerDubstep.isPlaying()) {
            mediaPlayerDubstep.pause();
        } else {
            mediaPlayerDubstep.start();
        }
    }

    private void refreshPlayPauseIcon() {
        if (mediaPlayerDubstep.isPlaying())
            playPauseButton.setImageDrawable(pauseImage);
        else
            playPauseButton.setImageDrawable(playImage);
    }
}
