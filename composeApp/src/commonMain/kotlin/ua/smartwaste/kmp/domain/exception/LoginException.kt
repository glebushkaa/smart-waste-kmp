package ua.smartwaste.kmp.domain.exception

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

data class LoginException(
    val field: LoginField? = null,
    override val message: String,
) : Throwable()
