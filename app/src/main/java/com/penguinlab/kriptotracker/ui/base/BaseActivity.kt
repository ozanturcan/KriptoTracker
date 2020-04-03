package com.penguinlab.kriptotracker.ui.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


abstract class BaseActivity<DB : ViewDataBinding, VM : ViewModel> : DaggerAppCompatActivity() {
    @Inject
    internal lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    @Inject
    lateinit var firebaseAnalytics: FirebaseAnalytics

    lateinit var binding: DB
    lateinit var viewModel: VM
    abstract val viewModelClass: Class<VM>
    abstract val layoutRes: Int


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(viewModelClass)
    }

    override fun onResume() {
        super.onResume()
        firebaseAnalytics.setCurrentScreen(
            this,
            this.javaClass.canonicalName,
            this.javaClass.canonicalName
        )
    }

    fun loadFragment(
        containerId: Int,
        fragment: androidx.fragment.app.Fragment,
        addToBackStack: Boolean
    ) {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        if (addToBackStack) {
            ft.addToBackStack("")
        }
        ft.replace(containerId, fragment)
        ft.commit()
    }

    protected fun isScreenActive(fragmentClassName: String?): Boolean {
        return supportFragmentManager.fragments[0]::class.java.simpleName == fragmentClassName
    }

}