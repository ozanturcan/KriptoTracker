package com.penguinlab.kriptotracker.di.component

import com.penguinlab.kriptotracker.di.module.ActivityBuilderModule
import com.penguinlab.kriptotracker.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<com.penguinlab.kriptotracker.Application> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<com.penguinlab.kriptotracker.Application>() {
        @BindsInstance
        abstract fun app(application: android.app.Application): Builder
    }
}