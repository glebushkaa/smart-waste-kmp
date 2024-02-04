package ua.gleb.smartwaste.domain.repository

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

interface AuthRepository {

    /**
     * @return token
     */
    suspend fun login(email: String, password: String): String

    /**
     * @return token
     */
    suspend fun register(username: String, email: String, password: String): String
}
