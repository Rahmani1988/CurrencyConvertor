import SwiftUI

import Shared

@main
struct iOSApp: App {
    init() {
        Shared.KoinHelperKt.doInitKoinIos()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}