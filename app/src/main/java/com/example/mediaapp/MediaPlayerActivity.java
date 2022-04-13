package com.example.mediaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MediaPlayerActivity extends AppCompatActivity {
    TextView txtTitle, txtTimeSongLeft, txtTimeSongRight;
    SeekBar skBarSong;
    ImageButton btnPrev,btnPlay, btnLoop, btnNext;
    ArrayList<Song> listSongs;
    MediaPlayer mediaPlayer;
    Animation animation;
    ImageView imageViewDVD;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        Mapping();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        position = bundle.getInt("position",0);
        listSongs = (ArrayList<Song>) bundle.getSerializable("arrayListSong");
        CreateMediaSong();
        animation = AnimationUtils.loadAnimation(this,R.anim.dvd_rorate);
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imageViewDVD.clearAnimation();
                    btnPlay.setImageResource(R.drawable.play);
                }else{
                    mediaPlayer.start();
                    imageViewDVD.startAnimation(animation);
                    btnPlay.setImageResource(R.drawable.pause60);
                }

                SetTimeRight();
                SetTimeLeft();
            }
        });

        btnLoop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isLooping()){
                    mediaPlayer.setLooping(false);
                    btnLoop.setImageResource(R.drawable.reload);
                }else{
                    mediaPlayer.setLooping(true);
                    btnLoop.setImageResource(R.drawable.refresh);
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position > listSongs.size()-1){
                    position = 0;
                }else{
                    position++;
                }

                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }else{
                    btnPlay.setImageResource(R.drawable.pause60);
                }
                CreateMediaSong();
                mediaPlayer.start();
                imageViewDVD.startAnimation(animation);
                SetTimeRight();
                SetTimeLeft();
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position < 0){
                    position = listSongs.size()-1;
                }else{
                    position--;
                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }else{
                    btnPlay.setImageResource(R.drawable.pause60);
                }
                CreateMediaSong();
                mediaPlayer.start();
                imageViewDVD.startAnimation(animation);
                SetTimeRight();
                SetTimeLeft();
            }
        });


        skBarSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
    }

    private void CreateMediaSong(){
        mediaPlayer = new MediaPlayer().create(MediaPlayerActivity.this,listSongs.get(position).getFileMp3());
        txtTitle.setText(listSongs.get(position).getName() + " - " + listSongs.get(position).getSinger());
    }



    private void SetTimeRight(){
        SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");
        txtTimeSongRight.setText(timeFormat.format(mediaPlayer.getDuration()));
        skBarSong.setMax(mediaPlayer.getDuration());
    }

    private void SetTimeLeft(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");
                txtTimeSongLeft.setText(timeFormat.format(mediaPlayer.getCurrentPosition()));
                skBarSong.setProgress(mediaPlayer.getCurrentPosition());

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        if(position > listSongs.size()-1){
                            position = 0;
                        }else{
                            position++;
                        }

                        if(mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                        }else{
                            btnPlay.setImageResource(R.drawable.pause60);
                        }
                        CreateMediaSong();
                        mediaPlayer.start();
                        SetTimeRight();
                        SetTimeLeft();
                    }
                });
                handler.postDelayed(this,500);
            }
        },100);

    }

    private void Mapping(){
        txtTimeSongLeft = (TextView) findViewById(R.id.TextViewTimeLeft);
        txtTimeSongRight = (TextView) findViewById(R.id.TextViewTimeRight);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        skBarSong = (SeekBar) findViewById(R.id.seekbarTime);
        btnNext = (ImageButton) findViewById(R.id.nextbtn);
        btnPrev = (ImageButton) findViewById(R.id.prevbtn);
        btnPlay = (ImageButton) findViewById(R.id.playbtn);
        btnLoop = (ImageButton) findViewById(R.id.btnloop);
        imageViewDVD = (ImageView) findViewById(R.id.imageViewDvd);
    }
}