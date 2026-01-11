![Maven Central Version](https://img.shields.io/maven-central/v/io.github.itsivag/mixpanel-kmp)
# Mixpanel KMP

A Kotlin Multiplatform library for integrating Mixpanel analytics across Android and iOS platforms.

## Overview

`mixpanel-kmp` provides a unified, type-safe API for tracking analytics events with Mixpanel across Android and iOS. Built with Kotlin Multiplatform, it allows you to write your analytics tracking logic once and deploy it on both mobile platforms.

Note: This library is a Kotlin Multiplatform wrapper around the native Mixpanel SDKs for Android and iOS. It depends on the official Mixpanel SDK for Android and the Mixpanel SDK for iOS under the hood.

## Installation

Add the dependency to your `build.gradle.kts`:

```kotlin
commonMain {
    dependencies {
        implementation("io.github.itsivag:mixpanel-kmp:<latest-version>")
    }
}
```

## Setup

### Android Setup

**Important:** Before using the tracker on Android, you must initialize the Android context in your Application class.

Add the following to your Application class:

```kotlin
import android.app.Application
import com.suprbeta.kuri.analytics.MixPanelAndroidContext

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        // Initialize MixPanel Android context BEFORE creating any tracker instances
        MixPanelAndroidContext.setUp(this)
    }
}
```

Don't forget to register your Application class in `AndroidManifest.xml`:

```xml
<application
    android:name=".MyApplication"
    ...>
</application>
```

### iOS Setup

No additional setup is required for iOS.

## Usage
Initialize the tracker with your Mixpanel project token:

```kotlin
val tracker = MixPanelAnalyticsTracker()

// Initialize with your Mixpanel token
tracker.init("your-mixpanel-token")
```
### Tracking Events

Track simple events:

```kotlin
// Track a simple event without properties
tracker.trackEvent("page_viewed", null)
```

Track events with properties:

```kotlin
// Track an event with additional context
tracker.trackEvent("button_clicked", mapOf(
    "button_name" to "submit",
    "screen" to "login"
))

// Track a purchase event
tracker.trackEvent("purchase_completed", mapOf(
    "item_id" to "SKU-123",
    "price" to 29.99,
    "currency" to "USD",
    "quantity" to 2
))
```

### Complete Example

```kotlin
import com.suprbeta.kuri.analytics.MixPanelAnalyticsTracker

class AnalyticsManager {
    private val tracker = MixPanelAnalyticsTracker()
    
    suspend fun initialize(token: String) {
        tracker.init(token)
    }
    
    fun trackUserAction(action: String, metadata: Map<String, Any>? = null) {
        tracker.trackEvent(action, metadata)
    }
    
    fun trackScreenView(screenName: String) {
        tracker.trackEvent("screen_viewed", mapOf(
            "screen_name" to screenName,
            "timestamp" to System.currentTimeMillis()
        ))
    }
}
```
## Platform Support

| Platform | Status |
|----------|--------|
| Android  | Supported |
| iOS      | Supported |

## Requirements

- Kotlin 1.9.0 or higher
- Kotlin Multiplatform enabled project
- Valid Mixpanel project token
- **Android**: minSdk 24 or higher
- **iOS**: iOS 18.0 or higher

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## Support

For issues and questions:
- Open an issue on [GitHub Issues](https://github.com/itsivag/mixpanel-kmp/issues)
- Check [Mixpanel Documentation](https://developer.mixpanel.com/) for SDK-specific questions

**Find this repository useful? ❤️**
**[Follow me](https://github.com/itsivag)** for my next creations! 🤩
