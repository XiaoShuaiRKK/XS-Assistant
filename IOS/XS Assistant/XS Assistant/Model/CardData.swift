//
//  UserData.swift
//  TestApp
//
//  Created by Already on 2023/11/22.
//

import Foundation


struct CardInfo: Identifiable,Codable{
    var id: String
    var title: String
    var subTitle: String
    var context: String
    var authorId: String
    var image: String?
    var background: String?
    var description: String
    var stateId: Int?
}

class CardInfoModel: ObservableObject{
    var emptyCard: CardInfo = CardInfo(id: "", title: "", subTitle: "", context: "", authorId: "", image: "", background: "", description: "")
    @Published var cards: [CardInfo] = []
    
    @Published var searchCards: [CardInfo] = []
    
    @MainActor
    func searchCardsLoad(target: String){
        let path = "/search/query/get/orderHot"
        let url = URL(string: ServerHttp.shared.getPath(path: path))!
        var request = URLRequest(url: url)
        request.addValue(UserManger.shared.currentToken!, forHTTPHeaderField: "token")
        request.httpMethod = "GET"
        _ = URLSession.shared.dataTask(with: request){ data, response, error in
            DispatchQueue.main.async{
                do{
                    print(String(decoding: data!, as: UTF8.self))
                    let data = try JSONDecoder().decode(Result<[CardInfo]>.self, from: data!)
                    self.searchCards = data.data
                }catch{
                    print("Search Article Error")
                    print(error)
                }
            }
        }
    }
    
    @MainActor func getSearch(target: String) -> [CardInfo]{
        searchCardsLoad(target: target)
        return searchCards
    }
    
    @MainActor
    func cardsLoad() async{
        let path = "/article/search/get/page?page=0&size=10"
        let url = URL(string: ServerHttp.shared.getPath(path: path))!
        var request = URLRequest(url: url)
        request.addValue(UserManger.shared.currentToken!, forHTTPHeaderField: "token")
        request.httpMethod = "GET"
        //请求主线程异步的请求方式 因为不建议除主线程外的其他线程修改UI
        let task = URLSession.shared.dataTask(with: request) { data, response, error in
            DispatchQueue.main.async{
                if error == nil{
                    do{
                        let data = try JSONDecoder().decode(Result<[CardInfo]>.self, from: data!)
                        self.cards = data.data
                        print("load all..")
                    }catch{
                        print("ArticleLoad error")
                        print(error)
                    }
                }
            }
        }
        task.resume()
    }
}

var courses = [
    CardInfo(id: "", title: "Hello New User", subTitle: "Nice to mee too", context: "", authorId: "", image: "Illustration 1", background: "Background 1", description: "welcome to xs")
]

var featuredCourses = [
    CardInfo(id: "", title: "Hello New User", subTitle: "Nice to mee too", context: "", authorId: "", image: "Illustration 1", background: "Background 1", description: "welcome to xs"),
    CardInfo(id: "", title: "Let Me Go", subTitle: "Nice to mee too", context: "", authorId: "", image: "Illustration 2", background: "Background 2", description: "welcome to xs"),
    CardInfo(id: "", title: "Welcome The XS", subTitle: "Nice to mee too", context: "", authorId: "", image: "Illustration 3", background: "Background 3", description: "welcome to xs"),
    CardInfo(id: "", title: "Today Task", subTitle: "Nice to mee too", context: "", authorId: "", image: "Illustration 4", background: "Background 4", description: "welcome to xs"),
    CardInfo(id: "", title: "Let Me Know You", subTitle: "Nice to mee too", context: "", authorId: "", image: "Illustration 5", background: "Background 5", description: "welcome to xs")
]




