package ua.gleb.smartwaste.database.api

import kotlinx.coroutines.flow.Flow
import ua.gleb.smartwaste.database.api.entity.RubbishEntity

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/31/2023
 */

interface ItemsDatabase {

    fun insertRubbish(rubbishEntity: RubbishEntity)

    fun getAllRubbishesFlow(): Flow<List<RubbishEntity>>

    fun updateRubbishCount(id: Long, count: Int)
}