package com.suprbeta.kuri.analytics

/**
 * A Kotlin Multiplatform implementation of [AnalyticsTracker] for Mixpanel analytics.
 *
 * This expect class provides a platform-agnostic API for tracking analytics events using Mixpanel.
 * The actual implementation is provided by platform-specific `actual` declarations for each target
 * (Android, iOS, etc.).
 *
 * ## Usage Example
 * ```kotlin
 * val tracker = MixPanelAnalyticsTracker()
 * tracker.init("your-mixpanel-token")
 * tracker.trackEvent("button_clicked", mapOf("button_name" to "submit"))
 * ```
 *
 * @constructor Creates a new instance of MixPanelAnalyticsTracker.
 * @see AnalyticsTracker
 */
expect class MixPanelAnalyticsTracker() : AnalyticsTracker {

    /**
     * Initializes the Mixpanel SDK with the provided project token.
     *
     * This method must be called before any tracking events are sent. It sets up the
     * Mixpanel instance with your project's unique token, which can be found in your
     * Mixpanel project settings.
     *
     * @param token The Mixpanel project token used to authenticate and route analytics
     *              data to the correct project. This token is unique to each Mixpanel project.
     * @throws IllegalStateException if initialization fails due to invalid token or configuration issues.
     */
    override suspend fun init(token: String)

    /**
     * Tracks a custom analytics event with optional properties.
     *
     * Use this method to record user actions, system events, or any custom events
     * you want to analyze in Mixpanel. Events can include additional context through
     * the properties parameter.
     *
     * ## Example
     * ```kotlin
     * // Track a simple event
     * tracker.trackEvent("page_viewed", null)
     *
     * // Track an event with properties
     * tracker.trackEvent("purchase_completed", mapOf(
     *     "item_id" to "SKU-123",
     *     "price" to 29.99,
     *     "currency" to "USD"
     * ))
     * ```
     *
     * @param event The name of the event to track. Use descriptive, consistent naming
     *              conventions (e.g., "button_clicked", "screen_viewed", "purchase_completed").
     * @param properties Optional map of key-value pairs providing additional context about
     *                   the event. Keys should be strings, and values can be any type that
     *                   Mixpanel supports (String, Number, Boolean, etc.). Pass `null` if
     *                   no additional properties are needed.
     */
    override fun trackEvent(
        event: String,
        properties: Map<String, Any>?
    )
}