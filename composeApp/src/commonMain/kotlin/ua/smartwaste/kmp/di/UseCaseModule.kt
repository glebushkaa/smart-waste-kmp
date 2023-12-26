package ua.smartwaste.kmp.di

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ua.smartwaste.kmp.data.usecase.UseCaseLoggerImpl
import ua.smartwaste.kmp.domain.usecase.auth.LogOutUseCase
import ua.smartwaste.kmp.domain.usecase.auth.LoginUseCase
import ua.smartwaste.kmp.domain.usecase.auth.RegisterUseCase
import ua.smartwaste.kmp.domain.usecase.core.UseCaseLogger
import ua.smartwaste.kmp.domain.usecase.user.GetUserUseCase

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

val useCaseModule = module {
    singleOf(::UseCaseLoggerImpl) bind UseCaseLogger::class

    factoryOf(::LoginUseCase)
    factoryOf(::RegisterUseCase)
    factoryOf(::LogOutUseCase)

    factoryOf(::GetUserUseCase)
}
