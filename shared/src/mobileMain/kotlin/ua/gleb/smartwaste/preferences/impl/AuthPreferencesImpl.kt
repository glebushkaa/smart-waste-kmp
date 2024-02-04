package ua.gleb.smartwaste.preferences.impl

import com.russhwolf.settings.Settings
import ua.gleb.smartwaste.preferences.api.AuthPreferences

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

class AuthPreferencesImpl(
    private val authSettings: Settings,
) : AuthPreferences {

    override val token: String?
        get() = authSettings.getStringOrNull(TOKEN_KEY)

    override fun setToken(token: String) {
        authSettings.putString(TOKEN_KEY, token)
    }

    override fun removeToken() {
        authSettings.remove(TOKEN_KEY)
    }

    private companion object {
        private const val TOKEN_KEY = "token-key"
    }
}
