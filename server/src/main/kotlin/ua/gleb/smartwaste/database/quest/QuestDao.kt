package ua.gleb.smartwaste.database.quest

import ua.gleb.smartwaste.database.quest.entity.QuestEntity
import ua.gleb.smartwaste.database.quest.entity.UserQuestEntity
import java.util.*

interface QuestDao {

    suspend fun getAllQuests(): List<QuestEntity>

    suspend fun getUserQuests(id: UUID): List<UserQuestEntity>

    suspend fun getQuests(): List<QuestEntity>

    suspend fun insertQuest(name: String, requiredProgress: Int)

    suspend fun insertUserQuest(questId: Long, userId: UUID, progress: Int)
}