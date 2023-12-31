package ua.smartwaste.kmp.presentation.screens.bucket

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ua.smartwaste.kmp.domain.usecase.items.AddRubbishUseCase
import ua.smartwaste.kmp.domain.usecase.items.GetAllRubbishesFlowUseCase
import ua.smartwaste.kmp.domain.usecase.items.GetAvailableRubbishesUseCase
import ua.smartwaste.kmp.domain.usecase.items.UpdateRubbishCountUseCase
import ua.smartwaste.kmp.log.error
import ua.smartwaste.kmp.log.info

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

class BucketScreenModel(
    private val getAvailableRubbishesUseCase: GetAvailableRubbishesUseCase,
    private val getAllRubbishesFlowUseCase: GetAllRubbishesFlowUseCase,
    private val addRubbishUseCase: AddRubbishUseCase,
    private val updateRubbishCountUseCase: UpdateRubbishCountUseCase
) : StateScreenModel<BucketState>(BucketState()) {

    init {
        collectAllRubbishes()
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

            is BucketEvent.AddRubbish -> {
                addRubbish(id = event.id, count = event.count)
            }

            is BucketEvent.DecreaseCount -> {
                decreaseCount(id = event.id)
            }

            is BucketEvent.IncreaseCount -> {
                increaseCount(id = event.id)
            }
        }
    }

    private fun increaseCount(id: Long) = screenModelScope.launch {
        val count = state.value.selectedRubbishList.find { rubbish ->
            rubbish.id == id
        }?.count ?: run {
            error(TAG) { "No rubbish with provided id was found" }
            return@launch
        }
        val params = UpdateRubbishCountUseCase.Params(id = id, count = count + 1)
        updateRubbishCountUseCase(params)
    }

    private fun decreaseCount(id: Long) = screenModelScope.launch {
        val count = state.value.selectedRubbishList.find { rubbish ->
            rubbish.id == id
        }?.count ?: run {
            error(TAG) { "No rubbish with provided id was found" }
            return@launch
        }
        val params = UpdateRubbishCountUseCase.Params(id = id, count = count - 1)
        updateRubbishCountUseCase(params)
    }

    private fun addRubbish(id: Long, count: Int) = screenModelScope.launch {
        val rubbish = state.value.availableRubbishList.find { it.id == id } ?: run {
            error(TAG) { "No rubbish with provided id was found" }
            return@launch
        }
        val params = AddRubbishUseCase.Params(rubbish = rubbish.copy(count = count))
        addRubbishUseCase(params)
    }

    private fun collectAllRubbishes() = screenModelScope.launch {
        getAllRubbishesFlowUseCase().getOrNull()?.collect { list ->
            mutableState.update {
                it.copy(selectedRubbishList = list)
            }
        }
    }

    private fun getAvailableRubbishes() = screenModelScope.launch {
        val result = getAvailableRubbishesUseCase().getOrDefault(persistentListOf())
        mutableState.update {
            it.copy(availableRubbishList = result)
        }
    }

    private companion object {
        private const val TAG = "BucketScreenModel"
    }
}
