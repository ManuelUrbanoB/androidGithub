package com.murbano.aplicationrepos.commons

import android.app.Application
import com.murbano.aplicationrepos.modules.moduleLogin
import com.murbano.aplicationrepos.modules.moduleMain
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(moduleMain, moduleLogin))
        }
    }
}