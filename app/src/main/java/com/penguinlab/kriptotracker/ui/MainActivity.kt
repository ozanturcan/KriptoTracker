package com.penguinlab.kriptotracker.ui

import android.os.Bundle
import com.penguinlab.common.runIfNull
import com.penguinlab.kriptotracker.R
import com.penguinlab.kriptotracker.databinding.ActivityMainBinding
import com.penguinlab.kriptotracker.ui.base.BaseActivity


class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {

    override val viewModelClass: Class<MainActivityViewModel> = MainActivityViewModel::class.java
    override val layoutRes: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState.runIfNull {
        }
    }


}