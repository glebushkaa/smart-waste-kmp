package ua.gleb.smartwaste.domain.usecase.core

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

abstract class ResultNoneParamsUseCase<Type : Any>(
    private val useCaseLogger: UseCaseLogger,
) : UseCase<Type, Nothing> {

    @Suppress("TooGenericExceptionCaught")
    suspend fun <T, P> T.runCatching(
        context: CoroutineContext = Dispatchers.IO,
        block: suspend T.() -> P,
    ): Result<P> = withContext(context) {
        return@withContext try {
            Result.success(block())
        } catch (throwable: Throwable) {
            useCaseLogger.logException(javaClass.simpleName, throwable)
            Result.failure(throwable)
        }
    }

    abstract suspend operator fun invoke(): Result<Type>
}
