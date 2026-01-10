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

    actual override fun trackEvent(
        event: String, properties: Map<String, Any>?
    ) {
        //TODO: send properties
        swiftMixPanel.trackEventWithEvent(
            event = event,
            properties = null
        )
    }
}