package ua.smartwaste.kmp.presentation.screens.bucket

import cafe.adriel.voyager.core.model.StateScreenModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ua.smartwaste.kmp.domain.usecase.items.AddRubbishUseCase
import ua.smartwaste.kmp.domain.usecase.items.GetAllRubbishesFlowUseCase
import ua.smartwaste.kmp.domain.usecase.items.GetAvailableRubbishesUseCase
import ua.smartwaste.kmp.domain.usecase.items.UpdateRubbishCountUseCase
import ua.smartwaste.kmp.log.error
import ua.smartwaste.kmp.model.Rubbish
import ua.smartwaste.kmp.presentation.core.modelScope

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
            BucketEvent.HideRubbishPopup -> {
                changeRubbishPopupVisibility(false)
            }

            BucketEvent.ShowAddRubbishPopup -> {
                changeRubbishPopupVisibility(true)
            }

            is BucketEvent.AddRubbish -> {
                val popupState = mutableState.value.rubbishPopupState
                addRubbish(id = popupState.rubbishId, count = popupState.count)
            }

            is BucketEvent.DecreaseCount -> {
                decreaseCount(id = event.id)
            }

            is BucketEvent.IncreaseCount -> {
                increaseCount(id = event.id)
            }

            is BucketEvent.ChangeRubbishPopupMode -> {
                mutableState.update {
                    val popupState = it.rubbishPopupState.copy(mode = event.mode)
                    it.copy(rubbishPopupState = popupState)
                }
            }

            BucketEvent.DecreaseRubbishPopupCount -> decreaseRubbishPopupCount()
            BucketEvent.IncreaseRubbishPopupCount -> increaseRubbishPopupCount()
            BucketEvent.ScanClicked -> {
                // TODO
            }

            is BucketEvent.SelectRubbish -> selectRubbish(event.id)
        }
    }

    private fun selectRubbish(id: Long) {
        val list = mutableState.value.rubbishPopupState.rubbishesList
        val rubbish = list.findRubbishById(id)
        val popupState = mutableState.value.rubbishPopupState.copy(
            rubbishId = rubbish.id,
            rubbishName = rubbish.name
        )
        mutableState.update { state ->
            state.copy(rubbishPopupState = popupState)
        }
    }

    private fun decreaseRubbishPopupCount() = mutableState.update { state ->
        val popupState = state.rubbishPopupState
        val count = popupState.count.minus(1).coerceAtLeast(MIN_COUNT)
        val newPopupState = popupState.copy(count = count)
        state.copy(rubbishPopupState = newPopupState)
    }

    private fun increaseRubbishPopupCount() = mutableState.update { state ->
        val popupState = state.rubbishPopupState
        val count = popupState.count.plus(1).coerceAtMost(MAX_COUNT)
        val newPopupState = popupState.copy(count = count)
        state.copy(rubbishPopupState = newPopupState)
    }

    private fun changeRubbishPopupVisibility(visible: Boolean) = mutableState.update {
        val popupState = it.rubbishPopupState.copy(visible = visible)
        it.copy(rubbishPopupState = popupState)
    }

    private fun increaseCount(id: Long) = modelScope.launch {
        val list = state.value.selectedRubbishes
        val rubbish = list.findRubbishById(id)
        val params = UpdateRubbishCountUseCase.Params(id = id, count = rubbish.count + 1)
        updateRubbishCountUseCase(params)
    }

    private fun decreaseCount(id: Long) = modelScope.launch {
        val list = state.value.selectedRubbishes
        val rubbish = list.findRubbishById(id)
        val params = UpdateRubbishCountUseCase.Params(id = id, count = rubbish.count - 1)
        updateRubbishCountUseCase(params)
    }

    private fun addRubbish(id: Long, count: Int) = modelScope.launch {
        val list = state.value.rubbishPopupState.rubbishesList
        val rubbish = list.findRubbishById(id)
        val params = AddRubbishUseCase.Params(rubbish = rubbish.copy(count = count))
        addRubbishUseCase(params)
    }

    private fun List<Rubbish>.findRubbishById(id: Long): Rubbish {
        return find { it.id == id } ?: run {
            throw Exception("No rubbish with provided id was found")
        }
    }

    private fun collectAllRubbishes() = modelScope.launch {
        getAllRubbishesFlowUseCase().getOrNull()?.collect { list ->
            mutableState.update {
                it.copy(selectedRubbishes = list)
            }
        }
    }

    private fun getAvailableRubbishes() = modelScope.launch {
        val list = getAvailableRubbishesUseCase().getOrDefault(persistentListOf())
        val popupState = mutableState.value.rubbishPopupState.copy(
            rubbishesList = list
        )
        mutableState.update { it.copy(rubbishPopupState = popupState) }
    }

    private companion object {
        private const val MIN_COUNT = 0
        private const val MAX_COUNT = 10
    }
}
