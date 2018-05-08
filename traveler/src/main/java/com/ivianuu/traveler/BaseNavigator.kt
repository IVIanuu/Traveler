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

import com.ivianuu.traveler.commands.*

/**
 * Base navigator which processes commands and maps them to functions
 */
abstract class BaseNavigator : Navigator {

    override fun applyCommands(commands: Array<Command>) {
        commands.forEach(this::applyCommand)
    }

    protected open fun applyCommand(command: Command) {
        when(command) {
            is Back -> back(command)
            is BackTo -> backTo(command)
            is Forward -> forward(command)
            is Replace -> replace(command)
        }
    }

    protected open fun forward(command: Forward) {
        throw IllegalArgumentException("unsupported command $command")
    }

    protected open fun replace(command: Replace) {
        throw IllegalArgumentException("unsupported command $command")
    }

    protected open fun back(command: Back) {
        throw IllegalArgumentException("unsupported command $command")
    }

    protected open fun backTo(command: BackTo) {
        throw IllegalArgumentException("unsupported command $command")
    }

}