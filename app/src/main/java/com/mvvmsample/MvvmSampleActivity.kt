package com.mvvmsample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.mvvmsample.databinding.MvvmSampleActivityBinding
import com.mvvmsample.ui.mvvm.MvvmSampleViewModel
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.mvvmsample.ui.ObservableFieldActivity
import com.mvvmsample.ui.ViewModelActivity


class MvvmSampleActivity : AppCompatActivity() {

    private var btnRefresh: Button? = null

    private var viewModel = MvvmSampleViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MvvmSampleViewModel::class.java)
//        val binding = DataBindingUtil.setContentView(this, R.layout.mvvm_sample_activity)
        val binding: MvvmSampleActivityBinding = DataBindingUtil.setContentView(
            this, R.layout.mvvm_sample_activity)

        binding.observableFieldsActivityButton.setOnClickListener {
            startActivity(Intent(this, ObservableFieldActivity::class.java))
        }
        binding.viewmodelActivityButton.setOnClickListener {
            startActivity(Intent(this, ViewModelActivity::class.java))
        }

//        binding.viewModel = viewModel
//        viewModel
//        binding!!.btnRefresh.setOnClickListener { viewModel.refresh() }

        // TODO: 使用Data Binding訂閱ViewModel中的資料以更新畫面
    }

}
