package com.hackathon.presenta;

import java.util.ArrayList;

/**
 * Created by root on 09.11.14.
 */
public class Page {
    private ArrayList<Keyword> page;

    public Page(ArrayList<Keyword> p)
    {
        page = p;
    }

    public void addKeyword(Keyword k)
    {
        page.add(k);
    }

    public ArrayList<Keyword> getPage()
    {
        return page;
    }


}
