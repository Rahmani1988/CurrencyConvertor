import SwiftUI

import Shared

@main
struct iOSApp: App {
    
    @StateObject private var router = AppRouter()
    
    init() {
        Shared.KoinHelperKt.doInitKoinIos()
    }
    
    var body: some Scene {
            WindowGroup {
                // Pass the path from the router to the NavigationStack
                NavigationStack(path: $router.path) {
                    ExchangeView()
                        .navigationDestination(for: AppRoute.self) { route in
                            switch route {
                            case .exchange:
                                ExchangeView()
                            case .details(let code):
                                DetailsView(currency: code)
                            }
                        }
                }
                .environmentObject(router)
            }
        }
}
