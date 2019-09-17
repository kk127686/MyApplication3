package com.juxiang.myapplication1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.juxiang.myapplication1.wiget.CircleView;


public class MainActivity extends AppCompatActivity {

    private CircleView circleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circleView = findViewById(R.id.circle);
        new Thread(){
            @Override
            public void run() {
                super.run();
                float deg=0;
                while(deg<360){
                    circleView.setDeg(deg);
                    deg+=5;
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }
}
