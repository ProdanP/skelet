package eu.bufa.prodan.myapplication.utils.animations;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import eu.bufa.prodan.myapplication.R;
import eu.bufa.prodan.myapplication.utils.MyBounceInterpolator;


public class AnimatedTouchEvent implements View.OnTouchListener {
    private boolean downWasAnimated = false;
    private boolean wasCanceled = false;
    private Context mContext;

    public AnimatedTouchEvent(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
            wasCanceled = true;
            if (downWasAnimated) {
                animateViewUp(v);
                downWasAnimated = false;
            }
        } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (!wasCanceled) {
                animateViewDown(v);
            }
        }
        return false;
    }

    private void animateViewDown(View view) {
        downWasAnimated = true;
        final Animation myAnimBounce = AnimationUtils.loadAnimation(mContext, R.anim.bounce_down);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.07f, 10f);
        myAnimBounce.setInterpolator(interpolator);
        myAnimBounce.setFillAfter(true);
        view.startAnimation(myAnimBounce);
    }

    private void animateViewUp(View view) {
        wasCanceled = false;
        final Animation myAnimBounce = AnimationUtils.loadAnimation(mContext, R.anim.bounce_up);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.07f, 10f);
        myAnimBounce.setInterpolator(interpolator);
        myAnimBounce.setFillAfter(true);
        view.startAnimation(myAnimBounce);
    }
}
