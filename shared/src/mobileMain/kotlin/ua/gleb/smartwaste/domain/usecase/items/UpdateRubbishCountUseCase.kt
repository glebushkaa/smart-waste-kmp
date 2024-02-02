package ua.gleb.smartwaste.domain.usecase.items

import ua.gleb.smartwaste.domain.repository.ItemsRepository
import ua.gleb.smartwaste.domain.usecase.core.ResultUseCase
import ua.gleb.smartwaste.domain.usecase.core.UseCase
import ua.gleb.smartwaste.domain.usecase.core.UseCaseLogger

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/31/2023
 */

class UpdateRubbishCountUseCase(
    private val itemsRepository: ua.gleb.smartwaste.domain.repository.ItemsRepository,
    useCaseLogger: UseCaseLogger
) : ResultUseCase<Unit, UpdateRubbishCountUseCase.Params>(useCaseLogger) {

    override suspend fun invoke(params: Params) = runCatching {
        itemsRepository.updateRubbishCount(
            id = params.id,
            count = params.count
        )
    }

    data class Params(
        val id: Long,
        val count: Int
    ) : UseCase.Params
}