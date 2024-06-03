//
//  Model.swift
//  TestApp
//
//  Created by Already on 2023/11/29.
//

import SwiftUI
import Combine

class Model: ObservableObject{
    @Published var showDetail: Bool = false
    //Modal
    @Published var selectedModal: Modal = .signIn
    @Published var showModal: Bool = false
    @Published var dismissModal: Bool = false
}

enum Modal: String{
    case signUp
    case signIn
}
