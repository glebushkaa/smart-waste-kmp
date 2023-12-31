package ua.smartwaste.kmp.domain.usecase.items

import ua.smartwaste.kmp.domain.repository.ItemsRepository
import ua.smartwaste.kmp.domain.usecase.core.ResultUseCase
import ua.smartwaste.kmp.domain.usecase.core.UseCase
import ua.smartwaste.kmp.domain.usecase.core.UseCaseLogger
import ua.smartwaste.kmp.domain.usecase.items.AddRubbishUseCase.Params
import ua.smartwaste.kmp.model.Rubbish

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/31/2023
 */

class AddRubbishUseCase(
    private val itemsRepository: ItemsRepository,
    useCaseLogger: UseCaseLogger
) : ResultUseCase<Unit, Params>(useCaseLogger) {

    override suspend fun invoke(params: Params) = runCatching {
        itemsRepository.addRubbish(params.rubbish)
    }

    data class Params(
        val rubbish: Rubbish
    ) : UseCase.Params
}