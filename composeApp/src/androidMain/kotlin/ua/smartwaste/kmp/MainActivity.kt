package ua.smartwaste.kmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import ua.smartwaste.kmp.presentation.App
import ua.smartwaste.kmp.presentation.theme.SmartTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            window.statusBarColor = SmartTheme.palette.background.toArgb()
            window.navigationBarColor = SmartTheme.palette.background.toArgb()
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}
