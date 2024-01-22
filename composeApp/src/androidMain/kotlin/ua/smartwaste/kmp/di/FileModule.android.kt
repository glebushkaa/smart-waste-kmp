package ua.smartwaste.kmp.di

import org.koin.core.scope.Scope
import ua.smartwaste.kmp.network.impl.FileUploader

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 1/2/2024
 */

actual fun Scope.singleFileUploader(): FileUploader {
    return FileUploader(context = get())
}