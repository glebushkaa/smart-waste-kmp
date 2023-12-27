package ua.smartwaste.kmp.data.repository

import ua.smartwaste.kmp.data.mapper.toUser
import ua.smartwaste.kmp.domain.exception.AuthException
import ua.smartwaste.kmp.domain.repository.UserRepository
import ua.smartwaste.kmp.model.User
import ua.smartwaste.kmp.network.api.user.UserApi
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

class UserRepositoryImpl(
    private val userApi: UserApi,
) : UserRepository {

    override suspend fun getUser(): User {
        val response = userApi.getUser()
        if (response.message?.isNotEmpty() == true) {
            val exception = AuthException(
                code = GET_USER_EXCEPTION,
                message = response.message,
            )
            throw exception
        }
        val days = response.createdAt?.let { calculateDaysSinceCreation(it) } ?: 0
        return response.toUser(
            days = days,
            requiredProgress = REQUIRED_LEVEL_PROGRESS,
        )
    }

    private fun calculateDaysSinceCreation(createdAt: String): Int {
        val createdDate = createdAt.toDate()
        val currentDate = Date()
        val diffInMillis = currentDate.time - createdDate.time
        return TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS).toInt()
    }

    private fun String.toDate(): Date {
        val simpleDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        return simpleDateFormat.parse(this) ?: throw ParseException("Invalid date format", 0)
    }

    private companion object {
        private const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        private const val GET_USER_EXCEPTION = 300
        private const val REQUIRED_LEVEL_PROGRESS = 500
    }
}
