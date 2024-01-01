@file:OptIn(InternalVoyagerApi::class)

package ua.smartwaste.kmp.presentation.core

import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.core.concurrent.PlatformMainDispatcher
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.ScreenModelStore
import ua.smartwaste.kmp.log.error
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/31/2023.
 */


val ScreenModel.modelScope: CoroutineScope
    get() = ScreenModelStore.getOrPutDependency(
        screenModel = this,
        name = "ScreenModelCoroutineScope",
        factory = { key ->
            val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
                error("modelScope", throwable)
            }
            val scope = SupervisorJob() +
                    PlatformMainDispatcher +
                    CoroutineName(key) +
                    coroutineExceptionHandler
            CoroutineScope(scope)
        },
        onDispose = { scope -> scope.cancel() }
    )