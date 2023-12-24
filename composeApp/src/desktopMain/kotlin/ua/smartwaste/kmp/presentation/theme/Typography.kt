package ua.smartwaste.kmp.presentation.theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/22/2023
 */

actual val openSansFontFamily: FontFamily = FontFamily(
    Font(resource = "font/open_sans_regular.ttf", weight = FontWeight.Normal),
    Font(resource = "font/open_sans_bold.ttf", weight = FontWeight.Bold),
    Font(resource = "font/open_sans_semi_bold.ttf", weight = FontWeight.SemiBold),
    Font(resource = "font/open_sans_extra_bold.ttf", weight = FontWeight.ExtraBold),
    Font(resource = "font/open_sans_medium.ttf", weight = FontWeight.Medium),
)
