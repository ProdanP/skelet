package eu.bufa.prodan.myapplication.rest.util;

import com.ebs.psalms.ApplicationController;
import com.ebs.psalms.BuildConfig;
import com.ebs.psalms.rest.api_service.Config;
import com.ebs.psalms.rest.api_service.util.interceptors.AuthenticationInterceptor;
import com.ebs.psalms.rest.api_service.util.interceptors.ConnectivityInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private Retrofit retrofit = null;

    private static final APIClient ourInstance = new APIClient();

    public static APIClient getInstance() {
        return ourInstance;
    }

    private APIClient() {
    }

    public Retrofit getClient() {
        if (retrofit == null) {
            RxJava2CallAdapterFactory rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(io.reactivex.schedulers.Schedulers.io());

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            httpClient.addInterceptor(new ConnectivityInterceptor(ApplicationController.getInstance().getContext()));
            httpClient.addInterceptor(new AuthenticationInterceptor());
            if(BuildConfig.DEBUG){
                httpClient.addInterceptor(logging);
            }
            httpClient.connectTimeout(10, TimeUnit.SECONDS);
            httpClient.readTimeout(10, TimeUnit.SECONDS);

            Gson gson = new GsonBuilder()
                    .serializeNulls()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Config.ROOT_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(rxAdapter)
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }
}
