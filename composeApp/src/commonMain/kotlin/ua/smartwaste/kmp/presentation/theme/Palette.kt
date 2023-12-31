package ua.smartwaste.kmp.presentation.theme

import androidx.compose.ui.graphics.Color

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/24/2023
 */

data class Palette(
    val primary: Color = Green,
    val secondary: Color = Color.Green,
    val tertiary: Color = Color.Red,
    val background: Color = BackgroundWhite,
    val surface: Color = Color.White,
    val error: Color = Color.Red,
    val onPrimary: Color = Color.White,
    val onSecondary: Color = Color.White,
    val onTertiary: Color = Color.White,
    val onBackground: Color = Color.Black,
    val onSurface: Color = Color.Black,
    val onError: Color = Color.Red,
    val link: Color = Color.Blue,
)

val darkPalette = Palette(
    primary = Green,
    secondary = Yellow,
    tertiary = DarkRed,
    background = DarkGrey,
    surface = NewSurfaceBlack,
    error = ErrorRed,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color.White,
    onSurface = NewOnSurfaceBlack,
    onError = BackgroundWhite,
    link = LinkBlue,
)

val lightPalette = Palette(
    primary = Green,
    secondary = Yellow,
    tertiary = Color.Red,
    background = BackgroundWhite,
    surface = Color.White,
    error = ErrorRed,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color.Black,
    onSurface = NewSurfaceBlack,
    onError = BackgroundWhite,
    link = LinkBlue,
)
