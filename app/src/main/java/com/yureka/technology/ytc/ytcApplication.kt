package com.yureka.technology.ytc

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree
import com.yureka.technology.ytc.BuildConfig

@HiltAndroidApp
class ytcApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        installLogging()
    }

    private fun installLogging() {
        if (BuildConfig.DEBUG) {
            // TODO - Check if you need non-debug tree line following:
            // https://github.com/JakeWharton/timber/blob/master/timber-sample/src/main/java/com/example/timber/ExampleApp.java
            Timber.plant(DebugTree())
        }
    }
}
