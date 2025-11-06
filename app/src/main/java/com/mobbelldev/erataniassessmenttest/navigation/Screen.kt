package com.mobbelldev.erataniassessmenttest.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    object Root : Screen()

    @Serializable
    object WordSearch : Screen()

    @Serializable
    object DataProcessing : Screen()

    @Serializable
    object Animation : Screen()
}