package ua.gleb.smartwaste.di

import org.koin.dsl.module
import ua.gleb.smartwaste.database.quest.QuestDao
import ua.gleb.smartwaste.database.quest.QuestDaoImpl
import ua.gleb.smartwaste.database.user.UserDao
import ua.gleb.smartwaste.database.user.UserDaoImpl

val databaseModule = module {
    single<UserDao> { UserDaoImpl() }
    single<QuestDao> { QuestDaoImpl() }
}