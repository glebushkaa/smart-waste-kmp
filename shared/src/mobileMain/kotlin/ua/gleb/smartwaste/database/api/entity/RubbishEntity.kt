package ua.gleb.smartwaste.database.api.entity

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/31/2023
 */

data class RubbishEntity(
    val id: Long,
    val name: String,
    val count: Int,
    val emoji: String
)