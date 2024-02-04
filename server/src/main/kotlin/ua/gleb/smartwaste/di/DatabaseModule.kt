package ua.gleb.smartwaste.di

import org.koin.dsl.module
import ua.gleb.smartwaste.database.login.LoginDao
import ua.gleb.smartwaste.database.login.LoginDaoImpl
import ua.gleb.smartwaste.database.user.UserDao
import ua.gleb.smartwaste.database.user.UserDaoImpl

val databaseModule = module {

    single<UserDao> { UserDaoImpl() }
    single<LoginDao> { LoginDaoImpl() }
}