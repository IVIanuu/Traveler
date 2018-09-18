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

package com.ivianuu.traveler

import com.ivianuu.traveler.internal.CommandBuffer
import com.ivianuu.traveler.internal.Results

/**
 * Base router
 */
open class Router {

    internal val commandBuffer = CommandBuffer()

    private val navigationListeners = mutableSetOf<NavigatorListener>()

    /**
     * Adds a listener which will be invoked after each call to [executeCommands]
     */
    fun addNavigationListener(listener: NavigatorListener) {
        navigationListeners.add(listener)
    }

    /**
     * Removes the [listener]
     */
    fun removeNavigationListener(listener: NavigatorListener) {
        navigationListeners.remove(listener)
    }

    /**
     * Executes the commands via the navigator or waits until one is set
     */
    open fun executeCommands(vararg commands: Command) {
        commandBuffer.executeCommands(commands)
        navigationListeners.forEach { it(commands) }
    }

}

/**
 * Navigates forward to [key]
 */
fun Router.navigateTo(key: Any, data: Any? = null) {
    executeCommands(Forward(key, data))
}

/**
 * Pops back to root and goes forward to [key]
 */
fun Router.newScreenChain(key: Any, data: Any? = null) {
    executeCommands(
        BackTo(null),
        Forward(key, data)
    )
}

/**
 * Clears all screen and opens [key]
 */
fun Router.newRootScreen(key: Any, data: Any? = null) {
    executeCommands(
        BackTo(null),
        Replace(key, data)
    )
}

/**
 * Replaces the top screen with [key]
 */
fun Router.replaceScreen(key: Any, data: Any? = null) {
    executeCommands(Replace(key, data))
}

/**
 * Goes back to [key]
 */
fun Router.backTo(key: Any) {
    executeCommands(BackTo(key))
}

/**
 * Goes back to the root screen
 */
fun Router.backToRoot() {
    executeCommands(BackTo(null))
}

/**
 * Finishes the chain
 */
fun Router.finishChain() {
    executeCommands(
        BackTo(null),
        Back
    )
}

/**
 * Goes back to the previous screen
 */
fun Router.exit() {
    executeCommands(Back)
}

/**
 * Adds the listener which will be invoked when [sendResult] was called with [resultCode].
 */
fun Router.addResultListener(resultCode: Int, listener: ResultListener) {
    Results.addResultListener(resultCode, listener)
}

/**
 * Adds the listener which will be invoked when [sendResult] was called with [resultCode].
 */
@JvmName("addResultListenerTyped")
inline fun <reified T> Router.addResultListener(
    resultCode: Int,
    crossinline onResult: (T) -> Unit
): (Any) -> Unit {
    val listener: (Any) -> Unit = { onResult(it as T) }
    addResultListener(resultCode, listener)
    return listener
}

/**
 * Removes the [listener] with [resultCode]
 */
fun Router.removeResultListener(resultCode: Int, listener: ResultListener) {
    Results.removeResultListener(resultCode, listener)
}

/**
 * Sends the [result] with the [resultCode]
 */
fun Router.sendResult(resultCode: Int, result: Any) =
    Results.sendResult(resultCode, result)

/**
 * Goes back to the previous screen and sends the [result] with the [resultCode]
 */
fun Router.exitWithResult(resultCode: Int, result: Any) {
    exit()
    sendResult(resultCode, result)
}