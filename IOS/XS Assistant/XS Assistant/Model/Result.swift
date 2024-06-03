//
//  Result.swift
//  XS Assistant
//
//  Created by Already on 2024/4/18.
//

import Foundation

struct Result<T: Codable>: Codable{
    var timestamp: TimeInterval
    var status: String
    var message: String
    var data: T
}
