package ua.gleb.smartwaste.domain.usecase.user

import ua.gleb.smartwaste.domain.repository.UserRepository
import ua.gleb.smartwaste.domain.usecase.core.ResultNoneParamsUseCase
import ua.gleb.smartwaste.domain.usecase.core.UseCaseLogger
import ua.gleb.smartwaste.model.User

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

class GetUserUseCase(
    private val userRepository: ua.gleb.smartwaste.domain.repository.UserRepository,
    useCaseLogger: UseCaseLogger,
) : ResultNoneParamsUseCase<User>(useCaseLogger) {

    override suspend fun invoke() = runCatching {
        return@runCatching userRepository.getUser()
    }
}
