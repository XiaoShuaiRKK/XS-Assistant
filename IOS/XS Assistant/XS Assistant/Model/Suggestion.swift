//
//  Suggestion.swift
//  TestApp
//
//  Created by Already on 2023/12/1.
//

import SwiftUI

struct Suggestion: Identifiable{
    let id = UUID()
    var text: String
}

var suggestionsData = [
    Suggestion(text: "SwiftUI"),
    Suggestion(text: "React"),
    Suggestion(text: "Hello")
]
