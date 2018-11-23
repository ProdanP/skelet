package eu.bufa.prodan.myapplication.recyclerview_utils.listeners;

public interface LazyLoadListener{
    void onLoadMore();
    void onRefresh();
    void onScroll(int dx, int dy);
    void onScrollStateChanged(int newState);
}