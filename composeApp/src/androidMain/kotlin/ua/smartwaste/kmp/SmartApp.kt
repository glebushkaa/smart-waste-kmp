package ua.smartwaste.kmp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import ua.gleb.smartwaste.di.modules
import ua.gleb.smartwaste.log.initializeLogger
import ua.smartwaste.kmp.presentation.di.screenModelModule

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */


class SmartApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SmartApp)
            modules(modules = modules + screenModelModule)
        }
        initializeLogger()
    }
}
