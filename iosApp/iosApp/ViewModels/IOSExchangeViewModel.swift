//
// Created by Mohammadreza Rahmani on 25/02/2026.
//

import Foundation
import Shared

@MainActor
class IOSExchangeViewModel: ObservableObject {
    private let viewModel: ExchangeViewModel
    @Published var uiState: ExchangeUiState = ExchangeUiStateIdle()

    init() {
        self.viewModel = KoinHelperKt.getExchangeViewModel()
        observeState()
    }

    private func observeState() {
        Task {
            // SKIE creates a wrapper that conforms to AsyncSequence
            for await state in viewModel.uiState {
                self.uiState = state
            }
        }
    }

    func fetchRates(base: String) {
        viewModel.fetchRates(base: base)
    }
}
