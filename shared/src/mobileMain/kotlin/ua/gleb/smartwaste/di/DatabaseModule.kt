package ua.gleb.smartwaste.di

import org.koin.core.module.dsl.singleOf
import org.koin.core.scope.Scope
import org.koin.dsl.bind
import org.koin.dsl.module
import smartwaste.rubbish.RubbishQueries
import smartwaste.rubbish.SmartDatabase
import ua.gleb.smartwaste.database.api.ItemsDatabase
import ua.gleb.smartwaste.database.impl.ItemsDatabaseImpl
import ua.gleb.smartwaste.database.impl.DriverFactory
import ua.gleb.smartwaste.database.impl.createDatabase

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/31/2023
 */

expect fun Scope.singleDriverFactory(): DriverFactory

val databaseModule = module {
    single { singleDriverFactory() }
    single { createDatabase(get()) }

    single<RubbishQueries> {
        val database: SmartDatabase = get()
        database.rubbishQueries
    }

    singleOf(::ItemsDatabaseImpl) bind ItemsDatabase::class
}
