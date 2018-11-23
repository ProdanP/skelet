package eu.bufa.prodan.myapplication.rest.util.listener;

import java.util.List;

public class ClassResponseListener <T> implements OnResponse<T> {

    @Override
    public void onSuccess(T response) {

    }

    @Override
    public void onError(int code, List list, String error) {

    }
}