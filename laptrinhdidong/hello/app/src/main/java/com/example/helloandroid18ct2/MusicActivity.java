package com.example.helloandroid18ct2;

import androidx.appcompat.app.AppCompatActivity;

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

public class MusicActivity extends AppCompatActivity {
    TextView txtMusicTitle, txtTimeStart, txtTimeEnd, txtContent;
    SeekBar skMusic;
    ImageView img;
    ImageButton btnPrev, btnPlay, btnStop, btnNext ;

    ArrayList<Song> arraySong;
    int index = 0;
    MediaPlayer mediaPlayer;
    Animation animation ;



    private void doControl() {
        txtMusicTitle = (TextView) findViewById(R.id.txt_Music_title);
        txtTimeStart = (TextView) findViewById(R.id.txt_timestart);
        txtTimeEnd = (TextView) findViewById(R.id.txt_timeend);
        txtContent = (TextView) findViewById(R.id.txt_content);
        skMusic = (SeekBar) findViewById(R.id.seekbar);
        btnPrev = (ImageButton) findViewById(R.id.btn_Prev);
        btnPlay = (ImageButton) findViewById(R.id.btn_Play);
        btnStop = (ImageButton) findViewById(R.id.btn_Pause);
        btnNext = (ImageButton) findViewById(R.id.btn_Next);
        img = (ImageView) findViewById(R.id.img_View);
    }

    private void addSong() {
        arraySong = new ArrayList<>();
        arraySong.add(new Song("Bước Qua Nhau", R.raw.buoc_qua_nhau));
        arraySong.add(new Song("Bố Già", R.raw.bo_gia));
        arraySong.add(new Song("Mình Chẳng Còn Mai Sau", R.raw.minh_chang_con_mai_sau));
    }

    private void doMediaplayer() {
        mediaPlayer = MediaPlayer.create(MusicActivity.this , arraySong.get(index).getFile());
        txtMusicTitle.setText(arraySong.get(index).getTitle());
    }

    private void setTimeend() {
        SimpleDateFormat edittime = new SimpleDateFormat("mm:ss");
        txtTimeEnd.setText(edittime.format(mediaPlayer.getDuration()));
        skMusic.setMax(mediaPlayer.getDuration());
    }

    private void setTimestart() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat editTime = new SimpleDateFormat("mm:ss");
                txtTimeStart.setText(editTime.format(mediaPlayer.getCurrentPosition()));
                skMusic.setProgress(mediaPlayer.getCurrentPosition());
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        index++;
                        if(index > arraySong.size() - 1){
                            index = 0;
                        }
                        if(mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                        }
                        doMediaplayer();
                        mediaPlayer.start();
                        btnPlay.setImageResource(R.drawable.pause);
                        setTimeend();
                        setTimestart();
                    }
                });
                handler.postDelayed(this, 500);
            }
        },100);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        doControl();
        addSong();
        animation = AnimationUtils.loadAnimation(this,R.anim.rote);
        doMediaplayer();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                if(index > arraySong.size() - 1){
                    index = 0;
                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                doMediaplayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause);
                setTimeend();
                setTimestart();
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index--;
                if(index < 0 ){
                    index = arraySong.size() - 1;
                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                doMediaplayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause);
                setTimeend();
                setTimestart();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                btnPlay.setImageResource(R.drawable.play);
                doMediaplayer();
                img.clearAnimation();
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    txtContent.setText(R.string.music_pause);
                    btnPlay.setImageResource(R.drawable.play);
                    img.clearAnimation();
                }else{
                    mediaPlayer.start();
                    txtContent.setText(R.string.music_play);
                    btnPlay.setImageResource(R.drawable.pause);
                    img.startAnimation(animation);
                }
                setTimeend();
                setTimestart();

            }
        });

        skMusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(skMusic.getProgress());
            }
        });

    }

}