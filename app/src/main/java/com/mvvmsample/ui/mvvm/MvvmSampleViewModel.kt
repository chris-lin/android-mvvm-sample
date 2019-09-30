package com.mvvmsample.ui.mvvm

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvvmsample.ui.mvvm.DataModelSample.onDataReadyCallback



class MvvmSampleViewModel : ViewModel() {
    val TAG = "MvvmSampleViewModel"
    val mData: ObservableField<String> = ObservableField()
//
//    val isLoading = ObservableBoolean(false)

    // ViewModel
    val isLoading = MutableLiveData<Boolean>();


    private val dataModel = DataModelSample()

    fun refresh() {
        Log.d(TAG, "hello refresh")

//        isLoading.set(true)
        isLoading.postValue(true)

//        // View
//        binding.setViewModel(viewModel);
//        binding.setLifecycleOwner(this);

        dataModel.retrieveData(object : onDataReadyCallback {
            override fun onDataReady(data: String) {
                mData.set(data)
//                isLoading.set(false)
                isLoading.postValue(false)
            }
        })
    }
}
