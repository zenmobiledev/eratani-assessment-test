package com.mobbelldev.erataniassessmenttest.ui.screen.animation

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp

@Composable
fun AnimationScreen(modifier: Modifier = Modifier) {
    var bpm by remember { mutableFloatStateOf(60f) }

    key(bpm) {
        val durationPerBeat = (60_000 / bpm).toInt()
        val infiniteTransition = rememberInfiniteTransition(label = "heart_beat")

        val scale by infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = 1.4f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = durationPerBeat),
                repeatMode = RepeatMode.Reverse,
            ),
            label = "heart_rate_animation"
        )
        HeartRateAnimation(
            modifier = modifier,
            scale = scale,
            bpm = bpm,
            onIncreaseBpm = { if (bpm < 180) bpm += 5 },
            onDecreaseBpm = { if (bpm > 40) bpm -= 5 },
        )
    }
}

@Composable
fun HeartRateAnimation(
    modifier: Modifier = Modifier,
    scale: Float,
    bpm: Float,
    onIncreaseBpm: () -> Unit,
    onDecreaseBpm: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Heart Beat (${bpm.toInt()}) BPM",
            fontSize = MaterialTheme.typography.titleLarge.fontSize
        )
        Spacer(modifier = Modifier.height(32.dp))

        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "Heart Beat",
            tint = MaterialTheme.colorScheme.error,
            modifier = Modifier
                .size(128.dp)
                .scale(scale)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = onDecreaseBpm) {
                Text(text = "−")
            }
            Button(onClick = onIncreaseBpm) {
                Text(text = "+")
            }
        }
    }
}