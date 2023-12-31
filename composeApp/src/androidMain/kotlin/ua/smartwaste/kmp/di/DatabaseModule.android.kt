package ua.smartwaste.kmp.di

import org.koin.core.scope.Scope
import ua.smartwaste.kmp.database.impl.DriverFactory

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/31/2023
 */

actual fun Scope.singleDriverFactory(): DriverFactory {
    return DriverFactory(get())
}
