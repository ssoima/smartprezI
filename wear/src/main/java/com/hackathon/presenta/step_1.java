package com.hackathon.presenta;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.view.WatchViewStub;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class step_1 extends Activity {

    private TextView keywordView;
    private TextView mTextView;
    private TextView mCountdownView;

    private Handler mHandler = new Handler();
    private Keyword c;
    private FrameLayout background;
    private FrameLayout fullFrame;
    private int frameWidth;
    private ArrayList<Keyword> keywords;
    private int actualkeyword;
    private float ct;
    private float timeOver;



    private Runnable countdown = new Runnable() {
        @Override
        public void run() {
            if(c.getSeconds() > 0) {
                c.timeElapse();
                timeOver = (ct * c.getSeconds() / (float) frameWidth) * 100;
                background.setMinimumWidth((int) (frameWidth - ct*c.getSeconds()));
                mCountdownView.setText(c.toString());
                if(timeOver > 75 ) {
                    background.setBackgroundColor(Color.rgb(83, 106, 11)); //gruen
                } else if(timeOver > 50) {
                    background.setBackgroundColor(Color.rgb(142, 140, 10)); //gelb
                } else if(timeOver > 25) {
                    background.setBackgroundColor(Color.rgb(147, 109, 38)); //orange1
                } else {
                    background.setBackgroundColor(Color.rgb(128, 38, 44)); //rot
                }
                mHandler.postDelayed(countdown, 1000);
            }
            else
                if (keywords.size()>actualkeyword+1) {
                    actualkeyword++;
                    c = keywords.get(actualkeyword);
                    keywordView.setText(c.getKeyword());
                    ct = frameWidth / c.getSeconds();
                    mCountdownView.setText(c.toString());
                    mHandler.postDelayed(countdown, 1000);
                    background.setMinimumWidth((int) (frameWidth - ct*c.getSeconds()));
                } else {

                    Intent k = new Intent(step_1.this, FinalScreen.class);
                    startActivity(k);
                }


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_1);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
                keywordView = (TextView) stub.findViewById(R.id.keywordView);
                background = (FrameLayout) stub.findViewById(R.id.background);
                mCountdownView = (TextView) stub.findViewById(R.id.countdownView);
                fullFrame = (FrameLayout) stub.findViewById(R.id.fullFrame);
                frameWidth = 280;  //fu.getWidth();
                keywords = new ArrayList<Keyword>() ;


                // Add the keywords
                actualkeyword = 0;
                keywords.add(new Keyword(10,"BlaBla"));
                keywords.add(new Keyword(5,"mnana"));


                c = keywords.get(actualkeyword);
                keywordView.setText(c.getKeyword());
                ct = (float) (280.0 / c.getSeconds());
                mCountdownView.setText(c.toString());
                mHandler.postDelayed(countdown, 1000);
            }
        });
    }
}
