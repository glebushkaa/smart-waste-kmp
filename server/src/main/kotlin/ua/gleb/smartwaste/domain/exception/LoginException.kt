package ua.gleb.smartwaste.domain.exception

data class LoginException(
    override val message: String,
): Throwable() {
    override fun toString() = "LoginException(message='$message')"
}