package ua.smartwaste.kmp.presentation.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import ua.smartwaste.kmp.R

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 11/22/2023
 */

actual val openSansFontFamily: FontFamily = FontFamily(
    Font(R.font.open_sans_regular, FontWeight.Normal),
    Font(R.font.open_sans_bold, FontWeight.Bold),
    Font(R.font.open_sans_semi_bold, FontWeight.SemiBold),
    Font(R.font.open_sans_extra_bold, FontWeight.ExtraBold),
    Font(R.font.open_sans_medium, FontWeight.Medium),
)
