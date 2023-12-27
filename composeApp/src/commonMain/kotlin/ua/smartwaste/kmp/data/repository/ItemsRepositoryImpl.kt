package ua.smartwaste.kmp.data.repository

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import ua.smartwaste.kmp.data.mapper.toRubbish
import ua.smartwaste.kmp.domain.repository.ItemsRepository
import ua.smartwaste.kmp.model.Rubbish
import ua.smartwaste.kmp.network.api.items.ItemsApi

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

class ItemsRepositoryImpl(
    private val itemsApi: ItemsApi,
) : ItemsRepository {

    override suspend fun getAvailableRubbishes(): ImmutableList<Rubbish> {
        return itemsApi.getAvailableRubbishes()
            .map { it.toRubbish() }
            .toImmutableList()
    }
}
