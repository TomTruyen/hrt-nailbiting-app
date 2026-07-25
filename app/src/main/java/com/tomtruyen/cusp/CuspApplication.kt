package com.tomtruyen.cusp

import android.app.Application
import com.tomtruyen.cusp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CuspApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@CuspApplication)
            modules(appModule)
        }
    }
}
