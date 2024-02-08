package ua.gleb.smartwaste.data.repository

import ua.gleb.smartwaste.core.extensions.calculateDaysSinceCreation
import ua.gleb.smartwaste.data.mapper.toUser
import ua.gleb.smartwaste.database.quest.QuestDao
import ua.gleb.smartwaste.database.quest.entity.UserQuestEntity
import ua.gleb.smartwaste.database.user.UserDao
import ua.gleb.smartwaste.domain.repository.UserRepository
import ua.gleb.smartwaste.model.Quest
import ua.gleb.smartwaste.model.User
import java.util.*

class UserRepositoryImpl(
    private val userDao: UserDao,
    private val questDao: QuestDao,
) : UserRepository {

    override suspend fun getUser(id: String): Result<User> {
        val uuid = UUID.fromString(id)
        val userEntity = userDao.getUser(uuid) ?: run {
            val throwable = Throwable("user doesn't exist")
            return Result.failure(throwable)
        }
        val createdAt = userEntity.createdAt ?: run {
            val throwable = Throwable("createdAt is null")
            return Result.failure(throwable)
        }
        val daysSinceCreation = calculateDaysSinceCreation(createdAt)
        val user = userEntity.toUser(daysSinceCreation)
        return Result.success(user)
    }

    override suspend fun getUserQuests(id: String): Result<List<Quest>> {
        val uuid = UUID.fromString(id)
        val userQuests = questDao.getUserQuests(uuid).map {
            it.toQuest()
        }
        return Result.success(userQuests)
    }

    private fun UserQuestEntity.toQuest(): Quest {
        return Quest(
            id = this.id ?: 0,
            title = this.name ?: "",
            requiredProgress = this.requiredProgress ?: 0,
            currentProgress = this.progress ?: 0
        )
    }
}