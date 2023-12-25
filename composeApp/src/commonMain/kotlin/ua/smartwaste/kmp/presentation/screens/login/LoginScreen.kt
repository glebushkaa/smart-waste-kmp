@file:OptIn(ExperimentalResourceApi::class)

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
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ua.smartwaste.kmp.presentation.components.SmartButton
import ua.smartwaste.kmp.presentation.core.painterDrawableResource
import ua.smartwaste.kmp.presentation.theme.SmartTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

class LoginScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = getScreenModel<LoginScreenModel>()
        val state by screenModel.state.collectAsState()
    }
}

@Composable
private fun LoginScreenContent(
    modifier: Modifier = Modifier,
    state: LoginState = LoginState(),
    sendEvent: (LoginEvent) -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(SmartTheme.palette.background)
            .padding(horizontal = SmartTheme.offset.width.large),
    ) {
        LoginFields(
            modifier = Modifier
                .widthIn(
                    max = SmartTheme.dimension.login.maxLoginScreenWidth,
                )
                .fillMaxWidth()
                .align(Alignment.TopCenter),
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
        BottomLoginButtons(
            modifier = Modifier
                .widthIn(
                    max = SmartTheme.dimension.login.maxLoginScreenWidth,
                )
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            screenType = state.loginMode,
            loginButtonEnabled = state.loginButtonEnabled,
            onLoginClick = {
//                navigator.push(HomeScreen)
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
            painter = painterDrawableResource("img_book"),
            contentDescription = null,
            modifier = Modifier.size(
                SmartTheme.dimension.login.topLogoSize,
            ),
        )
        Spacer(modifier = Modifier.width(SmartTheme.offset.width.small))
        Text(
            text = "GuideBook",
            style = SmartTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
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
    val giganticOffset = SmartTheme.offset.height.gigantic
    val loginFieldHeight = SmartTheme.dimension.login.fieldHeight
    val defaultOffset = SmartTheme.offset.height.default
    val usernameFieldHeight by remember(screenType) {
        derivedStateOf {
            if (screenType == LoginMode.REGISTER) loginFieldHeight else defaultOffset
        }
    }
    val padding by remember(screenType) {
        derivedStateOf {
            if (screenType == LoginMode.REGISTER) giganticOffset else defaultOffset
        }
    }
    val animatedPadding by animateDpAsState(
        targetValue = padding,
        animationSpec = tween(400),
    )
    val animatedUsernameFieldHeight by animateDpAsState(
        targetValue = usernameFieldHeight,
        animationSpec = if (screenType == LoginMode.REGISTER) tween(400) else tween(800),
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
                .padding(
                    top = SmartTheme.offset.height.large,
                ),
        )
        LoginHeader(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(
                    top = SmartTheme.offset.height.large,
                ),
            screenType = screenType,
        )
        AnimatedVisibility(
            modifier = Modifier.padding(top = animatedPadding),
            visible = screenType == LoginMode.REGISTER,
            enter = slideInHorizontally(
                initialOffsetX = { 2 * it },
                animationSpec = tween(800),
            ) + fadeIn(
                animationSpec = tween(400),
            ),
            exit = slideOutHorizontally(
                targetOffsetX = { 2 * it },
                animationSpec = tween(800),
            ) + fadeOut(
                animationSpec = tween(400),
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
        Spacer(modifier = Modifier.height(SmartTheme.offset.height.huge))
        LoginTextField(
            value = email,
            onValueChanged = emailChanged,
            placeholder = "Email",
            keyboardType = KeyboardType.Text,
        )
        Spacer(modifier = Modifier.height(SmartTheme.offset.height.huge))
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
                style = SmartTheme.typography.titleMedium,
            )
        },
        trailingIcon = {
            if (keyboardType != KeyboardType.Password) return@OutlinedTextField
            val imageRes = if (textHidden) {
                "drawable/ic_visibility_off.xml"
            } else {
                "drawable/ic_visibility.xml"
            }

            IconButton(onClick = { textHidden = !textHidden }) {
                Icon(
                    painter = painterResource(imageRes),
                    contentDescription = null,
                    tint = SmartTheme.palette.onSurface,
                )
            }
        },
        textStyle = SmartTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Bold,
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = SmartTheme.palette.onSurface,
            textColor = SmartTheme.palette.onSurface,
            focusedBorderColor = SmartTheme.palette.primary,
            unfocusedBorderColor = SmartTheme.palette.surface,
            backgroundColor = SmartTheme.palette.surface,
            errorBorderColor = SmartTheme.palette.error,
            placeholderColor = SmartTheme.palette.onSurface.copy(
                alpha = PLACEHOLDER_TEXT_ALPHA,
            ),
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
    onGetUserClicked: () -> Unit = {},
    onChangeScreenTypeClick: () -> Unit = {},
) {
    val buttonText = when (screenType) {
        LoginMode.LOGIN -> "Sign in"
        LoginMode.REGISTER -> "Sign up"
    }
    val loginText = buildLoginText(screenType)

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
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
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
