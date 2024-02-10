package ua.gleb.smartwaste.domain.usecase.items

import kotlinx.collections.immutable.ImmutableList
import ua.gleb.smartwaste.domain.repository.RubbishRepository
import ua.gleb.smartwaste.domain.usecase.core.ResultNoneParamsUseCase
import ua.gleb.smartwaste.domain.usecase.core.UseCaseLogger
import ua.gleb.smartwaste.model.Rubbish

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

class GetAvailableRubbishesUseCase(
    private val rubbishRepository: RubbishRepository,
    useCaseLogger: UseCaseLogger,
) : ResultNoneParamsUseCase<ImmutableList<Rubbish>>(useCaseLogger) {

    override suspend fun invoke() = runCatching {
        return@runCatching rubbishRepository.getAvailableRubbishes()
    }
}
