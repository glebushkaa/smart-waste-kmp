package ua.smartwaste.kmp.presentation.screens.login

import androidx.compose.runtime.Immutable

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

@Immutable
data class LoginState(
    val loginMode: LoginMode = LoginMode.LOGIN,
    val loginButtonEnabled: Boolean = false,
    val email: String = "",
    val password: String = "",
    val username: String = "",
    val loginTextFieldError: LoginTextFieldError? = null,
    val loaderVisible: Boolean = false,
)
