package ua.gleb.smartwaste.domain.repository

import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow
import ua.gleb.smartwaste.model.Rubbish

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

interface RubbishRepository {

    suspend fun getAvailableRubbishes(): ImmutableList<Rubbish>

    suspend fun scanItem(path: String): Rubbish?

    fun addRubbish(rubbish: Rubbish)

    fun getAllRubbishesFlow(): Flow<ImmutableList<Rubbish>>

    fun updateRubbishCount(id: Long, count: Int)

}
