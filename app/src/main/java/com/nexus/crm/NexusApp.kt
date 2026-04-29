package com.nexus.crm

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

import timber.log.Timber
import com.nexus.crm.BuildConfig

@HiltAndroidApp
class NexusApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}