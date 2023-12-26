package ua.smartwaste.kmp.domain.repository

import ua.smartwaste.kmp.model.User

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

interface UserRepository {

    suspend fun getUser(): User
}
