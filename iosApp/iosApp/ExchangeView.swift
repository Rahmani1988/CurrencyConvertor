import Shared
import SwiftUI

struct ExchangeView: View {
    @StateObject private var viewModel = IOSExchangeViewModel()

    var body: some View {
        NavigationStack {
            VStack {
                switch viewModel.uiState {
                case is ExchangeUiStateIdle:
                    IdleView { viewModel.fetchRates(base: "USD") }

                case is ExchangeUiStateLoading:
                    ProgressView("Fetching Rates...")

                case let success as ExchangeUiStateSuccess:
                    RateListView(data: success.data)

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
                    .frame(maxWidth: .infinity)
            }
            .buttonStyle(.borderedProminent)

            Text("Enter a currency to start")
                .foregroundColor(.secondary)
        }
    }
}

struct RateListView: View {
    let data: ExchangeResponse

    var body: some View {
        List {
            // Mapping the dictionary to a sorted array for the List
            let rates = data.conversionRates.sorted(by: { $0.key < $1.key })

            ForEach(rates, id: \.key) { currency, rate in
                HStack {
                    Text(currency)
                        .fontWeight(.bold)
                    Spacer()
                    Text("\(rate)")
                }
                .padding(.vertical, 4)
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
