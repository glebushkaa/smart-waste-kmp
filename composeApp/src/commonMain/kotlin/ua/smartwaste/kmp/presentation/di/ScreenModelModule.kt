package ua.smartwaste.kmp.presentation.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import ua.smartwaste.kmp.presentation.MainScreenModel
import ua.smartwaste.kmp.presentation.screens.bucket.BucketScreenModel
import ua.smartwaste.kmp.presentation.screens.login.LoginScreenModel
import ua.smartwaste.kmp.presentation.screens.profile.ProfileScreenModel
import ua.smartwaste.kmp.presentation.screens.splash.SplashScreenModel

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

val screenModelModule = module {
    factoryOf(::SplashScreenModel)
    factoryOf(::LoginScreenModel)
    factoryOf(::ProfileScreenModel)
    factoryOf(::MainScreenModel)
    factoryOf(::BucketScreenModel)
}