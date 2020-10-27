package com.application.architecture.views.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.application.architecture.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}