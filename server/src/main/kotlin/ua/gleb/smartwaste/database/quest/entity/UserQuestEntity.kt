package ua.gleb.smartwaste.database.quest.entity

data class UserQuestEntity(
    val id: Long? = null,
    val name: String? = null,
    val progress: Int? = null,
    val requiredProgress: Int? = null,
)