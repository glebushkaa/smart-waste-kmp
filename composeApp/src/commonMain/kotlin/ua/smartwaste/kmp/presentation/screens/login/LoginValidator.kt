package ua.smartwaste.kmp.presentation.screens.login

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

object LoginValidator {

    private const val MIN_EMAIL_LENGTH = 10
    private const val MIN_USERNAME_LENGTH = 4
    private const val MIN_PASSWORD_LENGTH = 8

    fun validateEmail(email: String): Boolean {
        val email = email.trim()
        return email.contains("@") && email.length >= MIN_EMAIL_LENGTH
    }

    fun validateUsername(username: String): Boolean {
        val username = username.trim()
        return username.length >= MIN_USERNAME_LENGTH &&
            !username.contains("[!\"#$%&'()*+,-/:;\\\\<=>?@\\[\\]^`{|}~]".toRegex())
    }

    fun validatePassword(password: String): Boolean {
        val password = password.trim()
        return password.length >= MIN_PASSWORD_LENGTH
    }
}
