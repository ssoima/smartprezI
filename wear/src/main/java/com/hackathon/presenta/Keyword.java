package com.hackathon.presenta;

/**
 * Created by Testuser on 09.11.2014.
 */
public class Keyword {

    private int seconds;
    private String keyw;

    public Keyword(int sec, String keyw) {
        this.seconds = sec;
        this.keyw = keyw;
    }

    public int getSeconds() {
        return seconds;
    }

    public String getKeyword() {
        return keyw;
    }

    public void timeElapse() {
        seconds--;
    }

    @Override
    public String toString() {
        return Integer.toString((int) Math.floor(seconds/60)) + ":" + ("00" + Integer.toString(seconds%60)).substring(Integer.toString(seconds%60).length());
    }


}
