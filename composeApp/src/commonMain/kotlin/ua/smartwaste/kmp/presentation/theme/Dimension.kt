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
    val bucket: BucketDimension = BucketDimension(),
    val components: ComponentsDimension = ComponentsDimension(),
) {
    @Immutable
    data class SplashDimension(val logo: Dp = 50.dp)

    @Immutable
    data class LoginDimension(
        val maxLoginScreenWidth: Dp = 330.dp,
        val topLogoSize: Dp = 20.dp,
        val fieldHeight: Dp = 40.dp,
        val buttonHeight: Dp = 44.dp,
    )

    @Immutable
    data class ProfileDimension(
        val profileImage: Dp = 90.dp,
        val levelProgressHeight: Dp = 8.dp,
        val userInfoCardSize: Dp = 62.dp,
        val questItemHeight: Dp = 40.dp
    )

    @Immutable
    data class ComponentsDimension(
        val topBarHeight: Dp = 46.dp,
        val topBarImageSize: Dp = 26.dp,
    )

    @Immutable
    data class BucketDimension(
        val rubbishItemCountButtonSize: Dp = 40.dp,
        val rubbishItemTextHeight: Dp = 32.dp,
        val addRubbishPopupHeight: Dp = 290.dp,
        val counterHeight: Dp = 44.dp,
        val addButtonHeight: Dp = 44.dp,
        val cancelButtonHeight: Dp = 44.dp,
        val counterButtonSize: Dp = 32.dp,
        val counterTextWidth: Dp = 26.dp,
        val selectRubbishButtonHeight: Dp = 44.dp,
        val arrowNextIconSize: Dp = 18.dp,
        val recyclerPointsButtonHeight: Dp = 44.dp,
    )
}

fun buildDimension(
    windowSizeClass: WindowSizeClass,
): Dimension {
    return Dimension(
        splash = buildSplashDimension(windowSizeClass),
        login = buildLoginDimension(windowSizeClass),
        profile = buildProfileDimension(windowSizeClass),
        bucket = buildBucketDimension(windowSizeClass),
        components = buildComponentsDimension(windowSizeClass),
    )
}

private fun buildBucketDimension(
    windowSizeClass: WindowSizeClass,
): Dimension.BucketDimension = with(windowSizeClass) {
    return Dimension.BucketDimension(
        rubbishItemCountButtonSize = buildGeneralDimension(40.dp.value),
        rubbishItemTextHeight = buildHeightDimension(32.dp.value),
        addRubbishPopupHeight = buildHeightDimension(290.dp.value),
        counterHeight = buildHeightDimension(44.dp.value),
        addButtonHeight = buildHeightDimension(44.dp.value),
        cancelButtonHeight = buildHeightDimension(44.dp.value),
        counterButtonSize = buildGeneralDimension(32.dp.value),
        counterTextWidth = buildWidthDimension(32.dp.value),
        selectRubbishButtonHeight = buildHeightDimension(44.dp.value),
        arrowNextIconSize = buildGeneralDimension(18.dp.value),
        recyclerPointsButtonHeight = buildHeightDimension(44.dp.value),
    )
}

private fun buildComponentsDimension(
    windowSizeClass: WindowSizeClass,
): Dimension.ComponentsDimension = with(windowSizeClass) {
    return Dimension.ComponentsDimension(
        topBarHeight = buildHeightDimension(46.dp.value),
        topBarImageSize = buildGeneralDimension(26.dp.value),
    )
}

private fun buildLoginDimension(
    windowSizeClass: WindowSizeClass,
): Dimension.LoginDimension = with(windowSizeClass) {
    return Dimension.LoginDimension(
        maxLoginScreenWidth = buildWidthDimension(330.dp.value),
        topLogoSize = buildHeightDimension(20.dp.value),
        fieldHeight = buildHeightDimension(44.dp.value),
        buttonHeight = buildHeightDimension(44.dp.value),
    )
}

private fun buildProfileDimension(
    windowSizeClass: WindowSizeClass,
): Dimension.ProfileDimension = with(windowSizeClass) {
    return Dimension.ProfileDimension(
        profileImage = buildGeneralDimension(90.dp.value),
        levelProgressHeight = buildHeightDimension(8.dp.value),
        userInfoCardSize = buildGeneralDimension(62.dp.value),
        questItemHeight = buildHeightDimension(40.dp.value),
    )
}

private fun buildSplashDimension(
    windowSizeClass: WindowSizeClass,
): Dimension.SplashDimension = with(windowSizeClass) {
    return Dimension.SplashDimension(
        logo = buildGeneralDimension(50.dp.value),
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
