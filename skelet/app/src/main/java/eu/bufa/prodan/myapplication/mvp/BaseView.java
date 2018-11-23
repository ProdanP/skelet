package eu.bufa.prodan.myapplication.mvp;

public interface BaseView {
    void displayLoading(boolean show);
    void displayError(String message, String field);
    void noUserErrorLogout();
    void noInternetConnection();
}
