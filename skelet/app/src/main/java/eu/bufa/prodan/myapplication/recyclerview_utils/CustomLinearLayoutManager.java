package eu.bufa.prodan.myapplication.recyclerview_utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

public class CustomLinearLayoutManager extends LinearLayoutManager {
    private float SPEED = 5000f;// Change this value (default=25f)

    public CustomLinearLayoutManager(Context context, float speed) {
        super(context);
        SPEED = speed;
    }

    public CustomLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public CustomLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        LinearSmoothScroller smoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {



            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return SPEED / displayMetrics.densityDpi;
            }

        };
        smoothScroller.setTargetPosition(position);
        startSmoothScroll(smoothScroller);
    }

}