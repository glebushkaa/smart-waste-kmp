package ua.gleb.smartwaste.data.repository

import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ua.gleb.smartwaste.core.mapToImmutable
import ua.gleb.smartwaste.data.mapper.toRubbish
import ua.gleb.smartwaste.data.mapper.toRubbishEntity
import ua.gleb.smartwaste.database.api.RubbishDatabase
import ua.gleb.smartwaste.domain.repository.RubbishRepository
import ua.gleb.smartwaste.model.Rubbish
import ua.gleb.smartwaste.network.api.items.RubbishApi

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

class RubbishRepositoryImpl(
    private val rubbishApi: RubbishApi,
    private val rubbishDatabase: RubbishDatabase,
) : RubbishRepository {

    override suspend fun scanItem(path: String): Rubbish? {
        return rubbishApi.scanRubbish(path)?.toRubbish()
    }

    override suspend fun getAvailableRubbishes(): ImmutableList<Rubbish> {
        return rubbishApi.getAvailableRubbishes().mapToImmutable {
            it.toRubbish()
        }
    }

    override fun addRubbish(rubbish: Rubbish) {
        val entity = rubbish.toRubbishEntity()
        rubbishDatabase.insertRubbish(entity)
    }

    override fun getAllRubbishesFlow(): Flow<ImmutableList<Rubbish>> {
        return rubbishDatabase.getAllRubbishesFlow().map {
            it.mapToImmutable { rubbishEntity -> rubbishEntity.toRubbish() }
        }
    }

    override fun updateRubbishCount(id: Long, count: Int) {
        rubbishDatabase.updateRubbishCount(
            id = id,
            count = count
        )
    }
}
