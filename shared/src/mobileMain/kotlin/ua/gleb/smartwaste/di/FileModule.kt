package ua.gleb.smartwaste.di

import org.koin.core.scope.Scope
import org.koin.dsl.module
import ua.gleb.smartwaste.network.impl.FileUploader

expect fun Scope.singleFileUploader(): FileUploader

val fileModule = module {
    single<FileUploader> { singleFileUploader() }
}