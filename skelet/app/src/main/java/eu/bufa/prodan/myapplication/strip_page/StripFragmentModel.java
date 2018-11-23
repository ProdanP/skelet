package eu.bufa.prodan.myapplication.strip_page;


import android.support.v4.app.Fragment;

public class StripFragmentModel {
    private Fragment fragment;
    private int resImg;
    private String text;

    public int getResImg() {
        return resImg;
    }

    public void setResImg(int resImg) {
        this.resImg = resImg;
    }

    public Fragment getFragment() {

        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
