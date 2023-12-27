package ua.smartwaste.kmp.presentation.screens.profile

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ua.smartwaste.kmp.domain.usecase.auth.LogOutUseCase
import ua.smartwaste.kmp.domain.usecase.user.GetQuestsUseCase
import ua.smartwaste.kmp.domain.usecase.user.GetUserUseCase

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

class ProfileScreenModel(
    private val getUserUseCase: GetUserUseCase,
    private val getQuestsUseCase: GetQuestsUseCase,
    private val logOutUseCase: LogOutUseCase,
) : StateScreenModel<ProfileState>(emptyProfileState) {

    private val _navigationEvent = Channel<ProfileNavigationEvent>()
    val navigationEvent = _navigationEvent.receiveAsFlow()

    init {
        getUser()
        getQuests()
    }

    private fun getQuests() = screenModelScope.launch {
        val quests = getQuestsUseCase().getOrNull() ?: return@launch
        mutableState.update {
            it.copy(quests = quests.toImmutableList())
        }
    }

    private fun getUser() = screenModelScope.launch {
        val user = getUserUseCase().getOrNull() ?: return@launch
        mutableState.update {
            it.copy(
                username = user.username,
                email = user.email,
                passedProgress = user.currentProgress / user.requiredProgress.toFloat(),
                requiredProgress = user.requiredProgress,
                currentProgress = user.currentProgress,
                level = user.level,
            )
        }
    }

    private fun logOut() = screenModelScope.launch {
        logOutUseCase()
        _navigationEvent.trySend(ProfileNavigationEvent.NavigateToLogin)
    }

    fun sendEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.LogOut -> logOut()
        }
    }
}
