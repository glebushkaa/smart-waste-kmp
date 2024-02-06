package ua.gleb.smartwaste.data.repository.user

import ua.gleb.smartwaste.core.extensions.calculateDaysSinceCreation
import ua.gleb.smartwaste.data.mapper.toUser
import ua.gleb.smartwaste.database.user.UserDao
import ua.gleb.smartwaste.model.User
import java.util.*

class UserRepositoryImpl(
    private val userDao: UserDao
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

    override suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }
}