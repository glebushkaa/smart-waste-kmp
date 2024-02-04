package ua.gleb.smartwaste.di

import org.koin.dsl.module
import ua.gleb.smartwaste.data.repository.login.LoginRepository
import ua.gleb.smartwaste.data.repository.login.LoginRepositoryImpl
import ua.gleb.smartwaste.data.repository.user.UserRepository
import ua.gleb.smartwaste.data.repository.user.UserRepositoryImpl

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<LoginRepository> { LoginRepositoryImpl(get()) }
}