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

package com.ivianuu.traveler.android

import android.app.Activity
import com.ivianuu.traveler.BaseNavigator
import com.ivianuu.traveler.commands.Command
import com.ivianuu.traveler.commands.Forward
import com.ivianuu.traveler.commands.Replace

/**
 * Navigator for activities
 */
abstract class AppNavigator(activity: Activity) : BaseNavigator(),
    AppNavigatorHelper.Callback {

    private val appNavigatorHelper = AppNavigatorHelper(this, activity)

    override fun forward(command: Forward) {
        if (!appNavigatorHelper.forward(command)) {
            unknownScreen(command)
        }
    }

    override fun replace(command: Replace) {
        if (!appNavigatorHelper.replace(command)) {
            unknownScreen(command)
        }
    }

    override fun unknownScreen(command: Command) {
        throw IllegalArgumentException("unknown screen $command")
    }
}