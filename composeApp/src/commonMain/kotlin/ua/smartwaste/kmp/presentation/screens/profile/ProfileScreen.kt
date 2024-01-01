package ua.smartwaste.kmp.presentation.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import ua.smartwaste.kmp.model.Quest
import ua.smartwaste.kmp.presentation.core.ResourceType
import ua.smartwaste.kmp.presentation.core.painterDrawableResource
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
fun ProfileScreenContent(
    state: ProfileState,
    sendEvent: (ProfileEvent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SmartTheme.palette.background),
    ) {
        UserSection(
            modifier = Modifier.padding(
                vertical = SmartTheme.offset.height.large,
                horizontal = SmartTheme.offset.width.large,
            ),
            username = state.username,
            email = state.email,
        )
        Row(
            modifier = Modifier
                .padding(horizontal = SmartTheme.offset.width.large)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Level up",
                style = SmartTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Normal,
                ),
                color = SmartTheme.palette.onBackground,
            )
            Text(
                text = "${state.currentProgress}/${state.requiredProgress}",
                style = SmartTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Normal,
                ),
                color = SmartTheme.palette.onBackground,
            )
        }
        LevelProgress(
            modifier = Modifier.padding(
                top = SmartTheme.offset.height.tiny,
                start = SmartTheme.offset.width.large,
                end = SmartTheme.offset.width.large,
            ),
            progress = state.passedProgress,
        )
        Column(
            modifier = Modifier
                .padding(top = SmartTheme.offset.height.huge)
                .fillMaxSize()
                .background(
                    color = SmartTheme.palette.primary,
                    shape = RoundedCornerShape(
                        topStart = 40.dp,
                        topEnd = 40.dp,
                        bottomEnd = 0.dp,
                        bottomStart = 0.dp,
                    ),
                ),
        ) {
            Row(
                modifier = Modifier
                    .padding(
                        vertical = SmartTheme.offset.height.large,
                        horizontal = SmartTheme.offset.width.huge,
                    )
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                UserInfoCard(
                    value = state.bucketsCount,
                    label = "Buckets",
                )
                UserInfoCard(
                    value = state.level,
                    label = "Level",
                )
                UserInfoCard(
                    value = state.daysCount,
                    label = "Days",
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = SmartTheme.palette.surface,
                        shape = RoundedCornerShape(
                            topStart = 40.dp,
                            topEnd = 40.dp,
                            bottomEnd = 0.dp,
                            bottomStart = 0.dp,
                        ),
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    modifier = Modifier.padding(
                        top = SmartTheme.offset.height.medium,
                    ),
                    text = "Quests",
                    style = SmartTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                    ),
                    color = SmartTheme.palette.onSurface,
                )
                QuestsList(
                    modifier = Modifier
                        .padding(
                            top = SmartTheme.offset.height.regular,
                            start = SmartTheme.offset.width.huge,
                            end = SmartTheme.offset.width.huge,
                        )
                        .fillMaxSize(),
                    quests = state.quests,
                )
            }
        }
    }
}

@Composable
fun UserInfoCard(
    modifier: Modifier = Modifier,
    value: Int,
    label: String,
) {
    Column(
        modifier = modifier
            .size(SmartTheme.dimension.profile.userInfoCardSize)
            .background(
                color = Color.White.copy(alpha = 0.3f),
                shape = SmartTheme.shape.large,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = value.toString(),
            style = SmartTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
            ),
            color = SmartTheme.palette.onPrimary,
        )
        Text(
            text = label,
            style = SmartTheme.typography.labelNormal.copy(
                fontWeight = FontWeight.Normal,
            ),
            color = SmartTheme.palette.onPrimary,
        )
    }
}

@Composable
private fun UserSection(
    modifier: Modifier = Modifier,
    username: String,
    email: String,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier
                .size(SmartTheme.dimension.profile.profileImage)
                .clip(shape = SmartTheme.shape.round)
                .background(
                    color = SmartTheme.palette.surface,
                    shape = SmartTheme.shape.medium,
                )
                .padding(SmartTheme.offset.height.medium),
            painter = painterDrawableResource(
                id = "ic_profile",
                type = ResourceType.XML,
            ),
            tint = SmartTheme.palette.onSurface,
            contentDescription = null,
        )
        Column(
            modifier = Modifier.padding(
                start = SmartTheme.offset.width.regular,
            ),
        ) {
            Text(
                text = username,
                style = SmartTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                ),
                color = SmartTheme.palette.onBackground,
            )
            Text(
                modifier = Modifier.padding(top = SmartTheme.offset.height.small),
                text = email,
                style = SmartTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Normal,
                ),
                color = SmartTheme.palette.onBackground,
                textDecoration = TextDecoration.Underline,
            )
        }
    }
}

@Composable
private fun LevelProgress(
    modifier: Modifier = Modifier,
    progress: Float = 0f,
) {
    val primaryColor = SmartTheme.palette.primary
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(SmartTheme.dimension.profile.levelProgressHeight)
            .background(
                color = SmartTheme.palette.surface,
                shape = SmartTheme.shape.large,
            )
            .drawBehind {
                drawRoundRect(
                    color = primaryColor,
                    size = size.copy(
                        width = size.width * progress,
                    ),
                    cornerRadius = CornerRadius(40.dp.toPx()),
                )
            },
    )
}

@Composable
private fun QuestsList(
    modifier: Modifier = Modifier,
    quests: ImmutableList<Quest> = persistentListOf(),
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            SmartTheme.offset.height.regular,
        ),
    ) {
        items(
            items = quests,
            key = { it.id },
        ) { quest ->
            QuestItem(quest = quest)
        }
    }
}

@Composable
private fun QuestItem(
    modifier: Modifier = Modifier,
    quest: Quest,
) {
    val background = if (quest.currentProgress >= quest.requiredProgress) {
        SmartTheme.palette.primary
    } else {
        SmartTheme.palette.secondary
    }
    Box(
        modifier = modifier
            .height(
                SmartTheme.dimension.profile.questItemHeight
            )
            .fillMaxWidth()
            .background(
                color = background,
                shape = SmartTheme.shape.large,
            ),
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = SmartTheme.offset.width.medium),
            text = quest.title,
            style = SmartTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold,
            ),
            color = SmartTheme.palette.onPrimary,
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = SmartTheme.offset.width.medium),
            text = "${quest.currentProgress}/${quest.requiredProgress}",
            style = SmartTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold,
            ),
            color = SmartTheme.palette.onPrimary,
        )
    }
}
