package ua.smartwaste.kmp.domain.usecase.items

import ua.smartwaste.kmp.domain.repository.ItemsRepository
import ua.smartwaste.kmp.domain.usecase.core.ResultUseCase
import ua.smartwaste.kmp.domain.usecase.items.UpdateRubbishCountUseCase.Params
import ua.smartwaste.kmp.domain.usecase.core.UseCase
import ua.smartwaste.kmp.domain.usecase.core.UseCaseLogger

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/31/2023
 */

class UpdateRubbishCountUseCase(
    private val itemsRepository: ItemsRepository,
    useCaseLogger: UseCaseLogger
) : ResultUseCase<Unit, Params>(useCaseLogger) {

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