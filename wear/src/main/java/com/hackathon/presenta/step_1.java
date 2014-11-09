package com.hackathon.presenta;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.view.WatchViewStub;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static com.hackathon.presenta.R.id.readybutton;

public class step_1 extends Activity{

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
    private int actualpage;
    private ArrayList<Page> pages;
    private Button nextpage;
    private boolean changePage;



    private Runnable countdown = new Runnable() {
        @Override
        public void run() {
            if (changePage) {
                actualpage++;
                actualkeyword=0;
                keywords = pages.get(actualpage).getPage();
                c = keywords.get(actualkeyword);
                keywordView.setText(c.getKeyword());
                ct=280/c.getSeconds();
                changePage=false;
            }

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
                nextpage = (Button) stub.findViewById(readybutton);
                nextpage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changePage= true;
                    }
                });

                frameWidth = 280;  //fu.getWidth();

                changePage = false;
                keywords = new ArrayList<Keyword>() ;

                pages = new ArrayList<Page>();

                // Add pages
                actualkeyword   = 0;
                actualpage      = 0;

                //page1
                keywords.add(new Keyword(2, "BlaBla"));
                keywords.add(new Keyword(5,"mnana"));
                pages.add(new Page(keywords));

                //page2
                keywords = new ArrayList<Keyword>() ;
                keywords.add(new Keyword(10, "1.1"));
                keywords.add(new Keyword(2, "1.2"));
                keywords.add(new Keyword(10, "1.3"));
                keywords.add(new Keyword(10, "1.4"));
                pages.add(new Page(keywords));
                //page2
                keywords = new ArrayList<Keyword>() ;
                keywords.add(new Keyword(10, "2.1"));
                keywords.add(new Keyword(2, "2.2"));
                keywords.add(new Keyword(10, "2.3"));
                keywords.add(new Keyword(10, "2.4"));
                pages.add(new Page(keywords));

                keywords = pages.get(actualpage).getPage();
                c = keywords.get(actualkeyword);
                keywordView.setText(c.getKeyword());
                ct=280/c.getSeconds();
                mHandler.postDelayed(countdown, 1000);
            }
        });
    }
}
