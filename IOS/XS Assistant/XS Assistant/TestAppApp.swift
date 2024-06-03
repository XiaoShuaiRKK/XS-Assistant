//
//  TestAppApp.swift
//  TestApp
//
//  Created by Already on 2023/11/17.
//

import SwiftUI

@main
struct TestAppApp: App {
    @StateObject var model = Model()
    var body: some Scene {
        WindowGroup {
            ContentView()
                .environmentObject(model)
        }
    }
}
