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

package com.ivianuu.traveler.sample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.ivianuu.traveler.android.FragmentNavigator

class FragmentsActivity : AppCompatActivity() {

    private val traveler get() = getTraveler("fragments")

    private val fragmentNavigator by lazy(LazyThreadSafetyMode.NONE) {
        object : FragmentNavigator(supportFragmentManager, android.R.id.content) {

            override fun createFragment(key: Any, data: Any?): Fragment? {
                return CounterFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable("key", key as CounterKey)
                    }
                }
            }

            override fun exit() {
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            traveler.router.newRootScreen(CounterKey(1))
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        traveler.navigatorHolder.setNavigator(fragmentNavigator)
    }

    override fun onPause() {
        traveler.navigatorHolder.removeNavigator()
        super.onPause()
    }
}