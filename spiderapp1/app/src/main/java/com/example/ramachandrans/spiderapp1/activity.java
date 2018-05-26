package com.example.ramachandrans.spiderapp1;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class activity extends Activity {

    EditText t1;
    RelativeLayout l;
    Handler h;
    Runnable r;
    MediaPlayer mp;
    int s = -14,ms = 999,m = 0;
    int flagrun = 0,flag = 0,screen = 0;

    public  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        t1 = findViewById(R.id.t1);
        l = findViewById(R.id.l);
        h = new Handler();
        mp = MediaPlayer.create(this, R.raw.beep07);
    }

    public void timer(View v){
        r = new Runnable() {
            @Override
            public void run() {
                try{
                    h.postDelayed(r,1);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                if(screen<3) {
                    ms--;
                }
                if(screen==2&&flag==0&&s<=0){
                    s = 0;
                    flag = 1;
                    ms = 999;
                }
                if (ms == 0) {
                    s++;
                    if(flag==0&&s==1){
                        s = 0;
                        mp.stop();
                        screen = 2;
                        flag = 1;
                    }
                    ms = 999;
                }
                if(s>-3&&flag==0){
                    mp.start();
                }
                if(s==60){
                    m++;
                    s = 0;
                }
                if((s<=0)&&(flag==0)){
                    t1.setText(Math.abs(s) + ":" + ms);
                }
                else{
                    t1.setText(m+":"+s+":"+(999-ms));
                }
            }
        };
        if(flagrun==0) {
            r.run();
            flagrun = 1;
        }

        if(screen==0) {
            screen = 1;
        }
        else if(screen==1){
            screen = 2;
        }
        else if(screen==2){
            screen =3;
        }
    }
}
