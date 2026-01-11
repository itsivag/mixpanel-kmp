package com.suprbeta.kuri.analytics

import android.app.Application
import android.content.Context

/**
 * Android-specific context provider for Mixpanel initialization.
 *
 * This object holds the Application context required by the Android Mixpanel SDK.
 * **Important:** You must call [setUp] in your Application class's `onCreate()` method
 * before creating any [MixPanelAnalyticsTracker] instances.
 *
 * ## Setup Instructions
 *
 * Add the following to your Application class:
 *
 * ```kotlin
 * class MyApplication : Application() {
 *     override fun onCreate() {
 *         super.onCreate()
 *
 *         // Initialize MixPanel context BEFORE creating MixPanelAnalyticsTracker
 *         MixPanelAndroidContext.setUp(this)
 *
 *         // Now you can safely create and use the tracker from CommonMain
 *         val tracker = MixPanelAnalyticsTracker()
 *         tracker.init("your-mixpanel-token")
 *     }
 * }
 * ```
 *
 * @see MixPanelAnalyticsTracker
 */
object MixPanelAndroidContext {
    private lateinit var application: Application

    /**
     * Initializes the Android context for Mixpanel.
     *
     * This method must be called once in your Application's `onCreate()` before
     * any Mixpanel tracking operations are performed.
     *
     * @param context The Application context. Must be an [Application] instance.
     * @throws ClassCastException if the provided context is not an Application instance.
     */
    fun setUp(context: Context) {
        application = context as Application
    }

    /**
     * Retrieves the Application context for Mixpanel SDK initialization.
     *
     * This method is used internally by [MixPanelAnalyticsTracker] to obtain the
     * context required by the Android Mixpanel SDK.
     *
     * @return The Application context.
     * @throws Exception if [setUp] has not been called before this method.
     */
    fun get(): Context {
        if (::application.isInitialized.not()) throw Exception("MixPanel Android Application context isn't initialized")
        return application.applicationContext
    }
}