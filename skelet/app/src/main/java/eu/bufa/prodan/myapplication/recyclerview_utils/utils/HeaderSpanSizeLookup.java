package eu.bufa.prodan.myapplication.recyclerview_utils.utils;

import android.support.v7.widget.GridLayoutManager;

import eu.bufa.prodan.myapplication.recyclerview_utils.adapters.HeaderAndFooterRecyclerViewAdapter;

public class HeaderSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private HeaderAndFooterRecyclerViewAdapter adapter;
    private int mSpanSize = 1;

    public HeaderSpanSizeLookup(HeaderAndFooterRecyclerViewAdapter adapter, int spanSize) {
        this.adapter = adapter;
        this.mSpanSize = spanSize;
    }

    @Override
    public int getSpanSize(int position) {
        boolean isHeaderOrFooter = adapter.isHeader(position) || adapter.isFooter(position);
        return isHeaderOrFooter ? mSpanSize : 1;
    }
}