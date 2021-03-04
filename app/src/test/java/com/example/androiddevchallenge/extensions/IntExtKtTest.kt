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
package com.example.androiddevchallenge.extensions

import junit.framework.TestCase
import org.junit.Test

class IntExtKtTest : TestCase() {

    @Test
    fun `test values toTimerData`() {
        var data = 123.toTimerData()
        assertEquals(0, data.hours)
        assertEquals(2, data.minutes)
        assertEquals(3, data.seconds)

        data = 18305.toTimerData()
        assertEquals(5, data.hours)
        assertEquals(5, data.minutes)
        assertEquals(5, data.seconds)
    }
}