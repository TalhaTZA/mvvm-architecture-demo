package com.application.architecture.views.fragments

import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.application.architecture.R
import com.application.architecture.databinding.FragmentDefaultBinding
import com.application.architecture.databinding.FragmentStartBinding
import com.application.architecture.views.viewmodels.MainActivityViewModel


internal class DefaultFragmentStart : BaseFragment() {

    private lateinit var mBinding: FragmentStartBinding
    private lateinit var mViewModel: MainActivityViewModel

    override fun init() {
        mViewModel.callServerFor()
    }

    override fun setListeners() {
    }

    override fun setLanguageData() {

    }

    override fun getFragmentLayout() = R.layout.fragment_start

    override fun getViewBinding() {
        mBinding = binding as FragmentStartBinding
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