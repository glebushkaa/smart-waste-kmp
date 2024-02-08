package ua.gleb.smartwaste.database.quest.entity

data class QuestEntity(
    val id: Long? = null,
    val name: String? = null,
    val requiredProgress: Int? = null,
)