/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
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
fun TimerCountdown(countdownSeconds: Int, countdownPercent: Float, onStopTimer: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = if (countdownSeconds == 0) "Time's Up!" else countdownSeconds.toCountdownPreview(),
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(vertical = 32.dp, horizontal = 8.dp)
        )
        Surface {
            CircularProgressIndicator(
                progress = countdownPercent,
                strokeWidth = 14.dp,
                modifier = Modifier.size(84.dp)
            )
            FloatingActionButton(onClick = { onStopTimer() }, modifier = Modifier.padding(14.dp)) {
                Icon(imageVector = Icons.Default.Stop, contentDescription = "Start")
            }
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
