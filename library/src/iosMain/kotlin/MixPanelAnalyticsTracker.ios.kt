package com.suprbeta.kuri.analytics

import ios.SwiftMixpanelAnalyticsTrackerImpl
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual class MixPanelAnalyticsTracker : AnalyticsTracker {
    private val swiftMixPanel by lazy {
        SwiftMixpanelAnalyticsTrackerImpl()
    }

    actual override suspend fun init(token : String) {
        swiftMixPanel.initMixPanelWithToken(token)
    }

    @Suppress("UNCHECKED_CAST")
    actual override suspend fun trackEvent(
        event: String, properties: Map<String, Any>?
    ) {
        swiftMixPanel.trackEventWithEvent(
            event = event,
            properties = properties as Map<Any?, *>?
        )
    }
}