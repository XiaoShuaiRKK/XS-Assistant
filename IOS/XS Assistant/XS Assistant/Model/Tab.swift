//
//  Tab.swift
//  TestApp
//
//  Created by Already on 2023/11/27.
//

import SwiftUI

struct TabItem: Identifiable{
    var id = UUID()
    var text: String
    var icon: String
    var tab: Tab
    var color: Color
}

var tabItems = [
    TabItem(text: "Home", icon: "house", tab: .HOME,color: .teal),
    TabItem(text: "Explore", icon: "magnifyingglass", tab: .EXPLORE, color: .blue),
    TabItem(text: "Notifications", icon: "bell", tab: .NOTIFICATIONS, color: .red),
    TabItem(text: "Library", icon: "rectangle.stack", tab: .LIBRARY, color: .pink)
]

enum Tab: String{
    case HOME
    case EXPLORE
    case NOTIFICATIONS
    case LIBRARY
}

struct TabPreferenceKey: PreferenceKey{
    static var defaultValue: CGFloat = 0
    static func reduce(value: inout CGFloat, nextValue: () -> CGFloat) {
        value = nextValue()
    }
}
