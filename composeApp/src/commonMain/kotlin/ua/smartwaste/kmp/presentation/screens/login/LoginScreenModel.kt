package ua.smartwaste.kmp.presentation.screens.login

import cafe.adriel.voyager.core.model.StateScreenModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ua.smartwaste.kmp.domain.usecase.auth.LoginUseCase
import ua.smartwaste.kmp.domain.usecase.auth.RegisterUseCase
import ua.smartwaste.kmp.presentation.core.modelScope

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

class LoginScreenModel(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
) : StateScreenModel<LoginState>(LoginState()) {

    private val _navigationEvent = Channel<LoginNavigationEvent>()
    val navigationEvent = _navigationEvent.receiveAsFlow()

    private fun swapLoginMode(): LoginMode {
        return when (state.value.loginMode) {
            LoginMode.LOGIN -> LoginMode.REGISTER
            LoginMode.REGISTER -> LoginMode.LOGIN
        }
    }

    private fun login() = modelScope.launch {
        mutableState.update { it.copy(loaderVisible = true) }
        val params = LoginUseCase.Params(
            email = state.value.email,
            password = state.value.password,
        )
        loginUseCase(params).onSuccess {
            _navigationEvent.trySend(LoginNavigationEvent.NavigateToProfile)
        }
        mutableState.update { it.copy(loaderVisible = false) }
    }

    private fun register() = modelScope.launch {
        mutableState.update { it.copy(loaderVisible = true) }
        val params = RegisterUseCase.Params(
            username = state.value.username,
            email = state.value.email,
            password = state.value.password,
        )
        registerUseCase(params).onSuccess {
            _navigationEvent.trySend(LoginNavigationEvent.NavigateToProfile)
        }
        mutableState.update { it.copy(loaderVisible = false) }
    }

    private fun validateInput(
        email: String = state.value.email,
        username: String = state.value.username,
        password: String = state.value.password,
        loginMode: LoginMode = state.value.loginMode,
    ) {
        val isEmailValid = LoginValidator.validateEmail(email)
        val isUsernameValid = LoginValidator.validateUsername(username)
        val isPasswordValid = LoginValidator.validatePassword(password)
        val signUpUsernameCheck = if (loginMode == LoginMode.REGISTER) isUsernameValid else true
        val isInputValid = isEmailValid && isPasswordValid && signUpUsernameCheck
        val event = if (isInputValid) {
            LoginEvent.EnableLoginButton
        } else {
            LoginEvent.DisableLoginButton
        }
        sendEvent(event)
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
}
