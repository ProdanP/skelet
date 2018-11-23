package eu.bufa.prodan.myapplication.recyclerview_utils.utils;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import eu.bufa.prodan.myapplication.recyclerview_utils.CustomLinearLayoutManager;
import eu.bufa.prodan.myapplication.recyclerview_utils.adapters.HeaderAndFooterRecyclerViewAdapter;
import eu.bufa.prodan.myapplication.recyclerview_utils.views.LoadingFooter;


public class RecyclerViewUtils {

    public static int getLayoutPosition(RecyclerView recyclerView, RecyclerView.ViewHolder holder) {
        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();
        if (outerAdapter != null && outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter) {

            int headerViewCounter = ((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getHeaderViewsCount();
            if (headerViewCounter > 0) {
                return holder.getLayoutPosition() - headerViewCounter;
            }
        }

        return holder.getLayoutPosition();
    }

    public static RecyclerView.ItemAnimator getAnimator(boolean hasChangeDuration){
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(500);
        itemAnimator.setRemoveDuration(500);
        itemAnimator.setChangeDuration(0);
        if(hasChangeDuration) {
            itemAnimator.setChangeDuration(500);
        }
        return itemAnimator;
    }

    public static CustomLinearLayoutManager getCustomLinear(Context context, float speed) {
        return new CustomLinearLayoutManager(context, speed);
    }

    public static LinearLayoutManager getLayoutManager(Context context, boolean horizontal){
        return getLayoutManager(context, horizontal, true);
    }

    public static LinearLayoutManager getLayoutManager(Context context, boolean horizontal, final boolean canScroll){
        int orientation = LinearLayoutManager.VERTICAL;
        if(horizontal){
            orientation = LinearLayoutManager.HORIZONTAL;
        }
        return new LinearLayoutManager(context, orientation,false){
            @Override
            public boolean canScrollVertically() {
                return canScroll;
            }
        };
    }

    public static GridLayoutManager getGridLayoutManager(Context context, int columns){
        return getGridLayoutManager(context, columns, true);

    }
    public static GridLayoutManager getGridLayoutManager(Context context, int columns, final boolean canScroll) {
        return new GridLayoutManager(context,columns){
            @Override
            public boolean canScrollVertically() {
                return canScroll;
            }
        };

    }

    public static CustomGridLayoutManager getCustomGridLayoutManager(Context context, int columns){
        return new CustomGridLayoutManager(context,columns);

    }

    public static class CustomGridLayoutManager extends GridLayoutManager {
        private boolean isScrollEnabled = true;

        public CustomGridLayoutManager(Context context, int columns) {
            super(context,columns);
        }

        public void setScrollEnabled(boolean flag) {
            this.isScrollEnabled = flag;
        }

        @Override
        public boolean canScrollVertically() {
            return isScrollEnabled && super.canScrollVertically();
        }
    }

    public static ExStaggeredGridLayoutManager getStaggeredGridLayoutManager(int columns){
        return new ExStaggeredGridLayoutManager(columns,StaggeredGridLayoutManager.VERTICAL);
    }

    public static void setFooterViewState(Context instance, RecyclerView recyclerView, LoadingFooter.State state, @LayoutRes int resourceLayout, @IdRes int ResourceIdRoot, @IdRes int ResourceIdProgress) {
        System.out.println("state is "+state);

        if(instance == null) {
            return;
        }

        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();

        if (outerAdapter == null || !(outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter)) {
            return;
        }

        HeaderAndFooterRecyclerViewAdapter headerAndFooterAdapter = (HeaderAndFooterRecyclerViewAdapter) outerAdapter;


        LoadingFooter footerView;

        if (headerAndFooterAdapter.getFooterViewsCount() > 0) {
            footerView = (LoadingFooter) headerAndFooterAdapter.getFooterView();
            footerView.setState(state);
        } else {
            footerView = new LoadingFooter(instance,resourceLayout,ResourceIdRoot,ResourceIdProgress);
            footerView.setState(state);
            headerAndFooterAdapter.addFooterView(footerView);
        }
    }

    public static LoadingFooter.State getFooterViewState(RecyclerView recyclerView) {

        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();
        if (outerAdapter != null && outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter) {
            if (((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getFooterViewsCount() > 0) {
                LoadingFooter footerView = (LoadingFooter) ((HeaderAndFooterRecyclerViewAdapter) outerAdapter).getFooterView();
                return footerView.getState();
            }
        }

        return LoadingFooter.State.Normal;
    }
}