package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;
import android.os.Bundle;
import android.view.View;
import android.os.CountDownTimer;
import android.media.MediaPlayer;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
    TextView timerTextView;
    Button controllerButton;
    Boolean counterIsActive=false;
    CountDownTimer countDownTimer;
    public void resetTimer(){
        timerTextView.setText("0:30");
        timerSeekBar.setProgress(30);
        countDownTimer.cancel();
        controllerButton.setText("Go!");
        timerSeekBar.setEnabled(true);
        counterIsActive=false;

    }
    public void updateTimer(int secondsLeft){
        int minutes = (int) secondsLeft / 60; //no. of minutes in integer
        int seconds = secondsLeft - minutes*60; //no. of seconds left
        String secondString = Integer.toString(seconds);
         if(seconds<=9){
            secondString="0"+secondString;
        }
        timerTextView.setText(Integer.toString(minutes) + ":" + secondString);
    }
    public void controlTimer(View view){
        if(counterIsActive == false) {
            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            controllerButton.setText("Stop");

            //Log.i("Button pressed:","Pressed");
            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {
                    resetTimer();
                    //timerTextView.setText("0:00");
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.music);
                    mplayer.start();
                }
            }.start();
        } else{
            resetTimer();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerSeekBar = findViewById(R.id.timerSeekBar);
         timerTextView = (TextView) findViewById(R.id.timerTextView);
         controllerButton = (Button) findViewById(R.id.controllerButton);
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
             /*   int minutes = (int) progress / 60; //no. of minutes in integer
                int seconds = progress - minutes*60; //no. of seconds left
                String secondString = Integer.toString(seconds);
                if(secondString == "0"){
                    secondString ="00";
                }
                timerTextView.setText(Integer.toString(minutes) + ":" + secondString);*/
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}