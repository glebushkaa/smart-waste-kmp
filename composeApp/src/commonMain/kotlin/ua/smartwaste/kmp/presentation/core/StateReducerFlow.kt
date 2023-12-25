package ua.smartwaste.kmp.presentation.core

import kotlinx.coroutines.flow.StateFlow

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

interface StateReducerFlow<State, Event> {

    val state: StateFlow<State>

    fun sendEvent(event: Event)
}
