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
package com.example.androiddevchallenge

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.ui.screen.SetupTimer
import com.example.androiddevchallenge.ui.screen.TimerCountdown
import com.example.androiddevchallenge.ui.theme.MyTheme

@ExperimentalAnimationApi
@ExperimentalFoundationApi
class MainActivity : AppCompatActivity() {

    private var countDownTimer: CountDownTimer? = null
    private var countdownSeconds by mutableStateOf(0)
    private var isTimerRunning by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyTheme {
                MyApp(
                    isTimerRunning = isTimerRunning,
                    countdownSeconds = countdownSeconds,
                    onStartTimer = { timeInSeconds ->
                        isTimerRunning = true
                        countDownTimer?.cancel()
                        countDownTimer =
                            object : CountDownTimer((timeInSeconds * 1000).toLong(), 1000) {
                                override fun onTick(millisUntilFinished: Long) {
                                    countdownSeconds = millisUntilFinished.toInt() / 1000 + 1
                                }

                                override fun onFinish() {
                                    countdownSeconds = 0
                                }
                            }
                        countDownTimer?.start()
                    },
                    onStopTimer = { isTimerRunning = false }
                )
            }
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun MyApp(
    isTimerRunning: Boolean = false,
    countdownSeconds: Int = 0,
    onStartTimer: ((Int) -> Unit)? = null,
    onStopTimer: (() -> Unit)? = null
) {
    Surface(color = MaterialTheme.colors.background) {
        if (!isTimerRunning) {
            SetupTimer {
                onStartTimer?.invoke(it)
            }
        } else {
            TimerCountdown(countdownSeconds = countdownSeconds) {
                onStopTimer?.invoke()
            }
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp {}
    }
}

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp {}
    }
}
