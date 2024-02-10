package ua.gleb.smartwaste.domain.usecase.items

import ua.gleb.smartwaste.domain.usecase.core.ResultUseCase
import ua.gleb.smartwaste.domain.usecase.core.UseCase
import ua.gleb.smartwaste.domain.usecase.core.UseCaseLogger
import ua.gleb.smartwaste.model.Rubbish

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 1/2/2024
 */

class ScanItemUseCase(
    private val rubbishRepository: ua.gleb.smartwaste.domain.repository.RubbishRepository,
    useCaseLogger: UseCaseLogger
) : ResultUseCase<Rubbish, ScanItemUseCase.Params>(useCaseLogger) {

    override suspend fun invoke(params: Params) = runCatching {
        val item = rubbishRepository.scanItem(params.filePath)
        return@runCatching item ?: throw NullPointerException()
    }

    data class Params(
        val filePath: String,
    ) : UseCase.Params
}