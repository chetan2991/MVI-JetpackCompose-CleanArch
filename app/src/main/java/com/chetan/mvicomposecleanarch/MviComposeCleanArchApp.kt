package com.chetan.mvicomposecleanarch

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MviComposeCleanArchApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}