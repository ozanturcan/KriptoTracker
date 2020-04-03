package com.penguinlab.kriptotracker

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics


import timber.log.Timber

internal class CrashReportingTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, throwable: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }

        val t = throwable ?: Exception(message)

        // Crashlytics
        val firebaseInstance = FirebaseCrashlytics.getInstance()
        firebaseInstance.setCustomKey(CRASHLYTICS_KEY_PRIORITY, priority)
        firebaseInstance.setCustomKey(CRASHLYTICS_KEY_TITLE, CRASHLYTICS_KEY_TITLE)
        tag?.let {
            firebaseInstance.setCustomKey(CRASHLYTICS_KEY_TAG, it)
        }
        firebaseInstance.setCustomKey(CRASHLYTICS_KEY_MESSAGE, message)
        FirebaseCrashlytics.getInstance().recordException(t)

    }

    companion object {
        private const val CRASHLYTICS_KEY_TITLE = "UncaughtExceptionsHandler"
        private const val CRASHLYTICS_KEY_PRIORITY = "priority"
        private const val CRASHLYTICS_KEY_TAG = "tag"
        private const val CRASHLYTICS_KEY_MESSAGE = "message"
    }
}