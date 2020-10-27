package com.application.architecture.views.utils

import android.content.Context
import android.graphics.Color
import android.os.CountDownTimer
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import com.application.architecture.R
import com.application.architecture.databinding.NotificationDialogBinding


object DisplayNotification {


    private const val NOTIFICATION_TIMER = 1000L

    private lateinit var mTimer: CountDownTimer

    enum class STYLE {

        INFO,
        SUCCESS,
        FAILURE

    }

    private fun setStyle(style: STYLE, binding: NotificationDialogBinding, message: String) {


        binding.apply {
            txtViewNotificationMessage.text = message

            when (style) {

                STYLE.FAILURE -> {
                    cardViewNotification.setCardBackgroundColor(Color.parseColor("#fe8083"))
                    imgViewNotificationIcon.setImageResource(R.drawable.icon_close_white_24dp)
                }

                STYLE.INFO -> {
                    cardViewNotification.setCardBackgroundColor(Color.parseColor("#7AC0FF"))
                    imgViewNotificationIcon.setImageResource(R.drawable.icon_info_outline_white_24dp)
                }

                STYLE.SUCCESS -> {
                    cardViewNotification.setCardBackgroundColor(Color.parseColor("#137BDE"))
                    imgViewNotificationIcon.setImageResource(R.drawable.icon_check_white_24dp)

                }

            }


            executePendingBindings()
        }

    }


    fun show(cxt: Context, binding: NotificationDialogBinding, style: STYLE, message: String) {


        if (binding.root.isVisible && binding.txtViewNotificationMessage.text.toString() == message) {
            mTimer.apply {
                cancel()
                start()
            }
            return
        } else if (binding.root.isVisible) {
            mTimer.apply {
                cancel()
                start()
            }
        }


        val animationEnter = AnimationUtils.loadAnimation(cxt, R.anim.notification_dialog_enter)
        val animationExit = AnimationUtils.loadAnimation(cxt, R.anim.notification_dialog_exit)

        setStyle(style, binding, message)


        if (!binding.root.isVisible) {
            binding.root.startAnimation(animationEnter)
        }


        animationEnter.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                binding.root.apply {
                    visibility = View.VISIBLE


                    mTimer = object : CountDownTimer(NOTIFICATION_TIMER, 1000) {
                        override fun onFinish() {
                            startAnimation(animationExit)
                        }

                        override fun onTick(millisUntilFinished: Long) {
                        }

                    }

                    mTimer.start()


//                    Handler().postDelayed({
//                        startAnimation(animationExit)
//                    }, NOTIFICATION_TIMER)
                }
            }

            override fun onAnimationStart(animation: Animation?) {
//                binding.root.visibility = View.VISIBLE
            }

        })

        animationExit.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                binding.root.visibility = View.GONE
            }

            override fun onAnimationStart(animation: Animation?) {
            }

        })
    }
}