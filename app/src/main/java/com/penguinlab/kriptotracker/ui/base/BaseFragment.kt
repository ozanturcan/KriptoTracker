package com.penguinlab.kriptotracker.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseFragment<DB : ViewDataBinding, VM : ViewModel> : DaggerFragment() {

    var disposables = CompositeDisposable()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var firebaseAnalytics: FirebaseAnalytics

    lateinit var viewModel: VM

    lateinit var binding: DB

    abstract val viewModelClass: Class<VM>
    abstract val layoutRes: Int


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding.lifecycleOwner = this
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        firebaseAnalytics.setCurrentScreen(
            requireActivity(),
            this.javaClass.canonicalName,
            this.javaClass.canonicalName
        )
    }

    override fun onDestroy() {
        if (!disposables.isDisposed) {
            disposables.clear()
        }
        super.onDestroy()
    }

}
