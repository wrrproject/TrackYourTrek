package com.example.trackyourtrek.Utility;

import android.view.View;

@FunctionalInterface
public interface RecyclerViewItemTouch<T> {
    void onItemTouch(T modelObject, View v);
}
