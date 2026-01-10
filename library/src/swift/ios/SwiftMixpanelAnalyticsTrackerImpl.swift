//
// Created by Siva G on 10/01/26.
//

import Foundation
import Mixpanel

@objcMembers public class SwiftMixpanelAnalyticsTrackerImpl: NSObject {
    public func initMixPanel(token: String) {
        Mixpanel.initialize(token: token, trackAutomaticEvents: true)
    }

    public func trackEvent(event: String, properties: [String: Any]?) {
        let mixpanel = Mixpanel.mainInstance()
        mixpanel.loggingEnabled = true

        guard let properties else {
            mixpanel.track(event: event)
            return
        }

        let mpProperties: [String: MixpanelType] =
            properties.compactMapValues {
                $0 as? MixpanelType
            }

        mixpanel.track(event: event, properties: mpProperties)
    }
}
