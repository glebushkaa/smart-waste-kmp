package ua.smartwaste.kmp.presentation.screens.bucket

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 1/1/2024
 */

sealed interface BucketSideEffect {

    data object ScanRubbish : BucketSideEffect

    data class Toast(val message: String) : BucketSideEffect
}