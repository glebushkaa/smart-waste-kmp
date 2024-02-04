package ua.gleb.smartwaste.di

import com.russhwolf.settings.Settings
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module
import ua.gleb.smartwaste.preferences.api.AuthPreferences
import ua.gleb.smartwaste.preferences.impl.AuthPreferencesImpl

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

private const val AUTH_SETTINGS = "authSettings"

expect fun Scope.provideSettings(name: String): Settings

val preferencesModule = module {

    single<Settings>(named(AUTH_SETTINGS)) { provideSettings(name = AUTH_SETTINGS) }

    single<AuthPreferences> {
        AuthPreferencesImpl(
            authSettings = get(named(AUTH_SETTINGS)),
        )
    }
}
