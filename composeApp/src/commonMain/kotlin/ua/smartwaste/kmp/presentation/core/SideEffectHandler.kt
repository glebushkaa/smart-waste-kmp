package ua.smartwaste.kmp.presentation.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 1/1/2024
 */

@Composable
inline fun <reified S> SideEffectHandler(
    sideEffect: S?,
    crossinline action: (S) -> Unit,
    crossinline clearEffect: () -> Unit
) {
    LaunchedEffect(sideEffect) {
        sideEffect ?: return@LaunchedEffect
        action(sideEffect)
        clearEffect()
    }
}