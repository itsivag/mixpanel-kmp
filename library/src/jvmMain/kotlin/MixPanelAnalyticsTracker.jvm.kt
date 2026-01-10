package com.suprbeta.kuri.analytics

actual class MixPanelAnalyticsTracker : AnalyticsTracker {
    actual override fun trackEvent(
        event: String,
        properties: Map<String, Any>?
    ) {
    }
}