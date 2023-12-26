package ua.smartwaste.kmp.presentation.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import ua.smartwaste.kmp.presentation.screens.login.LoginScreen
import ua.smartwaste.kmp.presentation.theme.SmartTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

object ProfileScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val screenModel = getScreenModel<ProfileScreenModel>()
        val state by screenModel.state.collectAsState()
        val navigationEvent by screenModel.navigationEvent.collectAsState(null)

        ProfileScreenContent(
            state = state,
            sendEvent = screenModel::sendEvent,
        )

        LaunchedEffect(navigationEvent) {
            val event = navigationEvent ?: return@LaunchedEffect
            when (event) {
                ProfileNavigationEvent.NavigateToLogin -> {
                    navigator?.replaceAll(LoginScreen)
                }
            }
        }
    }
}

@Composable
private fun ProfileScreenContent(
    state: ProfileState,
    sendEvent: (ProfileEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SmartTheme.palette.background),
    ) {
        Text(
            text = "Username: ${state.username}",
        )
        Text(
            text = "Email: ${state.email}",
        )
        Button(
            modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxWidth(),
            onClick = {
                val event = ProfileEvent.LogOut
                sendEvent(event)
            },
        ) {
            Text("Log Out")
        }
    }
}
