package com.example.androiddevchallenge.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Stop
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.extensions.appendLeadingZero
import com.example.androiddevchallenge.extensions.toTimerData

@Composable
fun TimerCountdown(countdownSeconds: Int, onStopTimer: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = countdownSeconds.toCountdownPreview(),
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(vertical = 32.dp, horizontal = 8.dp)
        )
        FloatingActionButton(
            onClick = { onStopTimer() },
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(imageVector = Icons.Default.Stop, contentDescription = "Start")
        }
    }
}

private fun Int.toCountdownPreview(): String {
    val data = this.toTimerData()
    return StringBuilder().apply {
        if (data.hours > 0) {
            append(data.hours)
            append(":")
        }
        if (data.hours > 0 || data.minutes > 0) {
            append(if (length == 0) data.minutes else data.minutes.appendLeadingZero())
            append(":")
        }
        append(if (length == 0) data.seconds else data.seconds.appendLeadingZero())
    }.toString()
}
