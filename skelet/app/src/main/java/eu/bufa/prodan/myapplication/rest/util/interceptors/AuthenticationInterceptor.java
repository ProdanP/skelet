package eu.bufa.prodan.myapplication.rest.util.interceptors;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor {
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();
        //String token = CurrentUser.getInstance().getAccessToken();
        Request.Builder builder = original.newBuilder();
        /*if(token != null)
            builder.header("x-token", token);*/
        Request request = builder.build();
        return chain.proceed(request);
    }
}
