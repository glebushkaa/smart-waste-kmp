package ua.smartwaste.kmp.presentation.screens.bucket

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ua.smartwaste.kmp.domain.usecase.items.GetAvailableRubbishesUseCase

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

class BucketScreenModel(
    private val getAvailableRubbishesUseCase: GetAvailableRubbishesUseCase,
) : StateScreenModel<BucketState>(BucketState()) {

    init {
        getAvailableRubbishes()
    }

    fun sendEvent(event: BucketEvent) {
        when (event) {
            BucketEvent.HideAddRubbishPopup -> {
                mutableState.update { it.copy(rubbishPopupVisible = false) }
            }

            BucketEvent.ShowAddRubbishPopup -> {
                mutableState.update { it.copy(rubbishPopupVisible = true) }
            }
        }
    }

    private fun getAvailableRubbishes() = screenModelScope.launch {
        val result = getAvailableRubbishesUseCase().getOrDefault(persistentListOf())
        mutableState.update { it.copy(availableRubbishList = result) }
    }
}
