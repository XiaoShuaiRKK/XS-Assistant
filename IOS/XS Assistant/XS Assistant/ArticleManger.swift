//
//  ArticleManger.swift
//  XS Assistant
//
//  Created by Already on 2024/6/5.
//

import Foundation

class ArticleManger: ObservableObject{
    static let shared = ArticleManger()
    @Published var message: String = ""
    
    func createArticle(title: String,subTitle: String,description: String,context: String){
        let article = CardInfo(id: "", title: title, subTitle: subTitle, context: context, authorId: UserManger.shared.currentAccount.idNumber, description: description,stateId: 0)
        let path = "/article/insert/addArticle"
        let url = URL(string: ServerHttp.shared.getPath(path: path))!
        var request = URLRequest(url: url)
        request.httpBody = try? JSONEncoder().encode(article)
        request.addValue(UserManger.shared.currentToken!, forHTTPHeaderField: "token")
        request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        request.httpMethod = "POST"
        let task = URLSession.shared.dataTask(with: request) { data, response, error in
            if error == nil{
                do{
                    let data = try JSONDecoder().decode(Result<Bool>.self, from: data!)
                    self.message = data.message
                }catch{
                    print("Article Insert error")
                    print(error)
                }
            }
        }
        task.resume()
    }
}
