package ua.smartwaste.kmp.domain.usecase.user

import ua.smartwaste.kmp.domain.repository.UserRepository
import ua.smartwaste.kmp.domain.usecase.core.ResultNoneParamsUseCase
import ua.smartwaste.kmp.domain.usecase.core.UseCaseLogger
import ua.smartwaste.kmp.model.User

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

class GetUserUseCase(
    private val userRepository: UserRepository,
    useCaseLogger: UseCaseLogger,
) : ResultNoneParamsUseCase<User>(useCaseLogger) {

    override suspend fun invoke() = runCatching {
        return@runCatching userRepository.getUser()
    }
}
