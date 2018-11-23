package eu.bufa.prodan.myapplication;

import android.support.annotation.LayoutRes;

/**
 * Created by Vasile-barbaros-Pc on 9/15/2016.
 */
public interface BaseInterface {
    int getLayoutResourceId();
    @LayoutRes
    int getLayoutResourceIdLoading();
    int getRootLoadingViewResId();

}
