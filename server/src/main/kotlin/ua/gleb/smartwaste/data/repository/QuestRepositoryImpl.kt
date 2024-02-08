package ua.gleb.smartwaste.data.repository

import ua.gleb.smartwaste.data.mapper.toQuest
import ua.gleb.smartwaste.database.quest.QuestDao
import ua.gleb.smartwaste.domain.repository.QuestRepository
import ua.gleb.smartwaste.model.Quest
import java.util.*

class QuestRepositoryImpl(
    private val questDao: QuestDao
) : QuestRepository {

    /**
     * TODO Later add new logic for processing result
     */
    override suspend fun addNewQuest(name: String, requiredProgress: Int): Result<Unit> {
        questDao.insertQuest(name, requiredProgress)
        return Result.success(Unit)
    }

    override suspend fun getAllQuests(): List<Quest> {
        return questDao.getQuests().map {
            it.toQuest()
        }
    }

    override suspend fun updateUserQuestProgress(questId: Long, userId: String, progress: Int) {
        val uuid = UUID.fromString(userId)
        questDao.insertUserQuest(questId, uuid, progress)
    }
}