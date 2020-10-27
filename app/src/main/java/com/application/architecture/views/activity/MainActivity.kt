package com.application.architecture.views.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.application.architecture.R
import com.application.architecture.databinding.ActivityMainBinding
import com.application.architecture.views.fragments.LoaderFragment
import com.application.architecture.views.utils.DisplayNotification
import com.application.architecture.views.viewmodels.BaseViewModel
import com.application.architecture.views.viewmodels.MainActivityViewModel

class MainActivity : BaseActivity() {


    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mViewModel: MainActivityViewModel

    private lateinit var mNavController: NavController
    private lateinit var mNavDestination: NavDestination

    private val loaderFragment by lazy { LoaderFragment() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        init()


    }


    private fun init() {

        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        initNavController()

        observe()
    }

    private fun initNavController() {
        mNavController = findNavController(R.id.nav_host_fragment)

        mNavController.apply {

            addOnDestinationChangedListener { controller, destination, arguments ->
                mNavDestination = destination

            }

        }

    }

    override fun getActivityLayout() = R.layout.activity_main

    override fun getViewBinding() {
        mBinding = binding as ActivityMainBinding
    }

    private fun observe() {


        mViewModel.apply {
            loader.observe(this@MainActivity, Observer {
                it ?: return@Observer
                if (it) {

                    if (loaderFragment.isVisible) {
                        loaderFragment.dismiss()
                    }

                    loaderFragment.isCancelable = false
                    val ft = supportFragmentManager.beginTransaction()
                    val prev = supportFragmentManager.findFragmentByTag("dialog")
                    if (prev != null) {
                        ft.remove(prev)
                    }
                    ft.addToBackStack(null)
                    ft.let { loaderFragment.show(it, "dialog") }

                } else {
                    loaderFragment.dismiss()
                }

            })


            notificationMessage.observe(this@MainActivity, Observer {

                it ?: return@Observer

                if (it.show) {

                    DisplayNotification.show(
                        this@MainActivity,
                        mBinding.notificationLayout,
                        it.style,
                        it.message
                    )

                    setNotificationMessage(it.copy(show = false))
                }


            })
        }
    }

}