package com.suprbeta.kuri.analytics

import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("AnalyticsTracker", exact = true)
interface AnalyticsTracker {
    suspend fun init(token : String)
    suspend fun trackEvent(event: String, properties: Map<String, Any>? = emptyMap())
}