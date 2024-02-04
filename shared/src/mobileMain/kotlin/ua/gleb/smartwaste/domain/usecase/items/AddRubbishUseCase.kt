package ua.gleb.smartwaste.domain.usecase.items

import ua.gleb.smartwaste.domain.repository.ItemsRepository
import ua.gleb.smartwaste.domain.usecase.core.ResultUseCase
import ua.gleb.smartwaste.domain.usecase.core.UseCase
import ua.gleb.smartwaste.domain.usecase.core.UseCaseLogger
import ua.gleb.smartwaste.model.Rubbish

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/31/2023
 */

class AddRubbishUseCase(
    private val itemsRepository: ua.gleb.smartwaste.domain.repository.ItemsRepository,
    useCaseLogger: UseCaseLogger
) : ResultUseCase<Unit, AddRubbishUseCase.Params>(useCaseLogger) {

    override suspend fun invoke(params: Params) = runCatching {
        itemsRepository.addRubbish(params.rubbish)
    }

    data class Params(
        val rubbish: Rubbish
    ) : UseCase.Params
}