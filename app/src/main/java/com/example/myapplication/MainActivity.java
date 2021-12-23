package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button ClickArea;
    TextView PopCount;
    int RealPopCount;
    Button Fever_btn;
    public boolean fever_state = false;
    public static Context context_main;
    MyTimer myTimer;
    boolean play;
    SoundPool soundPool;
    SoundManager soundManager;
    int playSoundId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ClickArea = findViewById(R.id.ClickImage);
        PopCount = findViewById(R.id.PopCount);
        Fever_btn = findViewById(R.id.fever_btn);
        context_main = this; // Context(Mytimer)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundPool = new SoundPool.Builder().build();
        } else {
            //롤리팝 이하 버전일 경우 //new SoundPool(1번,2번,3번) //1번 - 음악 파일 갯수 //2번 - 스트림 타입 //3번 - 음질
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        }

        soundManager = new SoundManager(this, soundPool);
        soundManager.addSound(0, R.raw.we);


        ClickArea.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        if(fever_state == false)
                        {
                            ClickArea.setBackgroundResource(R.drawable.pop_click);
                            soundManager.playSound(0);

                            break;
                        } else {
                            ClickArea.setBackgroundResource(R.drawable.pop_fever);
                            RealPopCount += 4;
                            break;
                        }

                    case MotionEvent.ACTION_UP:
                        ClickArea.setBackgroundResource(R.drawable.pop_nor);
                        RealPopCount++;
                        PopCount.setText("POP : "+RealPopCount);
                        soundManager.resumeSound(0);



                        break;
                }
                return false;
            }
        });

        Fever_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            fever_state = true;

            myTimer = new MyTimer(5000,1000);
            myTimer.start();
            }
        });


    }
}
