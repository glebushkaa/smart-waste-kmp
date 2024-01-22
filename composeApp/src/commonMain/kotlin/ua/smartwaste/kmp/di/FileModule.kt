package ua.smartwaste.kmp.di

import org.koin.core.scope.Scope
import org.koin.dsl.module
import ua.smartwaste.kmp.network.impl.FileUploader

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 1/2/2024
 */

expect fun Scope.singleFileUploader(): FileUploader

val fileModule = module {
    single<FileUploader> { singleFileUploader() }
}