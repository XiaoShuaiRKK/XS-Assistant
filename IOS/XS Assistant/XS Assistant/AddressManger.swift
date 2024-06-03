//
//  AddressManger.swift
//  XS Assistant
//
//  Created by Already on 2024/4/19.
//

import Foundation

class AddressManger{
    private let userDefault = UserDefaults.standard
    private var addressList: [Address] = [Address(id: 0, areaName: "", areaNameChinese: ""),Address(id: 0, areaName: "", areaNameChinese: "")]
    static let shared = AddressManger()
    var addressInfo: [Address]{
        get{
            return addressList
        }set{
            addressList = newValue
        }
    }
    
    func getAddress(id: Int) -> Address{
        if(id == 0){
            return addressList[0]
        }
        if(addressInfo.count < 3){
            let path = "/area/info/getAll"
            let url = URL(string: ServerHttp.shared.getPath(path: path))!
            var request = URLRequest(url: url)
            request.addValue(UserManger.shared.currentToken!, forHTTPHeaderField: "token")
            request.httpMethod = "GET"
            let task = URLSession.shared.dataTask(with: request) { data, response, error in
                DispatchQueue.global(qos: .default).async{
                    if error == nil{
                        do{
                            print("Load Address")
                            let data = try JSONDecoder().decode(Result<[Address]>.self, from: data!)
                            self.addressInfo = data.data
                            print(data)
                            print(data.data)
                        }catch{
                            print("AddressManger error")
                            print(error)
                        }
                    }
                }
            }
            task.resume()
        }
        return addressInfo[id - 1]
    }
}
