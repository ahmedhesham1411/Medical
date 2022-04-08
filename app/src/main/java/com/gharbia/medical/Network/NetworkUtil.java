package com.gharbia.medical.Network;

import com.gharbia.medical.Utilities.Constant;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

public class NetworkUtil {
    public static NetworkInterface getRetrofitNoHeader() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(200, TimeUnit.SECONDS)
                .writeTimeout(200, TimeUnit.SECONDS)
                .readTimeout(200, TimeUnit.SECONDS);
        httpClient.addInterceptor(chain -> {

            Request original = chain.request();
            Request.Builder builder = original.newBuilder()
                    .addHeader("lang", Locale.getDefault().getLanguage())
                    .addHeader("Content-Type", "application/json")
                    .method(original.method(), original.body());
            return chain.proceed(builder.build());
        });
        httpClient.interceptors().add(interceptor);  // <-- this is the important line!
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());

        return new Retrofit.Builder()
                .baseUrl(Constant.BASEURL)
                .client(httpClient.build())
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(NetworkInterface.class);


    }

    public static NetworkInterface getRetrofitNoHeaderMultiPart() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(200, TimeUnit.SECONDS)
                .writeTimeout(200, TimeUnit.SECONDS)
                .readTimeout(200, TimeUnit.SECONDS);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request original = chain.request();
                Request.Builder builder = original.newBuilder()
                        .addHeader("lang", Locale.getDefault().getLanguage())
                        .method(original.method(), original.body());
                return chain.proceed(builder.build());
            }
        });
        httpClient.interceptors().add(interceptor);  // <-- this is the important line!
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());

        return new Retrofit.Builder()
                .baseUrl(Constant.BASEURL)
                .client(httpClient.build())
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(NetworkInterface.class);

    }

    public static NetworkInterface getRetrofitByToken(String token) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(200, TimeUnit.SECONDS)
                .writeTimeout(200, TimeUnit.SECONDS)
                .readTimeout(200, TimeUnit.SECONDS);

        final String newToken= "Bearer "+token;

        httpClient.addInterceptor(chain -> {

            Request original = chain.request();
            Request.Builder builder = original.newBuilder()
                    .addHeader("Authorization", newToken)
                    .addHeader("lang", Locale.getDefault().getLanguage())
                    .addHeader("Content-Type", "application/json")
                    .method(original.method(), original.body());
            return chain.proceed(builder.build());

        });
        httpClient.interceptors().add(interceptor);  // <-- this is the important line!
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());

        return new Retrofit.Builder()
                .baseUrl(Constant.BASEURL)
                .client(httpClient.build())
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(NetworkInterface.class);
    }

    public static NetworkInterface getRetrofitByTokenMultiPart(String token) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(200, TimeUnit.SECONDS)
                .writeTimeout(200, TimeUnit.SECONDS)
                .readTimeout(200, TimeUnit.SECONDS);

        final String newToken= "Bearer"+token;

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request original = chain.request();
                Request.Builder builder = original.newBuilder()
                        .addHeader("token", token)
                        .addHeader("lang", Locale.getDefault().getLanguage())
                        .method(original.method(), original.body());
                return chain.proceed(builder.build());

            }
        });
        httpClient.interceptors().add(interceptor);  // <-- this is the important line!
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());

        return new Retrofit.Builder()
                .baseUrl(Constant.BASEURL)
                .client(httpClient.build())
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(NetworkInterface.class);
    }
}
