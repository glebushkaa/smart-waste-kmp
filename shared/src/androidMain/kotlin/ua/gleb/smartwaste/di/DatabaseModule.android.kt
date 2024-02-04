package ua.gleb.smartwaste.di

import org.koin.core.scope.Scope
import ua.gleb.smartwaste.database.impl.DriverFactory

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/31/2023
 */

actual fun Scope.singleDriverFactory(): DriverFactory {
    return DriverFactory(get())
}
