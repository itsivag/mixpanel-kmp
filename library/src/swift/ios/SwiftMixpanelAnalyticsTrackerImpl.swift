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

    public func identifyDistinctId(_ distinctId: String) {
        let mixpanel = Mixpanel.mainInstance()
        mixpanel.identify(distinctId: distinctId)
    }

    public func setProfileProperties(_ properties: [AnyHashable: Any]) {
        let mixpanel = Mixpanel.mainInstance()
        let mpProperties: [String: MixpanelType] =
            properties.compactMap { (key, value) -> (String, MixpanelType)? in
                guard let stringKey = key as? String else { return nil }
                guard let mpValue = value as? MixpanelType else { return nil }
                return (stringKey, mpValue)
            }
            .reduce(into: [String: MixpanelType]()) { $0[$1.0] = $1.1 }
        mixpanel.people.set(properties: mpProperties)
    }
}
