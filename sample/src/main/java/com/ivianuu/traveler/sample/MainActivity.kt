package com.ivianuu.traveler.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ivianuu.traveler.android.AppNavigator
import com.ivianuu.traveler.lifecycle.setNavigator
import com.ivianuu.traveler.navigate
import com.ivianuu.traveler.sample.fragment.FragmentsKey
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val traveler by lazy { traveler("main") }
    private val navigatorHolder get() = traveler.navigatorHolder
    private val router get() = traveler.router

    private val navigator by lazy(LazyThreadSafetyMode.NONE) { AppNavigator(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragments.setOnClickListener { router.navigate(FragmentsKey()) }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(this, navigator)
    }
}