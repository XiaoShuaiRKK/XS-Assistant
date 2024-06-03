//
//  Account.swift
//  XS Assistant
//
//  Created by Already on 2024/4/16.
//

import Foundation

struct Account: Codable{
    var id: Int?
    var firstName: String
    var lastName: String
    var email: String
    var password: String?
    var birth: String
    var idNumber: String
    var areaId: Int?
    var stateId: Int?
}
