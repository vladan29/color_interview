package com.vladan.color_interview.api;

import android.content.Context;

import com.google.gson.Gson;
import com.vladan.color_interview.BuildConfig;
import com.vladan.color_interview.utils.AppConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vladan on 8/29/2020
 */
public class RetrofitService {

    public static ApiService create(Context context, Gson gson) {
        Gson mGson = gson;
        Retrofit retrofit = null;
        String JWTToken = AppConstants.checkOrCreateJWT(context);
        String credentials = "Bearer" + " " + JWTToken;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(5, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.level(HttpLoggingInterceptor.Level.BODY);
        }
        builder.addInterceptor(chain -> {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", credentials)
                    .build();
            return chain.proceed(request);
        });

        OkHttpClient client = builder.build();
        if (mGson == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiService.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        } else {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiService.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(mGson)).build();
        }

        return retrofit.create(ApiService.class);
    }
}

