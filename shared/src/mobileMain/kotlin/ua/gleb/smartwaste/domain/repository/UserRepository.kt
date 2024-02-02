package ua.gleb.smartwaste.domain.repository

import kotlinx.collections.immutable.ImmutableList
import ua.gleb.smartwaste.model.Quest
import ua.gleb.smartwaste.model.User

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

interface UserRepository {

    suspend fun getUser(): User

    suspend fun getQuests(): ImmutableList<Quest>
}
