package ua.smartwaste.kmp.domain.usecase.items

import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow
import ua.smartwaste.kmp.domain.repository.ItemsRepository
import ua.smartwaste.kmp.domain.usecase.core.ResultNoneParamsUseCase
import ua.smartwaste.kmp.domain.usecase.core.UseCaseLogger
import ua.smartwaste.kmp.model.Rubbish

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/31/2023
 */

class GetAllRubbishesFlowUseCase(
    private val itemsRepository: ItemsRepository,
    useCaseLogger: UseCaseLogger
) : ResultNoneParamsUseCase<Flow<ImmutableList<Rubbish>>>(useCaseLogger) {

    override suspend fun invoke() = runCatching {
        itemsRepository.getAllRubbishesFlow()
    }
}