//
// Created by Mohammadreza Rahmani on 25/02/2026.
//

import Foundation
import Shared

@MainActor
class IOSExchangeViewModel: ObservableObject {
    let viewModel: ExchangeViewModel
    @Published var uiState: ExchangeUiState = .idle

    init(repository: ExchangeRepository) {
        self.viewModel = ExchangeViewModel(repository: repository)
        observeState()
    }

    private func observeState() {
        // collecting the StateFlow from Kotlin
         Task {
             for await state in viewModel.uiState {
                 self.uiState = state
             }
         }
    }

    func fetchRates(base: String) {
        viewModel.fetchRates(base: base)
    }
}
