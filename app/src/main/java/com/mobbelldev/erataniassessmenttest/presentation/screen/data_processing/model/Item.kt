package com.mobbelldev.erataniassessmenttest.presentation.screen.data_processing.model

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val id: Int,
    val nama_barang: String,
    val stok_awal: Int,
    val transaksi: List<Transaction>,
    val stok_akhir: Int
)
