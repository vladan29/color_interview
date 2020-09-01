package com.vladan.color_interview.api

import android.content.Context
import com.google.gson.Gson
import com.vladan.color_interview.BuildConfig
import com.vladan.color_interview.utils.AppConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by vladan on 8/29/2020
 */
object RetrofitService {
    @JvmStatic
    fun create(context: Context?, gson: Gson?): ApiService {
        var retrofit: Retrofit?
        val jWTToken = context?.let { AppConstants.checkOrCreateJWT(it) }
        val credentials = "Bearer $jWTToken"
        val builder = OkHttpClient.Builder()
        builder.readTimeout(10, TimeUnit.SECONDS)
        builder.connectTimeout(5, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        }
        builder.addInterceptor(Interceptor { chain: Interceptor.Chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader("Authorization", credentials)
                .build()
            chain.proceed(request)
        })
        val client = builder.build()
        retrofit = if (gson == null) {
            Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
        else {
            Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson)).build()
        }
        return retrofit.create(ApiService::class.java)
    }
}