package ua.smartwaste.kmp.database.impl

import app.cash.sqldelight.coroutines.asFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import smartwaste.rubbish.RubbishQueries
import ua.smartwaste.kmp.database.api.ItemsDatabase
import ua.smartwaste.kmp.database.api.entity.RubbishEntity
import ua.smartwaste.kmp.database.impl.mapper.toRubbishEntity

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/31/2023
 */

class ItemsDatabaseImpl(
    private val queries: RubbishQueries
) : ItemsDatabase {

    override fun insertRubbish(rubbishEntity: RubbishEntity) {
        queries.insertRubbish(
            id = rubbishEntity.id,
            name = rubbishEntity.name,
            count = rubbishEntity.count.toLong()
        )
    }

    override fun getAllRubbishesFlow(): Flow<List<RubbishEntity>> {
        return queries.getAllRubbishes().asFlow().map {
            it.executeAsList().map { rubbish -> rubbish.toRubbishEntity() }
        }
    }

    override fun updateRubbishCount(id: Long, count: Int) {
        return queries.updateRubbishCount(
            id = id,
            count = count.toLong()
        )
    }
}