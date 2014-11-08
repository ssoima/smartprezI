package com.hackathon.presenta;

import android.app.Activity;
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



    private Runnable countdown = new Runnable() {
        @Override
        public void run() {
            if(c.getSeconds() > 0) {
                c.timeElapse();
                mCountdownView.setText(c.toString());
                mHandler.postDelayed(countdown, 1000);
                if (c.getSeconds()!=0)
                    background.setMinimumWidth(frameWidth - ct*c.getSeconds());
            }
            else
                if (keywords.size()>actualkeyword+1) {
                    actualkeyword++;
                    c = keywords.get(actualkeyword);
                    keywordView.setText(c.getKeyword());

                    c.timeElapse();
                    mCountdownView.setText(c.toString());
                    mHandler.postDelayed(countdown, 1000);
                    background.setMinimumWidth(frameWidth - ct*c.getSeconds());
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
                FrameLayout fu = (FrameLayout) stub.findViewById(R.id.fullFrame);
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
