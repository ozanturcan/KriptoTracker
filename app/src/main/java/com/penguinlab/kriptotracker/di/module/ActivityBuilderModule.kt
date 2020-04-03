package com.penguinlab.kriptotracker.di.module

import com.penguinlab.kriptotracker.di.scope.ActivityScope
import com.penguinlab.kriptotracker.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun bindMainActivity(): MainActivity
}