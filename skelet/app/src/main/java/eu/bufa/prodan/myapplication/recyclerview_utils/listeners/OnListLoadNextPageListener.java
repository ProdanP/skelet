package eu.bufa.prodan.myapplication.recyclerview_utils.listeners;

import android.view.View;


public interface OnListLoadNextPageListener {

     void onLoadNextPage(View view);
     void onScroll(int dx, int dy);
     void onScrollStateChanged(int newState);
}
