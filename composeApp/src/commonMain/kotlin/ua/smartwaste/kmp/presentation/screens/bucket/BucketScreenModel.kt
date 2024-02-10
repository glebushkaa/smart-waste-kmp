package ua.smartwaste.kmp.presentation.screens.bucket

import cafe.adriel.voyager.core.model.StateScreenModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ua.gleb.smartwaste.domain.usecase.items.AddRubbishUseCase
import ua.gleb.smartwaste.domain.usecase.items.GetAllRubbishesFlowUseCase
import ua.gleb.smartwaste.domain.usecase.items.GetAvailableRubbishesUseCase
import ua.gleb.smartwaste.domain.usecase.items.ScanItemUseCase
import ua.gleb.smartwaste.domain.usecase.items.UpdateRubbishCountUseCase
import ua.gleb.smartwaste.model.Rubbish
import ua.smartwaste.kmp.presentation.core.modelScope

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

class BucketScreenModel(
    private val getAvailableRubbishesUseCase: GetAvailableRubbishesUseCase,
    private val getAllRubbishesFlowUseCase: GetAllRubbishesFlowUseCase,
    private val addRubbishUseCase: AddRubbishUseCase,
    private val updateRubbishCountUseCase: UpdateRubbishCountUseCase,
    private val scanItemUseCase: ScanItemUseCase
) : StateScreenModel<BucketState>(BucketState()) {

    private val _sideEffect = Channel<BucketSideEffect?>()
    val sideEffect = _sideEffect.receiveAsFlow()

    init {
        collectAllRubbishes()
        getAvailableRubbishes()
    }

    fun sendEvent(event: BucketEvent) {
        when (event) {
            is BucketEvent.AddRubbish -> {
                val popupState = mutableState.value.rubbishPopupState
                addRubbish(id = popupState.rubbishId, count = popupState.count)
            }

            BucketEvent.HideRubbishPopup -> changeRubbishPopupVisibility(false)
            BucketEvent.ShowAddRubbishPopup -> changeRubbishPopupVisibility(true)
            is BucketEvent.DecreaseCount -> decreaseCount(id = event.id)
            is BucketEvent.IncreaseCount -> increaseCount(id = event.id)
            is BucketEvent.ChangeRubbishPopupMode -> changePopupMode(event.mode)
            BucketEvent.DecreaseRubbishPopupCount -> decreaseRubbishPopupCount()
            BucketEvent.IncreaseRubbishPopupCount -> increaseRubbishPopupCount()
            BucketEvent.ScanClicked -> _sideEffect.trySend(BucketSideEffect.ScanRubbish)
            is BucketEvent.SelectRubbish -> selectRubbish(event.id)
            is BucketEvent.ProcessScannedItemPath -> scanImage(event.path)
            is BucketEvent.SendToast -> sendToast(event.message)
            BucketEvent.ClearSideEffect -> _sideEffect.trySend(null)
        }
    }

    private fun sendToast(message: String) {
        val effect = BucketSideEffect.Toast(message)
        _sideEffect.trySend(effect)
    }

    private fun changePopupMode(mode: RubbishPopupMode) = mutableState.update {
        val popupState = it.rubbishPopupState.copy(mode = mode)
        it.copy(rubbishPopupState = popupState)
    }

    private fun scanImage(path: String) = modelScope.launch {
        changeLoaderVisibility(true)
        val params = ScanItemUseCase.Params(path)
        val rubbish = scanItemUseCase(params).getOrNull()
        changeLoaderVisibility(false)
        if (rubbish == null) {
            val effect = BucketSideEffect.Toast("Item was not recognized")
            _sideEffect.send(effect)
            return@launch
        }
        selectRubbish(rubbish)
    }

    private fun changeLoaderVisibility(visible: Boolean) = mutableState.update {
        it.copy(loaderVisible = visible)
    }

    private fun selectRubbish(rubbish: Rubbish) {
        val popupState = mutableState.value.rubbishPopupState.copy(
            rubbishId = rubbish.id,
            rubbishName = rubbish.title
        )
        mutableState.update { state ->
            state.copy(rubbishPopupState = popupState)
        }
    }

    private fun selectRubbish(id: Long) {
        val list = mutableState.value.rubbishPopupState.rubbishesList
        val rubbish = list.findRubbishById(id)
        selectRubbish(rubbish)
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
