package ua.gleb.smartwaste.domain.repository

import ua.gleb.smartwaste.model.Quest

interface QuestRepository {
    suspend fun addNewQuest(name: String, requiredProgress: Int): Result<Unit>

    suspend fun getAllQuests(): List<Quest>

    suspend fun updateUserQuestProgress(
        questId: Long,
        userId: String,
        progress: Int
    )
}