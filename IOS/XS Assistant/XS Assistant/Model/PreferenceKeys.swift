//
//  PreferenceKeys.swift
//  TestApp
//
//  Created by Already on 2023/11/27.
//

import SwiftUI

struct ScrollPreferenceKey: PreferenceKey{
    static var defaultValue: CGFloat = 0
    static func reduce(value: inout CGFloat, nextValue: () -> CGFloat) {
        value = nextValue()
    }
}

//SignUpView GeometryReader
struct CirclePreferenceKey: PreferenceKey{
    static var defaultValue: CGFloat = 0
    static func reduce(value: inout CGFloat, nextValue: () -> CGFloat) {
        value = nextValue()
    }
}
