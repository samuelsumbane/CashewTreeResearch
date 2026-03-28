package com.samuelsumbane.cashewtreedata

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class CashewTreeApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CashewTreeApp)
            modules(appModule)
        }
    }
}