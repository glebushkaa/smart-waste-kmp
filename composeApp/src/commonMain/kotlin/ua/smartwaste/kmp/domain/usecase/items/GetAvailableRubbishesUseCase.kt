package ua.smartwaste.kmp.domain.usecase.items

import kotlinx.collections.immutable.ImmutableList
import ua.smartwaste.kmp.domain.repository.ItemsRepository
import ua.smartwaste.kmp.domain.usecase.core.ResultNoneParamsUseCase
import ua.smartwaste.kmp.domain.usecase.core.UseCaseLogger
import ua.smartwaste.kmp.model.Rubbish

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

class GetAvailableRubbishesUseCase(
    private val itemsRepository: ItemsRepository,
    useCaseLogger: UseCaseLogger,
) : ResultNoneParamsUseCase<ImmutableList<Rubbish>>(useCaseLogger) {

    override suspend fun invoke() = runCatching {
        return@runCatching itemsRepository.getAvailableRubbishes()
    }
}
