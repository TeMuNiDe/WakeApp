package com.vitech.wakeapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import com.vitech.wakeapp.R;
import com.vitech.wakeapp.data.Puzzler;
import com.vitech.wakeapp.rearend.PuzzleAdapter;
import com.vitech.wakeapp.rearend.ScheduleManager;



public class FiredAlarm extends Activity {
GridView puzzleView;
    TextView puzzleTitle;
    TextView puzzleInfo;
    PuzzleAdapter puzzleAdapter;
    Puzzler p;
    int count;
    int pid;
    ScheduleManager scheduleManager;
    AudioManager audioManager;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fired_alarm);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

puzzleView = (GridView)findViewById(R.id.puzzleGrid);

        count = 0;
        scheduleManager = new ScheduleManager();
scheduleManager.setRepeat(FiredAlarm.this);
        audioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), AudioManager.FLAG_VIBRATE);
        mediaPlayer = MediaPlayer.create(FiredAlarm.this,R.raw.asphalt);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.release();
            }
        });
        p = new Puzzler();
        puzzleTitle = (TextView)findViewById(R.id.puzzleTitle);
        puzzleInfo = (TextView)findViewById(R.id.textPuzzleInfo);
        pid = (int)(Math.random()*(2));
        System.out.println(Integer.toString(pid));
        puz();
    }

    public void puz(){
        runOnUiThread(new Runnable() {
            @Override
            public void run(){
                if(pid==0){
                    puzzleTitle.setText("Number Puzzle");
                    puzzleInfo.setText("Click above numbers in Ascending Order ie 1-9");
                }
                else {
                    puzzleTitle.setText("Character Puzzle");
                    puzzleInfo.setText("Click above Alphabets in  Order  a-i");
                }
                puzzleAdapter = new PuzzleAdapter(p.shuffle(pid),FiredAlarm.this);
                puzzleView.setAdapter(puzzleAdapter);
                puzzleView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (count == 8) {
scheduleManager.cancelRepeat(FiredAlarm.this);
mediaPlayer.stop();
                            mediaPlayer.release();
                            Intent i = new Intent(FiredAlarm.this,WakeAppActivity.class);
                            FiredAlarm.this.startActivity(i);
                        }
                        if (p.checkord(position)) {
                            count++;
                        } else {
                            count = 0;
                            puz();

                        }

                    }
                });
            }
        });


    }



}

