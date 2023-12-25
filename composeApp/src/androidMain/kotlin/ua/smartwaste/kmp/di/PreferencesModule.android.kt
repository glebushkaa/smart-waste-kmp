package ua.smartwaste.kmp.di

import android.content.Context
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

actual fun Scope.provideSettings(name: String): Settings {
    val sharedPreferences = androidContext().getSharedPreferences(
        name,
        Context.MODE_PRIVATE,
    )
    return SharedPreferencesSettings(sharedPreferences)
}
