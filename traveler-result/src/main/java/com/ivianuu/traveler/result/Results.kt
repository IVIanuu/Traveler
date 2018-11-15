/*
 * Copyright 2018 Manuel Wrage
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ivianuu.traveler.result

internal object Results {

    private val resultListeners = mutableMapOf<Int, MutableSet<ResultListener>>()

    fun addResultListener(resultCode: Int, listener: ResultListener) {
        val listeners = resultListeners.getOrPut(resultCode) { mutableSetOf() }
        if (!listeners.contains(listener)) {
            listeners.add(listener)
        }
    }

    fun removeResultListener(resultCode: Int, listener: ResultListener) {
        val listeners = resultListeners[resultCode] ?: return
        listeners.remove(listener)
        if (listeners.isEmpty()) {
            resultListeners.remove(resultCode)
        }
    }

    fun sendResult(resultCode: Int, result: Any): Boolean {
        val listeners = resultListeners[resultCode]?.toList()
        if (listeners != null) {
            listeners.forEach { it(result) }
            return true
        }

        return false
    }
}