package com.mvvmsample

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.mvvmsample.viewmodel.ProfileLiveDataViewModel
import com.mvvmsample.databinding.MvvmFragmentBinding

class MvvmFragment : Fragment() {
    val TAG = "MvvmFragment"

    companion object {
        fun newInstance() = MvvmFragment()
    }

    //    private lateinit var viewModel: MvvmViewModel
    private lateinit var viewModel: ProfileLiveDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")


        viewModel = ViewModelProviders.of(this).get(ProfileLiveDataViewModel::class.java)
//        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
//            setIndex(arguments?.getInt(PlaceholderFragment.ARG_SECTION_NUMBER) ?: 1)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView")

        var binding: MvvmFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.mvvm_fragment, container, false)

        // Bind layout with ViewModel
        binding.viewmodel = viewModel

        // LiveData needs the lifecycle owner
        binding.lifecycleOwner = this
        val view = binding.root

        // ignore
//        val nameView: TextView = view.findViewById(R.id.name)
//        val likesView: TextView = view.findViewById(R.id.likes)
//        viewModel.name.observe(this, Observer<String> {
//            nameView.text = it
//        })
//        viewModel.likes.observe(this, Observer<Int> {
//            likesView.text = it.toString()
//        })
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated")
    }

}
