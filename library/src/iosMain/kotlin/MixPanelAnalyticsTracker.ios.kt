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

    actual override suspend fun identify(distinctId: String) {
        swiftMixPanel.identifyDistinctId(distinctId)
    }

    @Suppress("UNCHECKED_CAST")
    actual override suspend fun setProfile(properties: Map<String, Any>) {
        swiftMixPanel.setProfileProperties(properties as Map<Any?, *>)
    }
}