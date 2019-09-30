package com.mvvmsample.ui.mvvm

import android.os.Handler
import androidx.lifecycle.ViewModel


class DataModelSample : ViewModel() {
    fun retrieveData(callback: onDataReadyCallback) {
        Handler().postDelayed(Runnable { callback.onDataReady("New Data") }, 1500)
    }

    interface onDataReadyCallback {
        fun onDataReady(data: String)
    }

}
