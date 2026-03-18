//
//  DetailsView.swift
//  iosApp
//
//  Created by Mohammadreza Rahmani on 18/03/2026.
//
import SwiftUI

struct DetailsView: View {
    let currency: String
    
    var body: some View {
        VStack {
            Text("Details for \(currency)")
                .font(.largeTitle)
        }
        .navigationTitle(currency)
    }
}
