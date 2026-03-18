//
//  AppRouter.swift
//  iosApp
//
//  Created by Mohammadreza Rahmani on 18/03/2026.
//
import SwiftUI

class AppRouter: ObservableObject {
    @Published var path = NavigationPath()

    func navigate(to route: AppRoute) {
        path.append(route)
    }

    func pop() {
        path.removeLast()
    }

    func popToRoot() {
        path.removeLast(path.count)
    }
}
