package ua.smartwaste.kmp.presentation.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import ua.smartwaste.kmp.presentation.theme.SmartTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

object ProfileScreen : Screen {

    @Composable
    override fun Content() {
        ProfileScreenContent()
    }
}

@Composable
private fun ProfileScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SmartTheme.palette.background),
    ) {
        Text(
            text = "Username: gle.bushkaa",
        )
        Text(
            text = "Email: gleb.mokryy@gmail.com",
        )
    }
}
