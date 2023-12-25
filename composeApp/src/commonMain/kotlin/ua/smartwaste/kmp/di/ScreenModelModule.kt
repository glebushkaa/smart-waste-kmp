package ua.smartwaste.kmp.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import ua.smartwaste.kmp.presentation.screens.login.LoginScreenModel

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

val screenModelModule = module {
    factoryOf(::LoginScreenModel)
}
