@file:OptIn(ExperimentalResourceApi::class, ExperimentalResourceApi::class)

package ua.smartwaste.kmp.presentation.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/24/2023
 */

enum class ResourceType(val format: String) {
    PNG(".png"), XML(".xml")
}

@Composable
fun painterDrawableResource(
    id: String,
    type: ResourceType = ResourceType.PNG,
): Painter {
    val path = "drawable/$id${type.format}"
    return painterResource(path)
}
