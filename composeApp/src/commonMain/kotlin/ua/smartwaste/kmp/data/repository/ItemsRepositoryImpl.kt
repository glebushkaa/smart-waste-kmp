package ua.smartwaste.kmp.data.repository

import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ua.smartwaste.kmp.core.mapToImmutable
import ua.smartwaste.kmp.data.mapper.toRubbish
import ua.smartwaste.kmp.data.mapper.toRubbishEntity
import ua.smartwaste.kmp.database.api.ItemsDatabase
import ua.smartwaste.kmp.domain.repository.ItemsRepository
import ua.smartwaste.kmp.model.Rubbish
import ua.smartwaste.kmp.network.api.items.ItemsApi

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

class ItemsRepositoryImpl(
    private val itemsApi: ItemsApi,
    private val itemsDatabase: ItemsDatabase,
) : ItemsRepository {

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
