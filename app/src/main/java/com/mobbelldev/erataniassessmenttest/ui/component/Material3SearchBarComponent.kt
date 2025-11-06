package com.mobbelldev.erataniassessmenttest.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Material3SearchBarComponent(
    modifier: Modifier = Modifier,
    query: String,
    expanded: Boolean,
    searchResult: String?,
    dataDummy: List<String>,
    onQueryChange: (String) -> Unit,
    onExpandedChange: (Boolean) -> Unit,
    onSearch: (String) -> Unit,
    onClear: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .semantics { isTraversalGroup = true }
    ) {
        SearchBar(
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .semantics { traversalIndex = 0f },
            inputField = {
                SearchBarDefaults.InputField(
                    query = query,
                    onQueryChange = onQueryChange,
                    onSearch = {
                        if (it.isNotEmpty()) {
                            onSearch(query)
                            onExpandedChange(false)
                        }
                    },
                    leadingIcon = {
                        if (!expanded) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search"
                            )
                        } else {
                            IconButton(onClick = {
                                onExpandedChange(false)
                                onClear()
                            }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }

                    },
                    trailingIcon = {
                        if (expanded) {
                            IconButton(onClick = {
                                if (query.isNotEmpty()) {
                                    onClear()
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Clear"
                                )
                            }
                        }
                    },
                    expanded = expanded,
                    onExpandedChange = onExpandedChange,
                    placeholder = { Text(text = "Cari nama barang...") },
                )
            },
            expanded = expanded,
            onExpandedChange = onExpandedChange,
        ) {
            val suggestion = dataDummy.filter {
                it.contains(query, ignoreCase = true)
            }

            if (suggestion.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "Tidak ada hasil yang cocok.",
                        fontSize = MaterialTheme.typography.titleMedium.fontSize
                    )
                }
            } else {
                // Display suggestion data dummy
                Column(modifier = Modifier.verticalScroll(state = rememberScrollState())) {
                    suggestion.forEach { item ->
                        ListItem(
                            headlineContent = { Text(text = item) },
                            modifier = Modifier
                                .clickable {
                                    onQueryChange(item)
                                    onSearch(item)
                                    onExpandedChange(false)
                                }
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
        // Show data result
        searchResult?.let { data ->
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                when (data) {
                    "" -> {
                        Text(
                            text = "Tidak ditemukan",
                            color = MaterialTheme.colorScheme.error,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize
                        )
                    }

                    else -> {
                        Text(
                            text = "Data $data ditemukan",
                            fontSize = MaterialTheme.typography.titleMedium.fontSize
                        )
                    }
                }
            }
        }
    }
}