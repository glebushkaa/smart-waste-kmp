package ua.gleb.smartwaste.data.repository.login

import kotlinx.datetime.Clock
import ua.gleb.smartwaste.database.login.LoginDao
import ua.gleb.smartwaste.domain.exception.LoginException

class LoginRepositoryImpl(
    private val loginDao: LoginDao
) : LoginRepository {

    override suspend fun login(email: String, password: String): LoginResult {
        val savedPassword = loginDao.getPassword(email) ?: run {
            val throwable = LoginException("User not found")
            return LoginResult.Failure(throwable)
        }
        val passwordHash = password.hashCode().toString()
        if (passwordHash != savedPassword) {
            val throwable = LoginException("Password is not correct")
            return LoginResult.Failure(throwable)
        }
        return LoginResult.Success
    }

    override suspend fun register(name: String, email: String, password: String): LoginResult {
        val createdAt = Clock.System.now().toString()
        val hashedPassword = password.hashCode().toString()
        loginDao.register(name, email, hashedPassword, createdAt) ?: run {
            val throwable = LoginException("Register failed")
            return LoginResult.Failure(throwable)
        }
        return LoginResult.Success
    }
}