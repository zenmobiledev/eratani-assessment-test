package com.mobbelldev.erataniassessmenttest.ui.screen.root

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobbelldev.erataniassessmenttest.ui.theme.ErataniAssessmentTestTheme

@Composable
fun RootScreen(
    modifier: Modifier = Modifier,
    moveToWordSearchScreen: () -> Unit,
    moveToDataProcessingScreen: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Pencarian Kata
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 32.dp,
                    vertical = 8.dp
                ),
            onClick = { moveToWordSearchScreen() },
            shape = MaterialTheme.shapes.small,
        ) {
            Text(text = "Pencarian Kata")
        }

        // Pemrosesan Data
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 32.dp,
                    vertical = 8.dp
                ),
            onClick = { moveToDataProcessingScreen() },
            shape = MaterialTheme.shapes.small
        ) {
            Text(text = "Pemrosesan data")
        }

        // Animasi
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 32.dp,
                    vertical = 8.dp
                ),
            onClick = {},
            shape = MaterialTheme.shapes.small
        ) {
            Text(text = "Animasi")
        }

        // API Calling
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 32.dp,
                    vertical = 8.dp
                ),
            onClick = {},
            shape = MaterialTheme.shapes.small
        ) {
            Text(text = "API Calling")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RootScreenPreview() {
    ErataniAssessmentTestTheme {
        RootScreen(
            moveToWordSearchScreen = {},
            moveToDataProcessingScreen = {}
        )
    }
}