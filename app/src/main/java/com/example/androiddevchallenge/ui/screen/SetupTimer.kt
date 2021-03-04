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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.extensions.toTimerData

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun SetupTimer(digits: Int, onValueChanged: (Int) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        TimerInputPreview(digits) {
            onValueChanged(digits / 10)
        }
        NumPad { newValue ->
            val newDigitsValue = digits * 10 + newValue
            if (newDigitsValue < MaxValueInput) {
                onValueChanged(newDigitsValue)
            }
        }
        AnimatedVisibility(digits > 0) {
            FloatingActionButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(16.dp)) {
                Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Start")
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun TimerInputPreview(digits: Int, onDeleteDigit: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        val textColor = with(MaterialTheme.colors.onBackground) {
            if (digits == 0) copy(alpha = .33F)
            else this
        }
        Text(
            text = digits.toTimerPreview(),
            color = textColor,
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(vertical = 32.dp, horizontal = 8.dp)
        )
        AnimatedVisibility(visible = digits > 0) {
            IconButton(onClick = { onDeleteDigit() }) {
                Icon(imageVector = Icons.Default.Backspace, contentDescription = "Delete")
            }
        }
        AnimatedVisibility(visible = digits == 0) {
            Spacer(modifier = DefaultIconSizeModifier)
        }
    }
}

@ExperimentalFoundationApi
@Composable
private fun NumPad(onNumberClicked: (Int) -> Unit) {
    LazyVerticalGrid(cells = GridCells.Fixed(3)) {
        items(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, null, 0, null)) { item ->
            item?.let { number ->
                Button(
                    modifier = Modifier.padding(2.dp),
                    onClick = { onNumberClicked(number) }
                ) {
                    Text(text = number.toString(), style = MaterialTheme.typography.h6)
                }
            } ?: Spacer(modifier = Modifier.size(NumPadItemSize))
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun SetupTimerPreview() {
    SetupTimer(digits = 12345) {}
}

private fun Int.toTimerPreview(): String {
    val data = this.toTimerData(100)
    return StringBuilder()
        .append(data.hours.toTimerFormat())
        .append("h ")
        .append(data.minutes.toTimerFormat())
        .append("m ")
        .append(data.seconds.toTimerFormat())
        .append("s")
        .toString()
}

private fun Int.toTimerFormat(): String =
    if (this < 10) "0$this" else toString()

private const val MaxValueInput = 1000000
private val NumPadItemSize = 56.dp
private val DefaultIconSizeModifier = Modifier.size(48.dp)
