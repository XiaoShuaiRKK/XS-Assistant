//
//  ScrollOffsetKey.swift
//  XS Assistant
//
//  Created by Xiao Shuai on 2024/12/22.
//

import Foundation
import SwiftUICore

//用来获取滚动视图的偏移量
struct ScrollOffsetKey: PreferenceKey{
    typealias Value = CGFloat
    static var defaultValue: CGFloat = 0
    static func reduce(value: inout CGFloat,nextValue: () -> CGFloat){
        value = nextValue()
    }
}
