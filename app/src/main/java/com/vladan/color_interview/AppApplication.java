package com.vladan.color_interview;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.vladan.color_interview.utils.AppConstants;

/**
 * Created by vladan on 8/28/2020
 */
// Used in the manifest
@SuppressWarnings("unused")
public class AppApplication extends Application {

    private static AppApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    private static synchronized AppApplication getInstance() {
        return mInstance;
    }
}
