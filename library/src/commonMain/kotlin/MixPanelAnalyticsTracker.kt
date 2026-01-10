package com.suprbeta.kuri.analytics

expect class MixPanelAnalyticsTracker() : AnalyticsTracker {
    override suspend fun init(token : String)
    override fun trackEvent(
        event: String,
        properties: Map<String, Any>?
    )
}