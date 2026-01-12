package com.suprbeta.kuri.analytics

import com.mixpanel.android.BuildConfig
import com.mixpanel.android.mpmetrics.MixpanelAPI
import org.json.JSONObject

actual class MixPanelAnalyticsTracker : AnalyticsTracker {
    private lateinit var mixpanel: MixpanelAPI

    actual override suspend fun init(token: String) {
        mixpanel = MixpanelAPI.getInstance(MixPanelAndroidContext.get(), token, true)
    }

    actual override suspend fun trackEvent(event: String, properties: Map<String, Any>?) {
        if (BuildConfig.DEBUG.not()) {
            val json = JSONObject()
            properties?.forEach { (key, value) ->
                json.put(key, value)
            }
            mixpanel.track(event, json)
        }
    }
}