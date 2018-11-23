package eu.bufa.prodan.myapplication.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.babaliste.baseutility.R;


public class Button extends RelativeLayout {

    private boolean isFullscreen;
    private boolean downWasAnimated = false;
    private boolean wasCanceled = false;

    public Button(Context context) {
        super(context);
    }
    public Button(Context context, AttributeSet attrs){
        super(context,attrs);
        init(context,attrs);
    }
    public Button(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        init(context,attrs);
    }
    void init(Context context, AttributeSet attrs) {
        setOnTouchListener(new OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL ){
                    wasCanceled = true;
                    if(downWasAnimated) {
                        animateButtonUp();
                        downWasAnimated = false;
                        System.out.println("animateButtonUp");
                    }
                } else if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(!wasCanceled) {
                        animateButtonDown();
                    }
                }
                return false;
            }
        });

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click this");
            }
        });

        applyAttributes(context, attrs);
    }

    public void animateButtonDown() {
        downWasAnimated = true;
        final Animation myAnimBounce = AnimationUtils.loadAnimation(getContext(), R.anim.bounce_down);
      //  final Animation myAnimAlpha = AnimationUtils.loadAnimation(getContext(), R.anim.alpha_down);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.07f, 10f);
        myAnimBounce.setInterpolator(interpolator);
       // myAnimAlpha.setInterpolator(interpolator);
        myAnimBounce.setFillAfter(true);
      //  myAnimAlpha.setFillAfter(true);

        if(isFullscreen) {
            getChildAt(0).startAnimation(myAnimBounce);
        } else {
            startAnimation(myAnimBounce);
        }
    }
    void animateButtonUp() {
        wasCanceled = false;
        final Animation myAnimBounce = AnimationUtils.loadAnimation(getContext(), R.anim.bounce_up);
     //   final Animation myAnimAlpha = AnimationUtils.loadAnimation(getContext(), R.anim.alpha_up);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.07f, 10f);
        myAnimBounce.setInterpolator(interpolator);
     //   myAnimAlpha.setInterpolator(interpolator);
        myAnimBounce.setFillAfter(true);
      //  myAnimAlpha.setFillAfter(true);
        if(isFullscreen) {
            getChildAt(0).startAnimation(myAnimBounce);
        } else {
            startAnimation(myAnimBounce);
        }
    }

    private void applyAttributes(Context context, AttributeSet attrs) {
        // Extract attrs
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Button);
        final int N = a.getIndexCount();
        for (int i = 0; i < N; i++) {
            final int attr = a.getIndex(i);
            if (attr == R.styleable.Button_is_fullscreen) {
                isFullscreen = a.getBoolean(attr, false);
            }
        }
        a.recycle();
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setVisibility(visibility);
        }
    }

    public void enable() {
        setEnabled(true);
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setEnabled(true);
        }
    }
    public void disable() {
        setEnabled(false);
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setEnabled(false);
        }
    }
}