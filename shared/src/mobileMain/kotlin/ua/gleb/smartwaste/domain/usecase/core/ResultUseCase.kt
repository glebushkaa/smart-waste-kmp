package ua.gleb.smartwaste.domain.usecase.core

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

abstract class ResultUseCase<Type : Any, in Params : UseCase.Params>(
    private val useCaseLogger: UseCaseLogger,
) : UseCase<Type, Params> {

    abstract suspend operator fun invoke(params: Params): Result<Type>

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
}
