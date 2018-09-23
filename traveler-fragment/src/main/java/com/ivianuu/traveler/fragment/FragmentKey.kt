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

package com.ivianuu.traveler.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.ivianuu.traveler.Command
import kotlin.reflect.KClass

/**
 * A key which holds a [Fragment]
 */
interface FragmentKey {

    fun createFragment(data: Any?): Fragment

    fun getFragmentTag() = toString()

    fun setupFragmentTransaction(
        command: Command,
        currentFragment: Fragment?,
        nextFragment: Fragment,
        transaction: FragmentTransaction
    ) {
    }
}

/**
 * Returns a new [FragmentKey]
 */
fun FragmentKey(fragment: Fragment) = object : FragmentKey {
    override fun createFragment(data: Any?) = fragment
}

/**
 * Returns a new [FragmentKey]
 */
fun FragmentKey(clazz: KClass<out Fragment>) = object : FragmentKey {
    override fun createFragment(data: Any?) = clazz.java.newInstance()
}

/**
 * Returns a new [FragmentKey]
 */
inline fun <reified T : Fragment> FragmentKey() = FragmentKey(T::class)