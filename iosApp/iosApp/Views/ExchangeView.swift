import Shared
import SwiftUI

struct ExchangeView: View {
    @StateObject private var viewModel = IOSExchangeViewModel()
    @EnvironmentObject var router: AppRouter

    var body: some View {
        NavigationStack {
            VStack {
                switch viewModel.uiState {
                case is ExchangeUiStateIdle:
                    IdleView { viewModel.fetchRates(base: "USD") }

                case is ExchangeUiStateLoading:
                    ProgressView("Fetching Rates...")

                case let success as ExchangeUiStateSuccess:
                    RateListView(data: success.data) { currencyCode in
                        router.navigate(to: .details(currencyCode: currencyCode))
                    }

                case let error as ExchangeUiStateError:
                    ContentUnavailableView(
                        "Error",
                        systemImage: "exclamationmark.triangle",
                        description: Text(error.message)
                    )

                default:
                    EmptyView()
                }
            }
            .navigationTitle("Exchange Rates")
            .padding()
        }
    }
}

// MARK: - Subviews

struct IdleView: View {
    var onFetch: () -> Void

    var body: some View {
        VStack(spacing: 16) {
            Button(action: onFetch) {
                Text("Fetch USD Rates")
            }
            .buttonStyle(.borderedProminent)

            Text("Tap the button to start")
                .foregroundColor(.secondary)
        }
    }
}

struct RateListView: View {
    let data: ExchangeResponse
    var onSelect: (String) -> Void  // Callback for navigation

    var body: some View {
        List {
            let rates = data.conversionRates.sorted(by: { $0.key < $1.key })

            ForEach(rates, id: \.key) { currency, rate in
                Button(action: { onSelect(currency) }) {
                    HStack {
                        Text(currency)
                            .fontWeight(.bold)
                        Spacer()
                        Text("\(rate)")
                            .foregroundColor(.secondary)
                    }
                    .padding(.vertical, 8)
                    .contentShape(Rectangle())
                }
                .buttonStyle(.plain)  // Keeps the row looking like a standard list item
            }
        }
        .listStyle(.plain)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        IdleView {}
    }
}
