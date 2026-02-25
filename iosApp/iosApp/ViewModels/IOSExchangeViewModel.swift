//
// Created by Mohammadreza Rahmani on 25/02/2026.
//

import Foundation
import Shared

@MainActor
class IOSExchangeViewModel: ObservableObject {
    let wrapped: ExchangeViewModel
    @Published var state: ExchangeUiState = ExchangeUiStateIdle()

    init(repository: ExchangeRepository) {
        self.wrapped = ExchangeViewModel(repository: repository)

        // Start observing the Kotlin StateFlow
        Task {

        }
    }

    func fetchRates(base: String) {
        wrapped.fetchRates(base: base)
    }
}
