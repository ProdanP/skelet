package eu.bufa.prodan.myapplication.recyclerview_utils.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;


@SuppressLint("ViewConstructor")
public class LoadingFooter extends RelativeLayout {

    protected State mState = State.Normal;
    @Nullable
    View mLoadingView;

    @IdRes
    int ResourceIdRoot;
    @IdRes
    int ResourceIdProgress;
    @LayoutRes
    int resourceLayout;

    public LoadingFooter(Context context, @LayoutRes int resourceLayout, @IdRes int ResourceIdRoot, @IdRes int ResourceIdProgress) {
        super(context);
        this.resourceLayout = resourceLayout;
        this.ResourceIdRoot = ResourceIdRoot;
        this.ResourceIdProgress = ResourceIdProgress;
        init(context);
    }

    public void init(Context context) {

        inflate(context,resourceLayout, this);
        setOnClickListener(null);

        setState(State.Normal, true);
    }

    public State getState() {
        return mState;
    }

    public void setState(State status ) {
        setState(status, true);
    }

    public void setState(State status, boolean showView) {
        if (mState == status) {
            return;
        }
        mState = status;

        switch (status) {

            case Normal:
                setOnClickListener(null);
                if (mLoadingView != null) {
                    mLoadingView.setVisibility(VISIBLE);
                } else {

                    mLoadingView = findViewById(ResourceIdProgress);
                    mLoadingView.setVisibility(VISIBLE);
                }
                findViewById(ResourceIdRoot).setVisibility(VISIBLE);
                break;
            case Loading:
                setOnClickListener(null);
                if (mLoadingView != null) {
                    mLoadingView.setVisibility(VISIBLE);
                } else {
                    mLoadingView = findViewById(ResourceIdProgress);
                    mLoadingView.setVisibility(VISIBLE);
                }
                findViewById(ResourceIdRoot).setVisibility(VISIBLE);
                break;
            case TheEnd:
                setOnClickListener(null);
               findViewById(ResourceIdRoot).setVisibility(GONE);
                break;
            default:

                break;
        }
    }

    public static enum State {
        Normal, TheEnd, Loading
    }
}