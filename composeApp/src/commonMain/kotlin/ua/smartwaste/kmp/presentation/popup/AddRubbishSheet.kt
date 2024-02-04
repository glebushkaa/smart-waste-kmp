package ua.smartwaste.kmp.presentation.popup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

@Composable
fun AddRubbishSheet() {
    val scope = rememberCoroutineScope()
    var count by remember { mutableIntStateOf(INITIAL_COUNT) }
}

private const val INITIAL_COUNT = 0
