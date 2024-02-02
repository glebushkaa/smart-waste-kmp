package ua.gleb.smartwaste.database.impl

import app.cash.sqldelight.db.SqlDriver
import smartwaste.rubbish.SmartDatabase

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/31/2023
 */

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(factory: DriverFactory): SmartDatabase {
    val driver = factory.createDriver()
    return SmartDatabase(driver)
}
