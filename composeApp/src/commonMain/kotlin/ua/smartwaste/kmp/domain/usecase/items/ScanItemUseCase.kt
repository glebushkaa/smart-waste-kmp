package ua.smartwaste.kmp.domain.usecase.items

import ua.smartwaste.kmp.domain.repository.ItemsRepository
import ua.smartwaste.kmp.domain.usecase.core.ResultUseCase
import ua.smartwaste.kmp.domain.usecase.core.UseCase
import ua.smartwaste.kmp.domain.usecase.core.UseCaseLogger
import ua.smartwaste.kmp.domain.usecase.items.ScanItemUseCase.Params
import ua.smartwaste.kmp.model.Rubbish

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 1/2/2024
 */

class ScanItemUseCase(
    private val itemsRepository: ItemsRepository,
    useCaseLogger: UseCaseLogger
) : ResultUseCase<Rubbish, Params>(useCaseLogger) {

    override suspend fun invoke(params: Params) = runCatching {
        val item = itemsRepository.scanItem(params.filePath)
        return@runCatching item ?: throw NullPointerException()
    }

    data class Params(
        val filePath: String,
    ) : UseCase.Params
}