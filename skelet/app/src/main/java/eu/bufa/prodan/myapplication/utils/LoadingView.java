package eu.bufa.prodan.myapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class LoadingView {

    public View getProgressBar(Activity context, View view, @LayoutRes int resLayout)
    {
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(resLayout != 0 && vi != null) {
            View v = vi.inflate(resLayout, (ViewGroup) view,false);
            v.setVisibility(View.INVISIBLE);
            if (view != null) {
                ViewGroup layout = (ViewGroup) view;
                layout.addView(v);
            } else {
                ViewGroup layout = (ViewGroup) context.getWindow().getDecorView();
                layout.addView(v);
            }
            return v;
        } else {
            return null;
        }
    }
}
