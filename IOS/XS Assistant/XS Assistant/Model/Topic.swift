//
//  Topic.swift
//  TestApp
//
//  Created by Already on 2023/12/6.
//

import SwiftUI

struct Topic: Identifiable{
    let id = UUID()
    var title: String
    var icon: String
}

var topics = [
    Topic(title: "IOS Development", icon: "iphone"),
    Topic(title: "UI Design", icon: "eyedropper"),
    Topic(title: "Web Development", icon: "laptopcomputer")
]

var myInfoTopics = [
    Topic(title: "My Star", icon: "star.fill"),
    Topic(title: "My Liked", icon: "heart.fill")
]
