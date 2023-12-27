package ua.smartwaste.kmp.domain.repository

import kotlinx.collections.immutable.ImmutableList
import ua.smartwaste.kmp.model.Rubbish

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

interface ItemsRepository {

    suspend fun getAvailableRubbishes(): ImmutableList<Rubbish>
}
