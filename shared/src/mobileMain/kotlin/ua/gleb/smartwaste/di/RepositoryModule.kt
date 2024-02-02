package ua.gleb.smartwaste.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ua.gleb.smartwaste.data.repository.AuthRepositoryImpl
import ua.gleb.smartwaste.data.repository.ItemsRepositoryImpl
import ua.gleb.smartwaste.data.repository.UserRepositoryImpl
import ua.gleb.smartwaste.domain.repository.AuthRepository
import ua.gleb.smartwaste.domain.repository.ItemsRepository
import ua.gleb.smartwaste.domain.repository.UserRepository

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

val repositoryModule = module {
    singleOf(::AuthRepositoryImpl) bind ua.gleb.smartwaste.domain.repository.AuthRepository::class
    singleOf(::UserRepositoryImpl) bind ua.gleb.smartwaste.domain.repository.UserRepository::class
    singleOf(::ItemsRepositoryImpl) bind ua.gleb.smartwaste.domain.repository.ItemsRepository::class
}
