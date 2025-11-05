package com.mobbelldev.erataniassessmenttest.presentation.screen.data_processing.model

import kotlinx.serialization.Serializable

@Serializable
data class Transaction(
    val tanggal: String,
    val stok_masuk: Int,
    val stok_keluar: Int
)
