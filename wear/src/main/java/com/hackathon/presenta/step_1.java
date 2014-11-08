package com.hackathon.presenta;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.wearable.view.WatchViewStub;
import android.widget.Chronometer;
import android.widget.TextView;

public class step_1 extends Activity {

    private TextView mTextView;
    private TextView mCountdownView;

    private Handler mHandler = new Handler();
    private int c = 10;

    private Runnable countdown = new Runnable() {
        @Override
        public void run() {
            if(c > 0) {
                c--;
                mCountdownView.setText(c + "");
                mHandler.postDelayed(countdown, 1000);
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

                mCountdownView = (TextView) stub.findViewById(R.id.countDownView);
                mCountdownView.setText(c+"");


                mHandler.postDelayed(countdown, 1000);
            }
        });
    }
}
