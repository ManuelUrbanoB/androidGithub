package com.murbano.aplicationrepos.commons

import android.app.Application
import com.murbano.aplicationrepos.modules.moduleRepos
import com.murbano.aplicationrepos.modules.moduleUser
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(moduleUser, moduleRepos))
        }
    }
}