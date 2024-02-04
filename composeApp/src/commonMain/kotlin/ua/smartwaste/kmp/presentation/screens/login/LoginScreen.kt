package ua.smartwaste.kmp.presentation.screens.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import ua.smartwaste.kmp.presentation.components.SmartButton
import ua.smartwaste.kmp.presentation.core.EIGHT_HUNDRED_MILLIS
import ua.smartwaste.kmp.presentation.core.FOUR_HUNDRED_MILLIS
import ua.smartwaste.kmp.presentation.core.ResourceType
import ua.smartwaste.kmp.presentation.core.painterDrawableResource
import ua.smartwaste.kmp.presentation.popup.SmartLoader
import ua.smartwaste.kmp.presentation.screens.profile.ProfileScreen
import ua.smartwaste.kmp.presentation.theme.SmartTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

object LoginScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val screenModel = getScreenModel<LoginScreenModel>()
        val state by screenModel.state.collectAsState()
        val navigationEvent by screenModel.navigationEvent.collectAsState(null)

        if (state.loaderVisible) {
            SmartLoader()
        }

        LoginScreenContent(
            state = state,
            sendEvent = screenModel::sendEvent,
        )

        LaunchedEffect(navigationEvent) {
            val event = navigationEvent ?: return@LaunchedEffect
            when (event) {
                LoginNavigationEvent.NavigateToProfile -> {
                    navigator?.replaceAll(ProfileScreen)
                }
            }
        }
    }
}

@Composable
private fun LoginScreenContent(
    state: LoginState = LoginState(),
    sendEvent: (LoginEvent) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SmartTheme.palette.background)
            .padding(horizontal = SmartTheme.offset.width.large),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LoginFields(
            modifier = Modifier
                .widthIn(
                    max = SmartTheme.dimension.login.maxLoginScreenWidth,
                )
                .fillMaxWidth(),
            screenType = state.loginMode,
            username = state.username,
            email = state.email,
            password = state.password,
            usernameChanged = { username ->
                sendEvent(LoginEvent.UpdateUsernameTextField(username))
            },
            emailChanged = { email ->
                sendEvent(LoginEvent.UpdateEmailTextField(email))
            },
            passwordChanged = { password ->
                sendEvent(LoginEvent.UpdatePasswordTextField(password))
            },
        )
        Spacer(modifier = Modifier.weight(1f))
        BottomLoginButtons(
            modifier = Modifier
                .padding(top = SmartTheme.offset.height.regular)
                .widthIn(
                    max = SmartTheme.dimension.login.maxLoginScreenWidth,
                )
                .fillMaxWidth(),
            screenType = state.loginMode,
            loginButtonEnabled = state.loginButtonEnabled,
            onLoginClick = {
                sendEvent(LoginEvent.LoginClicked)
            },
            onChangeScreenTypeClick = {
                sendEvent(LoginEvent.SwapLoginMode)
            },
        )
    }
}

@Composable
private fun AppTitle(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterDrawableResource("img_recycle"),
            contentDescription = null,
            modifier = Modifier.size(
                SmartTheme.dimension.login.topLogoSize,
            ),
        )
        Spacer(modifier = Modifier.width(SmartTheme.offset.width.small))
        Text(
            text = "SmartWaste",
            style = SmartTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
            ),
            color = SmartTheme.palette.onSurface,
        )
    }
}

@Composable
private fun LoginHeader(
    modifier: Modifier = Modifier,
    screenType: LoginMode,
) {
    val text = when (screenType) {
        LoginMode.LOGIN -> "Sign in"
        LoginMode.REGISTER -> "Sign up"
    }

    Text(
        modifier = modifier,
        text = text,
        style = SmartTheme.typography.headlineLarge,
        color = SmartTheme.palette.onBackground,
    )
}

@Composable
private fun LoginFields(
    modifier: Modifier = Modifier,
    screenType: LoginMode,
    username: String,
    email: String,
    password: String,
    emailChanged: (String) -> Unit = {},
    passwordChanged: (String) -> Unit = {},
    usernameChanged: (String) -> Unit = {},
) {
    val hugeOffset = SmartTheme.offset.height.huge
    val loginFieldHeight = SmartTheme.dimension.login.fieldHeight
    val defaultOffset = SmartTheme.offset.height.default
    val usernameFieldHeight by remember(screenType) {
        derivedStateOf {
            if (screenType == LoginMode.REGISTER) loginFieldHeight else defaultOffset
        }
    }
    val padding by remember(screenType) {
        derivedStateOf {
            if (screenType == LoginMode.REGISTER) hugeOffset else defaultOffset
        }
    }
    val animatedPadding by animateDpAsState(
        targetValue = padding,
        animationSpec = tween(FOUR_HUNDRED_MILLIS.toInt()),
    )
    val animatedUsernameFieldHeight by animateDpAsState(
        targetValue = usernameFieldHeight,
        animationSpec = if (screenType == LoginMode.REGISTER) {
            tween(FOUR_HUNDRED_MILLIS.toInt())
        } else {
            tween(EIGHT_HUNDRED_MILLIS.toInt())
        },
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(
                min = SmartTheme.dimension.login.buttonHeight,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppTitle(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = SmartTheme.offset.height.regular),
        )
        LoginHeader(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = SmartTheme.offset.height.regular),
            screenType = screenType,
        )
        AnimatedVisibility(
            modifier = Modifier.padding(top = animatedPadding),
            visible = screenType == LoginMode.REGISTER,
            enter = slideInHorizontally(
                initialOffsetX = { 2 * it },
                animationSpec = tween(EIGHT_HUNDRED_MILLIS.toInt()),
            ) + fadeIn(
                animationSpec = tween(FOUR_HUNDRED_MILLIS.toInt()),
            ),
            exit = slideOutHorizontally(
                targetOffsetX = { 2 * it },
                animationSpec = tween(EIGHT_HUNDRED_MILLIS.toInt()),
            ) + fadeOut(
                animationSpec = tween(FOUR_HUNDRED_MILLIS.toInt()),
            ),
        ) {
            LoginTextField(
                modifier = Modifier.height(animatedUsernameFieldHeight),
                value = username,
                onValueChanged = usernameChanged,
                placeholder = "Username",
                keyboardType = KeyboardType.Text,
            )
        }
        Spacer(modifier = Modifier.height(SmartTheme.offset.height.regular))
        LoginTextField(
            value = email,
            onValueChanged = emailChanged,
            placeholder = "Email",
            keyboardType = KeyboardType.Text,
        )
        Spacer(modifier = Modifier.height(SmartTheme.offset.height.regular))
        LoginTextField(
            value = password,
            onValueChanged = passwordChanged,
            placeholder = "Password",
            keyboardType = KeyboardType.Password,
        )
    }
}

