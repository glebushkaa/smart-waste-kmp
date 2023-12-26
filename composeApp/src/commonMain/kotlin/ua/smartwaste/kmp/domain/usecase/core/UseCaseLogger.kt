package ua.smartwaste.kmp.domain.usecase.core

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

interface UseCaseLogger {

    fun logException(tag: String, throwable: Throwable)
}
