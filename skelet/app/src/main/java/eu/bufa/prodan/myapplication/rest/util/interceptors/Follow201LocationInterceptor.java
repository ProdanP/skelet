package eu.bufa.prodan.myapplication.rest.util.interceptors;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_SEE_OTHER;

public class Follow201LocationInterceptor implements Interceptor {

    private static final String FOLLOW_REDIRECT = "follow-redirect";
    public static final String FOLLOW_HEADER = FOLLOW_REDIRECT + ":true";
    private static final String LOCATION = "Location";

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        boolean shouldRedirect = request.header(FOLLOW_REDIRECT) != null;

        if (shouldRedirect) {
            request = request.newBuilder().removeHeader(FOLLOW_REDIRECT).build();
        }

        Response response = chain.proceed(request);
        if (shouldRedirect && response.code() == HTTP_CREATED && response.header(LOCATION) != null) {
            return response.newBuilder().code(HTTP_SEE_OTHER).build();
        }
        return response;
    }
}