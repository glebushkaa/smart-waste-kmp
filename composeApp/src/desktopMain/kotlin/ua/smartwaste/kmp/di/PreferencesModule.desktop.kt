package ua.smartwaste.kmp.di

import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings
import org.koin.core.scope.Scope
import java.util.prefs.Preferences

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

actual fun Scope.provideSettings(name: String): Settings {
    val preferences: Preferences = Preferences.userRoot().node(name)
    return PreferencesSettings(preferences)
}