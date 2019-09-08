package com.example.trackyourtrek.Utility;

@FunctionalInterface
public interface RecyclerViewItemTouch<T> {
    void onItemTouch(T modelObject);
}
