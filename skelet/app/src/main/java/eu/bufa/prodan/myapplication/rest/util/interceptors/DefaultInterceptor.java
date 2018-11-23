package eu.bufa.prodan.myapplication.rest.util.interceptors;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class DefaultInterceptor implements Interceptor {

    public DefaultInterceptor() {
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();
        //String token = CurrentUser.getInstance().getAccessToken(ApplicationController.getAppContext());
        Request.Builder builder = original.newBuilder();
       /* if(token != null && chain.request().header("no_token") == null) {
            builder.header("Authorization", "Bearer " + token);
        }*/
        Request request = builder.build();
        return chain.proceed(request);
    }
}
