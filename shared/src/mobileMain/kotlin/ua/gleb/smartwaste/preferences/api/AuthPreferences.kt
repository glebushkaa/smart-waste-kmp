package ua.gleb.smartwaste.preferences.api

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

interface AuthPreferences {

    val token: String?

    fun setToken(token: String)

    fun removeToken()
}
