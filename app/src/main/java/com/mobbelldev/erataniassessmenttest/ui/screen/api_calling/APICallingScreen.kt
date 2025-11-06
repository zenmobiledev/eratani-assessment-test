package com.mobbelldev.erataniassessmenttest.ui.screen.api_calling

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun APICallingScreen(
    moveToListUser: () -> Unit,
    moveToRegisterUser: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // List User
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 32.dp,
                    vertical = 8.dp
                ),
            onClick = { moveToListUser() },
            shape = MaterialTheme.shapes.small,
        ) {
            Text(text = "Menampilkan Daftar User")
        }

        // Register User
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 32.dp,
                    vertical = 8.dp
                ),
            onClick = { moveToRegisterUser() },
            shape = MaterialTheme.shapes.small,
        ) {
            Text(text = "Daftar User")
        }
    }
}