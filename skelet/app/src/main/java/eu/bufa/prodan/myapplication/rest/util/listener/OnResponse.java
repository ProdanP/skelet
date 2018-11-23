package eu.bufa.prodan.myapplication.rest.util.listener;


import java.util.List;

public  interface OnResponse<T> {
    void onSuccess(T response);
    void onError(int code, List<Error> errors, String error);
}
