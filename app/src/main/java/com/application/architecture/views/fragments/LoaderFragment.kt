package com.application.architecture.views.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.ViewModelProviders
import com.application.architecture.R
import com.application.architecture.databinding.FragmentLoaderBinding
import com.application.architecture.views.dialogs.BaseDialogFragment
import com.application.architecture.views.viewmodels.MainActivityViewModel


internal class LoaderFragment : BaseDialogFragment() {

    private lateinit var mBinding: FragmentLoaderBinding
    private lateinit var mViewModel: MainActivityViewModel

    override fun init() {

    }

    override fun setListeners() {
    }

    override fun setLanguageData() {

    }

    override fun getFragmentLayout() = R.layout.fragment_loader

    override fun getViewBinding() {
        mBinding = binding as FragmentLoaderBinding
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

    override fun onResume() {
        super.onResume()
        try {
            val lp = WindowManager.LayoutParams()
            val window = dialog!!.window
            window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            lp.copyFrom(window.attributes)
            //This makes the dialog take up the full width
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            window.attributes = lp
            window.statusBarColor = Color.WHITE
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            // getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}