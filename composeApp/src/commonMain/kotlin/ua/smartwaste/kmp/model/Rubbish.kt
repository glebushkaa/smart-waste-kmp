package ua.smartwaste.kmp.model

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

data class Rubbish(
    val id: Long,
    val name: String,
    val count: Int = 0,
    val limit: Int = 10,
    val categories: ImmutableList<Category> = persistentListOf(),
) {
    data class Category(
        val id: Long,
        val name: String,
        val slug: String,
        val icon: String,
    )
}
