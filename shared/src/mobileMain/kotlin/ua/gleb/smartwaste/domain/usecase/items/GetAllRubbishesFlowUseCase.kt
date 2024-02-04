package ua.gleb.smartwaste.domain.usecase.items

import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow
import ua.gleb.smartwaste.domain.repository.ItemsRepository
import ua.gleb.smartwaste.domain.usecase.core.ResultNoneParamsUseCase
import ua.gleb.smartwaste.domain.usecase.core.UseCaseLogger
import ua.gleb.smartwaste.model.Rubbish

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/31/2023
 */

class GetAllRubbishesFlowUseCase(
    private val itemsRepository: ua.gleb.smartwaste.domain.repository.ItemsRepository,
    useCaseLogger: UseCaseLogger
) : ResultNoneParamsUseCase<Flow<ImmutableList<Rubbish>>>(useCaseLogger) {

    override suspend fun invoke() = runCatching {
        itemsRepository.getAllRubbishesFlow()
    }
}