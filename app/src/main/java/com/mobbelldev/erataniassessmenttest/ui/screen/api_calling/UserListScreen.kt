package com.mobbelldev.erataniassessmenttest.ui.screen.api_calling

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mobbelldev.erataniassessmenttest.ui.screen.api_calling.api.ApiClient
import com.mobbelldev.erataniassessmenttest.ui.screen.api_calling.model.response.UserResponse

@Composable
fun UserListScreen(modifier: Modifier = Modifier) {
    var users by remember { mutableStateOf<List<UserResponse>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            users = ApiClient.api.getUsers()
        } catch (e: Exception) {
            error = e.message
        } finally {
            isLoading = false
        }
    }

    when {
        isLoading -> Box(
            modifier.fillMaxSize(),
            Alignment.Center
        ) {
            CircularProgressIndicator()
        }

        error != null -> Box(
            modifier = Modifier.fillMaxSize(),
            Alignment.Center
        ) {
            Text(
                "Error: $error",
                color = MaterialTheme.colorScheme.error
            )
        }

        else -> {
            // https://stackoverflow.com/questions/68143308/how-do-i-create-a-table-in-jetpack-compose

            val column1Weight = .3f
            val column2Weight = .5f
            val column3Weight = .2f

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                ) {
                    TableCellHeader("Nama", column1Weight)
                    TableCellHeader("Email", column2Weight)
                    TableCellHeader("Gender", column3Weight)
                }

                // Body
                LazyColumn {
                    items(users) { user ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.surface)
                        ) {
                            TableCell(user.name ?: "-", column1Weight)
                            TableCell(user.email ?: "-", column2Weight)
                            TableCell(user.gender ?: "-", column3Weight)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RowScope.TableCellHeader(
    text: String,
    weight: Float
) {
    Text(
        text = text,
        modifier = Modifier
            .weight(weight)
            .border(1.dp, MaterialTheme.colorScheme.outline)
            .padding(8.dp),
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun RowScope.TableCell(
    text: String,
    weight: Float
) {
    Text(
        text = text,
        modifier = Modifier
            .weight(weight)
            .border(1.dp, MaterialTheme.colorScheme.outline)
            .padding(8.dp),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.bodyMedium
    )
}