@Composable
fun LoginTextField(
    modifier: Modifier = Modifier,
    value: String,
    isError: Boolean = false,
    onValueChanged: (String) -> Unit = {},
    placeholder: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    var textHidden by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .height(
                SmartTheme.dimension.login.fieldHeight,
            ),
        value = value,
        singleLine = true,
        onValueChange = {
            val query = if (it.length > MAX_QUERY_LENGTH) it.substring(0, MAX_QUERY_LENGTH) else it
            onValueChanged(query)
        },
        isError = isError,
        visualTransformation = if (
            keyboardType != KeyboardType.Password ||
            textHidden
        ) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
        ),
        placeholder = {
            Text(
                text = placeholder,
                style = SmartTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = SmartTheme.palette.onSurface.copy(
                    alpha = PLACEHOLDER_TEXT_ALPHA,
                ),
            )
        },
        trailingIcon = {
            if (keyboardType != KeyboardType.Password) return@OutlinedTextField
            IconButton(onClick = { textHidden = !textHidden }) {
                Icon(
                    painter = painterDrawableResource(
                        id = if (textHidden) "ic_visibility_off" else "ic_visibility",
                        type = ResourceType.XML,
                    ),
                    contentDescription = null,
                    tint = SmartTheme.palette.onSurface,
                )
            }
        },
        textStyle = SmartTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Bold,
        ),
        colors = TextFieldDefaults.colors(
            cursorColor = SmartTheme.palette.onSurface,
            focusedTextColor = SmartTheme.palette.onSurface,
            unfocusedTextColor = SmartTheme.palette.onSurface,
            errorTextColor = SmartTheme.palette.onSurface,
            focusedContainerColor = SmartTheme.palette.surface,
            unfocusedContainerColor = SmartTheme.palette.surface,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = SmartTheme.palette.primary,
            errorIndicatorColor = SmartTheme.palette.error,
            errorContainerColor = SmartTheme.palette.error.copy(alpha = ERROR_CONTAINER_COLOR_ALPHA),
        ),
        shape = SmartTheme.shape.huge,
    )
}

@Composable
private fun BottomLoginButtons(
    modifier: Modifier = Modifier,
    screenType: LoginMode,
    loginButtonEnabled: Boolean,
    onLoginClick: () -> Unit = {},
    onChangeScreenTypeClick: () -> Unit = {},
) {
    val buttonText = when (screenType) {
        LoginMode.LOGIN -> "Sign in"
        LoginMode.REGISTER -> "Sign up"
    }
    val loginText = buildLoginText(screenType)

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SmartButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(
                    SmartTheme.dimension.login.buttonHeight,
                ),
            enabled = loginButtonEnabled,
            shape = SmartTheme.shape.small,
            onClick = onLoginClick,
            content = {
                Text(
                    text = buttonText,
                    style = SmartTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.ExtraBold,
                    ),
                    color = SmartTheme.palette.onPrimary,
                )
            },
        )
        ClickableText(
            modifier = Modifier
                .padding(
                    top = SmartTheme.offset.height.medium,
                    bottom = SmartTheme.offset.height.large,
                ),
            text = loginText,
            style = SmartTheme.typography.bodyMedium,
        ) { offset ->
            loginText.getStringAnnotations(
                tag = LOGIN_ANNOTATION_TAG,
                start = offset,
                end = offset,
            ).firstOrNull()?.let {
                onChangeScreenTypeClick()
            }
        }
    }
}

@Composable
private fun buildLoginText(
    loginMode: LoginMode,
): AnnotatedString {
    val startText = when (loginMode) {
        LoginMode.LOGIN -> "Don't have an account? "
        LoginMode.REGISTER -> "Already have an account? "
    }
    val endText = when (loginMode) {
        LoginMode.LOGIN -> "Sign up"
        LoginMode.REGISTER -> "Sign in"
    }
    return buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = SmartTheme.palette.onBackground,
            ),
        ) {
            append(startText)
        }
        pushStringAnnotation(tag = LOGIN_ANNOTATION_TAG, annotation = "")
        withStyle(
            style = SpanStyle(
                color = SmartTheme.palette.primary.copy(
                    alpha = LOGIN_LINK_TEXT_COLOR_ALPHA,
                ),
            ),
        ) {
            append(endText)
        }
        pop()
    }
}

private const val LOGIN_ANNOTATION_TAG = "login"

private const val LOGIN_LINK_TEXT_COLOR_ALPHA = 0.6f

private const val MAX_QUERY_LENGTH = 100

private const val PLACEHOLDER_TEXT_ALPHA = 0.6f
private const val ERROR_CONTAINER_COLOR_ALPHA = 0.1f
