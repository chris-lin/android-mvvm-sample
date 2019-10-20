package com.mvvmsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MvvmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mvvm_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MvvmFragment.newInstance())
                .commitNow()
        }
    }

}
