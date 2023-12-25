package ua.smartwaste.kmp.presentation.screens.login

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ua.smartwaste.kmp.domain.repository.AuthRepository
import ua.smartwaste.kmp.presentation.core.createStateReducerFlow

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

class LoginScreenModel(
    private val authRepository: AuthRepository,
) : ScreenModel {

    val stateReducer = createStateReducerFlow(
        initialState = LoginState(),
        reduceState = ::reduce,
    )
    val state: StateFlow<LoginState> = stateReducer.state

    private fun swapLoginMode(): LoginMode {
        val loginMode = stateReducer.state.value.loginMode
        return when (loginMode) {
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
        stateReducer.sendEvent(event)
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

    private fun reduce(currentState: LoginState, event: LoginEvent): LoginState {
        when (event) {
            LoginEvent.DisableLoginButton -> {
                return currentState.copy(loginButtonEnabled = false)
            }

            LoginEvent.EnableLoginButton -> {
                return currentState.copy(loginButtonEnabled = true)
            }

            LoginEvent.LoginClicked -> {
                when (currentState.loginMode) {
                    LoginMode.LOGIN -> login()
                    LoginMode.REGISTER -> register()
                }
            }

            is LoginEvent.SendTextFieldMessage -> {
                // TODO: implement
            }

            LoginEvent.SwapLoginMode -> {
                val mode = swapLoginMode()
                return currentState.copy(loginMode = mode)
            }

            is LoginEvent.UpdateEmailTextField -> {
                validateInput(email = event.email)
                return currentState.copy(email = event.email, loginTextFieldError = null)
            }

            is LoginEvent.UpdatePasswordTextField -> {
                validateInput(password = event.password)
                return currentState.copy(email = event.password, loginTextFieldError = null)
            }

            is LoginEvent.UpdateUsernameTextField -> {
                validateInput(username = event.username)
                return currentState.copy(email = event.username, loginTextFieldError = null)
            }
        }
        return currentState
    }

    private companion object {
        const val MIN_EMAIL_LENGTH = 10
        const val MIN_USERNAME_LENGTH = 4
        const val MIN_PASSWORD_LENGTH = 8
    }
}
