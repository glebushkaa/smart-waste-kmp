package ua.gleb.smartwaste.data.repository

import io.ktor.http.*
import kotlinx.datetime.Clock
import ua.gleb.smartwaste.domain.result.LoginResult
import ua.gleb.smartwaste.database.user.UserDao
import ua.gleb.smartwaste.domain.exception.LoginException
import ua.gleb.smartwaste.domain.repository.LoginRepository

class LoginRepositoryImpl(
    private val userDao: UserDao
) : LoginRepository {

    override suspend fun login(email: String, password: String): LoginResult {
        val user = userDao.getUserByEmail(email) ?: run {
            val throwable = LoginException("User not found", HttpStatusCode.NotFound)
            return LoginResult.Failure(throwable)
        }
        val passwordHash = password.hashCode().toString()
        if (passwordHash != user.password) {
            val throwable = LoginException("Password is not correct", HttpStatusCode.Unauthorized)
            return LoginResult.Failure(throwable)
        }
        val id = user.id ?: run {
            val throwable = LoginException("id is null", HttpStatusCode.NotFound)
            return LoginResult.Failure(throwable)
        }
        return LoginResult.Success(id)
    }

    override suspend fun register(name: String, email: String, password: String): LoginResult {
        val userExists = userDao.userExists(email)
        if (userExists) {
            val throwable = LoginException("User exists", HttpStatusCode.Conflict)
            return LoginResult.Failure(throwable)
        }
        val createdAt = Clock.System.now().toString()
        val hashedPassword = password.hashCode().toString()
        val user = userDao.register(name, email, hashedPassword, createdAt) ?: run {
            val throwable = LoginException("Register failed", HttpStatusCode.NotFound)
            return LoginResult.Failure(throwable)
        }
        val id = user.id ?: run {
            val throwable = LoginException("id is null", HttpStatusCode.NotFound)
            return LoginResult.Failure(throwable)
        }
        return LoginResult.Success(id)
    }
}