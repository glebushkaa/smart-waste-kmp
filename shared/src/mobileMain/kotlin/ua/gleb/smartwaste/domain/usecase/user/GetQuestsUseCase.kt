package ua.gleb.smartwaste.domain.usecase.user

import kotlinx.collections.immutable.ImmutableList
import ua.gleb.smartwaste.domain.usecase.core.ResultNoneParamsUseCase
import ua.gleb.smartwaste.domain.usecase.core.UseCaseLogger
import ua.gleb.smartwaste.model.Quest

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

class GetQuestsUseCase(
    private val userRepository: ua.gleb.smartwaste.domain.repository.UserRepository,
    useCaseLogger: UseCaseLogger,
) : ResultNoneParamsUseCase<ImmutableList<Quest>>(useCaseLogger) {

    override suspend fun invoke() = runCatching {
        return@runCatching userRepository.getQuests()
    }
}
