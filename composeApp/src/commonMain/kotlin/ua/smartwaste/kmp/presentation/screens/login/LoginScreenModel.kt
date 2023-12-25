package ua.smartwaste.kmp.presentation.screens.login

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ua.smartwaste.kmp.domain.repository.AuthRepository

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

class LoginScreenModel(
    private val authRepository: AuthRepository,
) : StateScreenModel<LoginState>(LoginState()) {

    private fun swapLoginMode(): LoginMode {
        return when (state.value.loginMode) {
            LoginMode.LOGIN -> LoginMode.REGISTER
            LoginMode.REGISTER -> LoginMode.LOGIN
        }
    }

    private fun login() = screenModelScope.launch(Dispatchers.IO) {
        authRepository.login(
            email = state.value.email,
            password = state.value.password,
        )
    }

    private fun register() = screenModelScope.launch(Dispatchers.IO) {
        authRepository.register(
            username = state.value.username,
            email = state.value.email,
            password = state.value.password,
        )
    }

    private fun validateInput(
        email: String = state.value.email,
        username: String = state.value.username,
        password: String = state.value.password,
        loginMode: LoginMode = state.value.loginMode,
    ) = screenModelScope.launch(Dispatchers.IO) {
        val isEmailValid = validateEmail(email)
        val isUsernameValid = validateUsername(username)
        val isPasswordValid = validatePassword(password)
        val signUpUsernameCheck = if (loginMode == LoginMode.REGISTER) isUsernameValid else true
        val isInputValid = isEmailValid && isPasswordValid && signUpUsernameCheck
        val event = if (isInputValid) {
            LoginEvent.EnableLoginButton
        } else {
            LoginEvent.DisableLoginButton
        }
        sendEvent(event)
    }

    private fun validateEmail(email: String): Boolean {
        val email = email.trim()
        return email.contains("@") && email.length >= MIN_EMAIL_LENGTH
    }

    private fun validateUsername(username: String): Boolean {
        val username = username.trim()
        return username.length >= MIN_USERNAME_LENGTH &&
            !username.contains("[!\"#$%&'()*+,-/:;\\\\<=>?@\\[\\]^`{|}~]".toRegex())
    }

    private fun validatePassword(password: String): Boolean {
        val password = password.trim()
        return password.length >= MIN_PASSWORD_LENGTH
    }

    fun sendEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.DisableLoginButton -> {
                mutableState.update { it.copy(loginButtonEnabled = false) }
            }

            LoginEvent.EnableLoginButton -> {
                mutableState.update { it.copy(loginButtonEnabled = true) }
            }

            LoginEvent.LoginClicked -> {
                when (state.value.loginMode) {
                    LoginMode.LOGIN -> login()
                    LoginMode.REGISTER -> register()
                }
            }

            is LoginEvent.SendTextFieldMessage -> {
                // TODO: implement
            }

            LoginEvent.SwapLoginMode -> {
                val mode = swapLoginMode()
                mutableState.update { it.copy(loginMode = mode) }
            }

            is LoginEvent.UpdateEmailTextField -> {
                validateInput(email = event.email)
                mutableState.update {
                    it.copy(email = event.email, loginTextFieldError = null)
                }
            }

            is LoginEvent.UpdatePasswordTextField -> {
                validateInput(password = event.password)
                mutableState.update {
                    it.copy(password = event.password, loginTextFieldError = null)
                }
            }

            is LoginEvent.UpdateUsernameTextField -> {
                validateInput(username = event.username)
                mutableState.update {
                    it.copy(username = event.username, loginTextFieldError = null)
                }
            }
        }
    }

    private companion object {
        const val MIN_EMAIL_LENGTH = 10
        const val MIN_USERNAME_LENGTH = 4
        const val MIN_PASSWORD_LENGTH = 8
    }
}
