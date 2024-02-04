package ua.smartwaste.kmp.presentation.screens.profile

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import ua.gleb.smartwaste.model.Quest

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

data class ProfileState(
    val username: String,
    val email: String,
    val passedProgress: Float,
    val requiredProgress: Int,
    val currentProgress: Int,
    val level: Int,
    val bucketsCount: Int,
    val daysCount: Int,
    val quests: ImmutableList<Quest> = persistentListOf(),
)

val emptyProfileState = ProfileState(
    username = "",
    email = "",
    passedProgress = 0f,
    requiredProgress = 0,
    currentProgress = 0,
    level = 0,
    bucketsCount = 0,
    daysCount = 0,
)
