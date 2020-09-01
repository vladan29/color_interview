package com.vladan.color_interview

import android.app.Application

/**
 * Created by vladan on 8/28/2020
 */
// Used in the manifest
@Suppress("unused")
class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        @get:Synchronized
        private var instance: AppApplication? = null
    }
}