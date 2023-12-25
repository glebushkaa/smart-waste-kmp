package ua.smartwaste.kmp.presentation.screens.login

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

sealed class LoginEvent {

    data object LoginClicked : LoginEvent()
    data object SwapLoginMode : LoginEvent()
    data object DisableLoginButton : LoginEvent()
    data object EnableLoginButton : LoginEvent()
    data class UpdateEmailTextField(val email: String) : LoginEvent()
    data class UpdateUsernameTextField(val username: String) : LoginEvent()
    data class UpdatePasswordTextField(val password: String) : LoginEvent()
    data class SendTextFieldMessage(
        val field: LoginTextFieldType,
        val message: String,
    ) : LoginEvent()
} 
