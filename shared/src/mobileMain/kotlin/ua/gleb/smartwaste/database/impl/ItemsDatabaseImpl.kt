package ua.gleb.smartwaste.database.impl

import app.cash.sqldelight.coroutines.asFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import smartwaste.rubbish.RubbishQueries
import ua.gleb.smartwaste.database.api.ItemsDatabase
import ua.gleb.smartwaste.database.api.entity.RubbishEntity
import ua.gleb.smartwaste.database.impl.mapper.toRubbishEntity

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
        return queries.getAllRubbishes().asFlow().map { query ->
            query.executeAsList().map { rubbish -> rubbish.toRubbishEntity() }
        }
    }

    override fun updateRubbishCount(id: Long, count: Int) {
        return queries.updateRubbishCount(
            id = id,
            count = count.toLong()
        )
    }
}