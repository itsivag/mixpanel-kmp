package com.suprbeta.kuri.analytics

import com.mixpanel.android.BuildConfig
import com.mixpanel.android.mpmetrics.MixpanelAPI
import org.json.JSONObject

actual class MixPanelAnalyticsTracker : AnalyticsTracker {
    private lateinit var mixpanel: MixpanelAPI

    actual override suspend fun init(token: String) {
        MixpanelAPI.getInstance(MixPanelAndroidContext.get(), "c3d382f1b21e986d8ae06bc1cd965013", true)
    }

    actual override fun trackEvent(event: String, properties: Map<String, Any>?) {
        if (BuildConfig.DEBUG.not()) {
            val json = JSONObject()
            properties?.forEach { (key, value) ->
                json.put(key, value)
            }
            mixpanel.track(event, json)
        }
    }
}