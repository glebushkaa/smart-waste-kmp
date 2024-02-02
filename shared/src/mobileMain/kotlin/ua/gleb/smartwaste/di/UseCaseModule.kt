package ua.gleb.smartwaste.di

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ua.gleb.smartwaste.data.usecase.UseCaseLoggerImpl
import ua.gleb.smartwaste.domain.usecase.auth.LogOutUseCase
import ua.gleb.smartwaste.domain.usecase.core.UseCaseLogger
import ua.gleb.smartwaste.domain.usecase.auth.LoginUseCase
import ua.gleb.smartwaste.domain.usecase.auth.RegisterUseCase
import ua.gleb.smartwaste.domain.usecase.items.GetAvailableRubbishesUseCase
import ua.gleb.smartwaste.domain.usecase.items.GetAllRubbishesFlowUseCase
import ua.gleb.smartwaste.domain.usecase.items.AddRubbishUseCase
import ua.gleb.smartwaste.domain.usecase.items.UpdateRubbishCountUseCase
import ua.gleb.smartwaste.domain.usecase.items.ScanItemUseCase
import ua.gleb.smartwaste.domain.usecase.user.GetQuestsUseCase
import ua.gleb.smartwaste.domain.usecase.user.GetUserUseCase

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

val useCaseModule = module {
    singleOf(::UseCaseLoggerImpl) bind UseCaseLogger::class

    factoryOf(::LoginUseCase)
    factoryOf(::RegisterUseCase)
    factoryOf(::LogOutUseCase)

    factoryOf(::GetUserUseCase)
    factoryOf(::GetQuestsUseCase)

    factoryOf(::GetAvailableRubbishesUseCase)
    factoryOf(::GetAllRubbishesFlowUseCase)
    factoryOf(::AddRubbishUseCase)
    factoryOf(::UpdateRubbishCountUseCase)
    factoryOf(::ScanItemUseCase)
}
