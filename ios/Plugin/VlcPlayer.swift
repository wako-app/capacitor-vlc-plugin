import Foundation

@objc public class VlcPlayer: NSObject {
    @objc public func stream(_ value: String) -> String {
        print(value)
        return value
    }
}
