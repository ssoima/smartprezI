package com.hackathon.presenta;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.view.WatchViewStub;
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
    private int ct;
    private float timeOver;



    private Runnable countdown = new Runnable() {
        @Override
        public void run() {
            if(c.getSeconds() > 0) {
                c.timeElapse();
               // mCountdownView.setText(c.toString());
                timeOver = ct * c.getSeconds() / frameWidth * 100;
                background.setMinimumWidth(frameWidth - ct*c.getSeconds());
                mCountdownView.setText(c.toString());
                if(timeOver > 90 ) {
                    background.setBackgroundColor(Color.rgb(243, 116, 90)); //rot
                } else if(timeOver > 80) {
                    background.setBackgroundColor(Color.rgb(254, 142, 52)); //orange2
                } else if(timeOver > 60) {
                    background.setBackgroundColor(Color.rgb(254, 170, 103)); //orange1
                } else if(timeOver > 40) {
                    background.setBackgroundColor(Color.rgb(254, 245, 103)); //gelb
                } else {
                    background.setBackgroundColor(Color.rgb(93, 204, 78)); //gruen
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
                    background.setMinimumWidth(frameWidth - ct*c.getSeconds());
                } else {

                    //SWITCH TO FINISH SLIDE
                }


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_1);
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
                ct=280/c.getSeconds();
                mHandler.postDelayed(countdown, 1000);
            }
        });
    }
}
