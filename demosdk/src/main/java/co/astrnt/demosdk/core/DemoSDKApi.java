package co.astrnt.demosdk.core;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import co.astrnt.demosdk.DemoApiService;
import io.reactivex.annotations.NonNull;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by deni rohimat on 26/11/18
 */
public class DemoSDKApi {

    private DemoApiService mDemoApiService;

    public DemoSDKApi(String baseUrl, boolean isDebugable) {

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.readTimeout(60, TimeUnit.SECONDS);
        httpClientBuilder.connectTimeout(60, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(120, TimeUnit.SECONDS);
        httpClientBuilder.callTimeout(60, TimeUnit.SECONDS);
        httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("device", "android")
                        .addHeader("Content-Type", "application/x-www-form-urlencoded")
                        .build();
                return chain.proceed(request);
            }
        });

        if (isDebugable) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            httpClientBuilder.addInterceptor(loggingInterceptor);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .baseUrl(baseUrl)
                .build();

        mDemoApiService = retrofit.create(DemoApiService.class);
    }

    public DemoApiService getApiService() {
        return mDemoApiService;
    }

}
