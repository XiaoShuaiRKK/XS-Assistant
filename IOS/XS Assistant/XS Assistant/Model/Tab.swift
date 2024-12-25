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
    TabItem(text: "My Articles", icon: "pencil.circle.fill", tab: .CREATE, color: .red),
    TabItem(text: "Chat", icon: "bubble.fill", tab: .CHAT, color: .pink)
]

enum Tab: String{
    case HOME
    case EXPLORE
    case CREATE
    case CHAT
}

struct TabPreferenceKey: PreferenceKey{
    static var defaultValue: CGFloat = 0
    static func reduce(value: inout CGFloat, nextValue: () -> CGFloat) {
        value = nextValue()
    }
}
