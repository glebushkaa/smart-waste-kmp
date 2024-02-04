package ua.gleb.smartwaste.data.repository

import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ua.gleb.smartwaste.core.mapToImmutable
import ua.gleb.smartwaste.data.mapper.toRubbish
import ua.gleb.smartwaste.data.mapper.toRubbishEntity
import ua.gleb.smartwaste.database.api.ItemsDatabase
import ua.gleb.smartwaste.domain.repository.ItemsRepository
import ua.gleb.smartwaste.model.Rubbish
import ua.gleb.smartwaste.network.api.items.ItemsApi

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

class ItemsRepositoryImpl(
    private val itemsApi: ua.gleb.smartwaste.network.api.items.ItemsApi,
    private val itemsDatabase: ItemsDatabase,
) : ua.gleb.smartwaste.domain.repository.ItemsRepository {

    override suspend fun scanItem(path: String): Rubbish? {
        return itemsApi.scanRubbish(path)?.toRubbish()
    }

    override suspend fun getAvailableRubbishes(): ImmutableList<Rubbish> {
        return itemsApi.getAvailableRubbishes().mapToImmutable {
            it.toRubbish()
        }
    }

    override fun addRubbish(rubbish: Rubbish) {
        val entity = rubbish.toRubbishEntity()
        itemsDatabase.insertRubbish(entity)
    }

    override fun getAllRubbishesFlow(): Flow<ImmutableList<Rubbish>> {
        return itemsDatabase.getAllRubbishesFlow().map {
            it.mapToImmutable { rubbishEntity -> rubbishEntity.toRubbish() }
        }
    }

    override fun updateRubbishCount(id: Long, count: Int) {
        itemsDatabase.updateRubbishCount(
            id = id,
            count = count
        )
    }
}
