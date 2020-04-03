package com.penguinlab.data.di.module

import android.app.Application
import androidx.annotation.NonNull
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAnalytics(@NonNull application: Application): FirebaseAnalytics {
        return FirebaseAnalytics.getInstance(application)
    }

}
