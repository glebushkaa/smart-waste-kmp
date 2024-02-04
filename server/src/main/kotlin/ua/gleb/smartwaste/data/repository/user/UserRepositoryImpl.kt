package ua.gleb.smartwaste.data.repository.user

import ua.gleb.smartwaste.core.extensions.calculateDaysSinceCreation
import ua.gleb.smartwaste.database.user.UserDao
import ua.gleb.smartwaste.data.mapper.toUser
import ua.gleb.smartwaste.model.User
import java.util.*

class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun getAllUsers(): List<User> {
        return userDao.getAllUsers().map { user ->
            val daysSinceCreation = calculateDaysSinceCreation(user.createdAt ?: "")
            user.toUser(daysSinceCreation)
        }
    }

    override suspend fun getUser(id: String): User? {
        val uuid = UUID.fromString(id)
        val user = userDao.getUser(uuid)
        val daysSinceCreation = calculateDaysSinceCreation(user?.createdAt ?: "")
        return user?.toUser(daysSinceCreation)
    }
}