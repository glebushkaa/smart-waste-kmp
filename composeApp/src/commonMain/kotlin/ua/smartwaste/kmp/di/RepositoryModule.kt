package ua.smartwaste.kmp.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ua.smartwaste.kmp.data.repository.AuthRepositoryImpl
import ua.smartwaste.kmp.data.repository.ItemsRepositoryImpl
import ua.smartwaste.kmp.data.repository.UserRepositoryImpl
import ua.smartwaste.kmp.domain.repository.AuthRepository
import ua.smartwaste.kmp.domain.repository.ItemsRepository
import ua.smartwaste.kmp.domain.repository.UserRepository

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

val repositoryModule = module {
    singleOf(::AuthRepositoryImpl) bind AuthRepository::class
    singleOf(::UserRepositoryImpl) bind UserRepository::class
    singleOf(::ItemsRepositoryImpl) bind ItemsRepository::class
}
