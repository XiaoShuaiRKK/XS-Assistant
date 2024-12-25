//
//  ServerHttp.swift
//  XS Assistant
//
//  Created by Already on 2024/5/17.
//

import Foundation

class ServerHttp{
    static let shared = ServerHttp()
    let server: String = "http://172.16.10.109:20577/xs_assistant"
    func getPath(path: String) -> String{
        return server + path
    }
}
