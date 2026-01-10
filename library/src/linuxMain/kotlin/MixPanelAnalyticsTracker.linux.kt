package com.suprbeta.kuri.analytics

actual class MixPanelAnalyticsTracker : com.suprbeta.kuri.analytics.AnalyticsTracker {
    actual override fun trackEvent(
        event: String,
        properties: Map<String, Any>?
    ) {
    }
}