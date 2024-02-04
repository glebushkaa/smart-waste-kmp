package ua.gleb.smartwaste.domain.exception

data class MissingParamException(
    override val message: String,
): Throwable() {
    override fun toString() = "MissingParamException(message='$message')"
}