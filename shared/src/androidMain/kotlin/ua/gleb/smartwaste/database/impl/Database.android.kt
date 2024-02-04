package ua.gleb.smartwaste.database.impl

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import smartwaste.rubbish.SmartDatabase

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/31/2023
 */

actual class DriverFactory(
    private val context: Context,
) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(SmartDatabase.Schema, context, "smartwaste.db")
    }
}
