package com.mobbelldev.erataniassessmenttest.ui.screen.data_processing.repository

import android.content.Context
import com.mobbelldev.erataniassessmenttest.ui.screen.data_processing.model.Item
import kotlinx.serialization.json.Json
import com.mobbelldev.erataniassessmenttest.R

class AgricultureRepository(private val context: Context) {
    fun loadDataFromJson(): List<Item> {
        val inputStream = context.resources.openRawResource(R.raw.agriculture)
        val jsonString = inputStream.bufferedReader().use { it.readText() }

        return Json.decodeFromString(jsonString)
    }
}