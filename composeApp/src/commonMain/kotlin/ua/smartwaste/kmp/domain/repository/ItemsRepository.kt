package ua.smartwaste.kmp.domain.repository

import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow
import ua.smartwaste.kmp.model.Rubbish

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

interface ItemsRepository {

    suspend fun getAvailableRubbishes(): ImmutableList<Rubbish>

    fun addRubbish(rubbish: Rubbish)

    fun getAllRubbishesFlow(): Flow<ImmutableList<Rubbish>>

    fun updateRubbishCount(id: Long, count: Int)
}
