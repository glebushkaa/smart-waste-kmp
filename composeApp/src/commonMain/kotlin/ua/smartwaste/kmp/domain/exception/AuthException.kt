package ua.smartwaste.kmp.domain.exception

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

data class AuthException(
    val code: Int,
    override val message: String,
) : Throwable() {

    override fun toString() = "AuthException(code=$code, message='$message')"
}
