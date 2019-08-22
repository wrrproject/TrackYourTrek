package com.example.trackyourtrek.System.Collections;

import com.example.trackyourtrek.System.Collections.Items.Walker;

import java.util.ArrayList;

public class Group {
    final private ArrayList<Walker> groupList = new ArrayList<Walker>();
    protected void add(Walker walker){
        groupList.add(walker);
    }
    protected void remove(Walker walker){
        groupList.remove(walker);
    }
}
