package ua.smartwaste.kmp.model

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

data class Quest(
    val id: Long,
    val title: String,
    val requiredProgress: Int,
    val currentProgress: Int,
)
