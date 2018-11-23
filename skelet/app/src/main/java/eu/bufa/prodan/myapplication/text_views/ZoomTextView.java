package eu.bufa.prodan.myapplication.text_views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.ScaleGestureDetector;


public class ZoomTextView extends TextViewStyled {
    private static final String TAG = "ZoomTextView";
    private ScaleGestureDetector mScaleDetector;

    private float mScaleFactor = 1.f;
    private float defaultSize;

    private float zoomLimit = 3.0f;
    private OnExternalScaleListener mExternalScaleListener;


    public ZoomTextView(Context context) {
        super(context);
        initialize();
    }

    public ZoomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public ZoomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        defaultSize = getTextSize();
        mScaleDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
    }

    /***
     * @param zoomLimit
     * Default value is 3, 3 means text can zoom 3 times the default size
     */

    public void setZoomLimit(float zoomLimit) {
        this.zoomLimit = zoomLimit;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public OnExternalScaleListener getExternalScaleListener() {
        return mExternalScaleListener;
    }

    public void setExternalScaleListener(OnExternalScaleListener mExternalScaleListener) {
        this.mExternalScaleListener = mExternalScaleListener;
    }

    public ScaleGestureDetector getmScaleDetector() {
        return mScaleDetector;
    }

    public void setScaleFactor(float mScaleFactor) {
        this.mScaleFactor = mScaleFactor;
        setTextSize(TypedValue.COMPLEX_UNIT_PX, defaultSize * mScaleFactor);
    }

    /*Scale Gesture listener class,
    mScaleFactor is getting the scaling value
    and mScaleFactor is mapped between 1.0 and and zoomLimit
    that is 3.0 by default. You can also change it. 3.0 means text
    can zoom to 3 times the default value.*/

    private void saveBookZoomScale(float mScaleFactor) {
        //SharedPreference.getInstance().saveSharedPreferenceFloat(getContext(), Constants.PREF_ZOOM_SCALE, mScaleFactor);
    }

    public interface OnExternalScaleListener {
        void onScaleEnd();

        void onScaleStart();
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            if (mExternalScaleListener != null) {
                mExternalScaleListener.onScaleStart();
            }
            return super.onScaleBegin(detector);
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            if (mExternalScaleListener != null)
                mExternalScaleListener.onScaleEnd();
            saveBookZoomScale(mScaleFactor);
            super.onScaleEnd(detector);
        }

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();
            mScaleFactor = Math.max(0.95f, Math.min(mScaleFactor, zoomLimit));
            setTextSize(TypedValue.COMPLEX_UNIT_PX, defaultSize * mScaleFactor);

            //setTextSize(TypedValue.COMPLEX_UNIT_PX, defaultSize * mScaleFactor);
            Log.e(TAG, String.valueOf(mScaleFactor));
            return true;
        }
    }
}