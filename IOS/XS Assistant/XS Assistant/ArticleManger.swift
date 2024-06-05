//
//  ArticleManger.swift
//  XS Assistant
//
//  Created by Already on 2024/6/5.
//

import Foundation

class ArticleManger{
    static let shared = ArticleManger()
    
    func createArticle(title: String,subTitle: String,description: String,context: String) -> Bool{
        var isSuccess = false
        var article = CardInfo(id: "", title: title, subTitle: subTitle, context: context, authorId: UserManger.shared.currentAccount.idNumber, image: "", background: "", description: description)
        let path = "/article/insert/addArticle"
        let url = URL(string: ServerHttp.shared.getPath(path: path))!
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.httpBody = try? JSONSerialization.data(withJSONObject: article)
        let task = URLSession.shared.dataTask(with: request) { data, response, error in
            DispatchQueue.global(qos: .default).async{
                if error == nil{
                    do{
                        let data = try JSONDecoder().decode(Result<Bool>.self, from: data!)
                        isSuccess = data.data
                    }catch{
                        print("Article Insert error")
                        print(error)
                    }
                }
            }
        }
        task.resume()
        return isSuccess
    }
}
