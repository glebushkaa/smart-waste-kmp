package ua.gleb.smartwaste.data.usecase

import ua.gleb.smartwaste.domain.usecase.core.UseCaseLogger
import ua.gleb.smartwaste.log.error

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

class UseCaseLoggerImpl : UseCaseLogger {

    override fun logException(tag: String, throwable: Throwable) {
        error(tag, throwable)
    }
}
