package com.application.architecture.views.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.application.architecture.R
import com.application.architecture.views.fragments.LoaderFragment
import com.application.architecture.views.utils.DisplayNotification
import com.application.architecture.views.utils.TinyDB
import com.application.architecture.views.viewmodels.BaseViewModel
import com.application.architecture.views.viewmodels.MainActivityViewModel
import com.bumptech.glide.Glide.init

abstract class BaseActivity : AppCompatActivity() {

    protected lateinit var tinydb: TinyDB
    protected lateinit var binding: ViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tinydb = TinyDB.instance

        init()


    }

    private fun init() {

        binding = DataBindingUtil.setContentView(this, getActivityLayout())

        getViewBinding()

    }

    abstract fun getActivityLayout(): Int

    abstract fun getViewBinding()


}