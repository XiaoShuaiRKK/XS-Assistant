//
//  UserManger.swift
//  XS Assistant
//
//  Created by Already on 2024/4/17.
//

import Foundation

class UserManger{
    private let userDefault = UserDefaults.standard
    private let userKey = "MyAccount"
    private let tokenKey = "MyToken"
    
    static let shared = UserManger()
    var currentAccount: Account!{
        get{
            if let data = UserDefaults.standard.data(forKey: userKey){
                if let userInfo = try? JSONDecoder().decode(Account.self, from: data){
                    return userInfo
                }
            }
            return nil
        }set{
            if let encoded = try? JSONEncoder().encode(newValue){
                UserDefaults.standard.set(encoded, forKey: userKey)
            }
        }
    }
    var currentToken: String?{
        get{
            let token = UserDefaults.standard.string(forKey: tokenKey)
            return token == nil ? "" : token
        }set{
            if newValue != nil{
                UserDefaults.standard.set(newValue, forKey: tokenKey)
            }
        }
    }
    
    var emptyAccount: Account!{
        get{
            return Account(id: 0, firstName: "", lastName: "", email: "", password: "", birth: "", idNumber: "", areaId: 0, stateId: 0)
        }
    }
    
    func loginUser(email: String,password: String) async -> Account?{
        let path = "/account/login?nameOrEmail=" + email + "&password=" + password;
        let url = URL(string: ServerHttp.shared.getPath(path: path))!
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        let task = URLSession.shared.dataTask(with: request) { data, response, error in
            if error == nil{
                do{
                    let data = try JSONDecoder().decode(Result<ResultAccount>.self, from: data!)
                    UserManger.shared.currentAccount = data.data.customer
                    UserManger.shared.currentToken = data.data.token
                    print(UserManger.shared.currentAccount ?? "user nil")
                }catch{
                    print("Login error")
                    print(error)
                }
            }
        }
        task.resume()
        return self.currentAccount
    }
}
