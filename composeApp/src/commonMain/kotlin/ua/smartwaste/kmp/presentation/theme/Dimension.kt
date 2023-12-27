package ua.smartwaste.kmp.presentation.theme

import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/24/2023
 */

@Immutable
data class Dimension(
    val splash: SplashDimension = SplashDimension(),
    val login: LoginDimension = LoginDimension(),
    val profile: ProfileDimension = ProfileDimension(),
    val components: ComponentsDimension = ComponentsDimension(),
) {
    @Immutable
    data class SplashDimension(val logo: Dp = 60.dp)

    @Immutable
    data class LoginDimension(
        val maxLoginScreenWidth: Dp = 400.dp,
        val topLogoSize: Dp = 24.dp,
        val fieldHeight: Dp = 50.dp,
        val buttonHeight: Dp = 54.dp,
    )

    @Immutable
    data class ProfileDimension(
        val profileImage: Dp = 110.dp,
        val levelProgressHeight: Dp = 10.dp,
        val userInfoCardSize: Dp = 76.dp,
    )

    @Immutable
    data class ComponentsDimension(
        val topBarHeight: Dp = 56.dp,
        val topBarImageSize: Dp = 32.dp,
    )
}

fun buildDimension(
    windowSizeClass: WindowSizeClass,
): Dimension {
    return Dimension(
        splash = buildSplashDimension(windowSizeClass),
        login = buildLoginDimension(windowSizeClass),
        profile = buildProfileDimension(windowSizeClass),
        components = buildComponentsDimension(windowSizeClass),
    )
}

private fun buildComponentsDimension(
    windowSizeClass: WindowSizeClass,
): Dimension.ComponentsDimension = with(windowSizeClass) {
    return Dimension.ComponentsDimension(
        topBarHeight = buildHeightDimension(56.dp.value),
        topBarImageSize = buildGeneralDimension(32.dp.value),
    )
}

private fun buildLoginDimension(
    windowSizeClass: WindowSizeClass,
): Dimension.LoginDimension = with(windowSizeClass) {
    return Dimension.LoginDimension(
        maxLoginScreenWidth = buildWidthDimension(400.dp.value),
        topLogoSize = buildHeightDimension(24.dp.value),
        fieldHeight = buildHeightDimension(50.dp.value),
        buttonHeight = buildHeightDimension(54.dp.value),
    )
}

private fun buildProfileDimension(
    windowSizeClass: WindowSizeClass,
): Dimension.ProfileDimension = with(windowSizeClass) {
    return Dimension.ProfileDimension(
        profileImage = buildGeneralDimension(110.dp.value),
        levelProgressHeight = buildHeightDimension(10.dp.value),
        userInfoCardSize = buildGeneralDimension(76.dp.value),
    )
}

private fun buildSplashDimension(
    windowSizeClass: WindowSizeClass,
): Dimension.SplashDimension = with(windowSizeClass) {
    return Dimension.SplashDimension(
        logo = buildGeneralDimension(60.dp.value),
    )
}

private fun WindowSizeClass.buildWidthDimension(compactSize: Float): Dp {
    return when (this.widthSizeClass) {
        WindowWidthSizeClass.Compact -> compactSize
        WindowWidthSizeClass.Medium -> compactSize * 1.2f
        WindowWidthSizeClass.Expanded -> compactSize * 1.4f
        else -> compactSize
    }.dp
}

private fun WindowSizeClass.buildHeightDimension(compactSize: Float): Dp {
    return when (this.heightSizeClass) {
        WindowHeightSizeClass.Compact -> compactSize
        WindowHeightSizeClass.Medium -> compactSize * 1.2f
        WindowHeightSizeClass.Expanded -> compactSize * 1.4f
        else -> compactSize
    }.dp
}

private fun WindowSizeClass.buildGeneralDimension(compactWidth: Float): Dp {
    val heightSize = buildHeightDimension(compactWidth)
    val widthSize = buildWidthDimension(compactWidth)
    return minOf(heightSize, widthSize)
}
