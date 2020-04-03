package com.penguinlab.kriptotracker

import android.app.Activity
import android.app.Application
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.penguinlab.kriptotracker.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber.*
import javax.inject.Inject


class Application : Application(), HasActivityInjector {

    @Inject
    internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }


    override fun onCreate() {
        super.onCreate()
        Thread.setDefaultUncaughtExceptionHandler { _, throwable ->
            // do something here - log to file and upload to    server/close resources/delete files...
            e(throwable)
        }
        DaggerAppComponent
            .builder()
            .app(this)
            .create(this)
            .inject(this)

        plant(
            if (BuildConfig.DEBUG)
                DebugTree()
            else
                CrashReportingTree()
        )

        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)

    }
}