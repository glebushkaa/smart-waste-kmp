package ua.smartwaste.kmp.presentation.core

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

class StateReducerFlowImpl<State, Event>(
    private val scope: CoroutineScope,
    private val initialState: State,
    private val reduceState: (State, Event) -> State,
) : StateReducerFlow<State, Event> {

    private val events = Channel<Event>()
    override val state: StateFlow<State>
        get() = events.receiveAsFlow()
            .buffer(Channel.UNLIMITED)
            .map { event -> reduceState(initialState, event) }
            .stateIn(scope, SharingStarted.Eagerly, initialState)

    override fun sendEvent(event: Event) {
        events.trySend(event)
    }
}

fun <State, Event> ScreenModel.createStateReducerFlow(
    initialState: State,
    reduceState: (State, Event) -> State,
): StateReducerFlow<State, Event> {
    return StateReducerFlowImpl(screenModelScope, initialState, reduceState)
}
