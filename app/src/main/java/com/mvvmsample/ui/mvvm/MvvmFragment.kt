package com.mvvmsample.ui.mvvm

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mvvmsample.R

class MvvmFragment : Fragment() {

    companion object {
        fun newInstance() = MvvmFragment()
    }

    private lateinit var viewModel: MvvmViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.mvvm_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MvvmViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
