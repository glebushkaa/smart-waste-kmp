package ua.smartwaste.kmp.di

import org.koin.core.scope.Scope
import org.koin.dsl.module
import ua.smartwaste.kmp.database.DriverFactory
import ua.smartwaste.kmp.database.createDatabase

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/31/2023
 */

expect fun Scope.singleDriverFactory(): DriverFactory

val databaseModule = module {
    single { singleDriverFactory() }
    single { createDatabase(get()) }
}
