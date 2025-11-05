package com.mobbelldev.erataniassessmenttest.ui.screen.word_search

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.mobbelldev.erataniassessmenttest.ui.component.Material3SearchBarComponent

@Composable
fun WordSearchScreen(modifier: Modifier = Modifier) {
    val dummyData = listOf(
        "Jagung",
        "Kentang",
        "Singkong",
        "Ubi Jalar",
        "Beras",
        "Daun Bawang",
        "Bawang Bombay",
        "Bawang Merah",
        "Bawang Putih",
        "Cabai Merah",
        "Cabai Rawit",
        "Cabai Hijau",
        "Kacang Tanah",
        "Kedelai",
        "Kacang Hijau"
    )
    var query by remember { mutableStateOf(value = "") }
    // Controls expansion state of the search bar
    var expanded by rememberSaveable { mutableStateOf(value = false) }
    var searchResult by remember { mutableStateOf<String?>(value = null) }
    fun onSearch(queryText: String) {
        expanded = false
        searchResult = findWord(
            input = queryText,
            dataList = dummyData
        )
    }

    // https://developer.android.com/develop/ui/compose/components/search-bar
    Material3SearchBarComponent(
        modifier = modifier,
        query = query,
        expanded = expanded,
        searchResult = searchResult,
        dataDummy = dummyData,
        onQueryChange = { query = it },
        onExpandedChange = { expanded = it },
        onSearch = ::onSearch,
        onClear = { query = "" }
    )
}

private fun findWord(input: String, dataList: List<String>): String? {
    if (input.isBlank()) return null
    val inputUser = input.lowercase()
    val found = dataList.find { it.lowercase() == inputUser }
    return found ?: ""
}