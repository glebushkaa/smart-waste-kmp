package ua.smartwaste.kmp.domain.usecase.user

import ua.smartwaste.kmp.domain.repository.UserRepository
import ua.smartwaste.kmp.domain.usecase.core.ResultNoneParamsUseCase
import ua.smartwaste.kmp.domain.usecase.core.UseCaseLogger
import ua.smartwaste.kmp.model.Quest

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

class GetQuestsUseCase(
    private val userRepository: UserRepository,
    useCaseLogger: UseCaseLogger,
) : ResultNoneParamsUseCase<List<Quest>>(useCaseLogger) {

    override suspend fun invoke() = runCatching {
        return@runCatching userRepository.getQuests()
    }
}
