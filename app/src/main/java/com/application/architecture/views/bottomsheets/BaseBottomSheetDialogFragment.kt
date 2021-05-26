package com.application.architecture.views.bottomsheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.application.architecture.views.interfaces.InitMethods
import com.application.architecture.views.utils.TinyDB
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


internal abstract class BaseBottomSheetDialogFragment : BottomSheetDialogFragment(), InitMethods {


    protected lateinit var className: String
    protected lateinit var tinydb: TinyDB
    protected lateinit var binding: ViewDataBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        className = javaClass.name
        tinydb = TinyDB.instance

        getViewModel()

        observe()

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(
            inflater,
            getFragmentLayout(),
            container,
            false
        )

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewBinding()

        init()

        setLanguageData()

        setLiveDataValues()

        setListeners()

    }

}
