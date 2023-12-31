package ua.smartwaste.kmp.presentation.screens.bucket

import app.cash.sqldelight.coroutines.asFlow
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import smartwaste.rubbish.SmartDatabase
import ua.smartwaste.kmp.domain.usecase.items.GetAvailableRubbishesUseCase
import ua.smartwaste.kmp.model.Rubbish

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

class BucketScreenModel(
    private val getAvailableRubbishesUseCase: GetAvailableRubbishesUseCase,
    private val smartDatabase: SmartDatabase,
) : StateScreenModel<BucketState>(BucketState()) {

    init {
        insertRubbishes()
        getAvailableRubbishes()
        collectRubbishes()
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

    private fun insertRubbishes() = screenModelScope.launch(Dispatchers.IO) {
        repeat(10) {
            smartDatabase.rubbishQueries.insertRubbish(
                it.toLong(),
                "Rubbish $it",
                it.toLong(),
            )
        }
    }

    private fun collectRubbishes() = screenModelScope.launch(Dispatchers.IO) {
        smartDatabase.rubbishQueries.getAllRubbishes()
            .asFlow()
            .map {
                it.executeAsList().map {
                    Rubbish(
                        id = it.id,
                        name = it.name,
                        count = it.count.toInt(),
                    )
                }
            }
            .collectLatest { list ->
                mutableState.update {
                    val immutableList = list.toImmutableList()
                    it.copy(selectedRubbishList = immutableList)
                }
            }
    }

    private fun getAvailableRubbishes() = screenModelScope.launch {
        val result = getAvailableRubbishesUseCase().getOrDefault(persistentListOf())
        mutableState.update { it.copy(availableRubbishList = result) }
    }
}
