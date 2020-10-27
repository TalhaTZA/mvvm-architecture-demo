package com.application.architecture.views.fragments

import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.application.architecture.R
import com.application.architecture.databinding.FragmentDefaultBinding
import com.application.architecture.views.viewmodels.MainActivityViewModel


internal class DefaultFragment : BaseFragment() {

    private lateinit var mBinding: FragmentDefaultBinding
    private lateinit var mViewModel: MainActivityViewModel

    override fun init() {

    }

    override fun setListeners() {
    }

    override fun setLanguageData() {

    }

    override fun getFragmentLayout() = R.layout.fragment_default

    override fun getViewBinding() {
        mBinding = binding as FragmentDefaultBinding
    }

    override fun getViewModel() {
        mViewModel = ViewModelProviders.of(requireActivity()).get(MainActivityViewModel::class.java)
    }

    override fun observe() {
    }

    override fun setLiveDataValues() {

    }

    override fun onClick(v: View?) {

    }

}