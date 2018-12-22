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

/**
 * The object used to navigate around
 */
interface Router {

    /**
     * Whether or not a [Navigator] is currently set
     */
    val hasNavigator: Boolean

    /**
     * Sets the [navigator] which will be used to navigate
     */
    fun setNavigator(navigator: Navigator)

    /**
     * Removes the current [Navigator]
     */
    fun removeNavigator()

    /**
     * Sends the [commands] to the [Navigator]
     */
    fun enqueueCommands(vararg commands: Command)

    /**
     * Adds the [listener]
     */
    fun addRouterListener(listener: RouterListener)

    /**
     * Removes the previously added [listener]
     */
    fun removeRouterListener(listener: RouterListener)

}

/**
 * Returns a new [router] instance
 */
fun Router(): Router = RealRouter()