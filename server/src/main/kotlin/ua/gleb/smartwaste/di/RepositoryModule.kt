package ua.gleb.smartwaste.di

import org.koin.dsl.module
import ua.gleb.smartwaste.data.repository.LoginRepositoryImpl
import ua.gleb.smartwaste.data.repository.QuestRepositoryImpl
import ua.gleb.smartwaste.data.repository.RubbishRepositoryImpl
import ua.gleb.smartwaste.data.repository.UserRepositoryImpl
import ua.gleb.smartwaste.domain.repository.LoginRepository
import ua.gleb.smartwaste.domain.repository.QuestRepository
import ua.gleb.smartwaste.domain.repository.RubbishRepository
import ua.gleb.smartwaste.domain.repository.UserRepository

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    single<LoginRepository> { LoginRepositoryImpl(get()) }
    single<QuestRepository> { QuestRepositoryImpl(get()) }
    single<RubbishRepository> { RubbishRepositoryImpl(get()) }
}