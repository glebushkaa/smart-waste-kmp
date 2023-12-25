package ua.smartwaste.kmp

import android.app.Application
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import ua.smartwaste.kmp.di.modules

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

class SmartApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SmartApp)
            modules(modules = modules)
        }
        Napier.base(DebugAntilog())
    }
}
