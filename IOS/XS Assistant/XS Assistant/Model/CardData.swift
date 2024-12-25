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
    var authorName: String?
    var authorId: String
    var image: String?
    var background: String?
    var description: String
    var stateId: Int?
}

class CardInfoModel: ObservableObject{
    var emptyCard: CardInfo = CardInfo(id: "", title: "", subTitle: "", context: "", authorId: "", image: "", background: "", description: "")
    var page: Int = 0
    var size: Int = 10
    var myPage: Int = 0
    var mySize: Int = 10
    var searchPage: Int = 1
    var searchSize: Int = 5
    @Published var cards: [CardInfo] = []
    
    @Published var searchCards: [CardInfo] = []
    @Published var myCards: [CardInfo] = []
    
    @MainActor
    func searchCardsLoad(target: String) async{
        let path = "/article/search/get/orderHot?target=\(target)&page=\(searchPage)&size=\(searchSize)"
        let url = URL(string: ServerHttp.shared.getPath(path: path))!
        var request = URLRequest(url: url)
        request.addValue(UserManger.shared.currentToken!, forHTTPHeaderField: "token")
        request.httpMethod = "GET"
        do{
            let (data, _) = try await URLSession.shared.data(for: request)
            let dataResponse = try JSONDecoder().decode(Result<[CardInfo]>.self, from: data)
            print("Data == ")
            print(dataResponse)
            self.searchCards = dataResponse.data
        }catch{
            print("Search Article Error")
            print(error)
        }
//        _ = URLSession.shared.dataTask(with: request){ data, response, error in
//            DispatchQueue.main.async{
//                
//            }
//        }
    }
    
    @MainActor func getSearch(target: String) async -> [CardInfo]{
        await searchCardsLoad(target: target)
        return searchCards
    }
    
    @MainActor
    func cardsLoad() async{
        if page == 0 {
            page = 1
        }
        let path = "/article/search/get/page?page=\(page)&size=\(size)"
        let url = URL(string: ServerHttp.shared.getPath(path: path))!
        var request = URLRequest(url: url)
        request.addValue(UserManger.shared.currentToken!, forHTTPHeaderField: "token")
        request.httpMethod = "GET"
        do{
            let (data, _) = try await URLSession.shared.data(for: request)
            let dataResponse = try JSONDecoder().decode(Result<[CardInfo]>.self, from: data)
            self.cards = dataResponse.data
            print("load all..")
        }catch{
            print("ArticleLoad error")
            print(error)
        }
        //请求主线程异步的请求方式 因为不建议除主线程外的其他线程修改UI
//        let task = URLSession.shared.dataTask(with: request) { data, response, error in
//            DispatchQueue.main.async{
//                if error == nil{
//                    do{
//                        let data = try JSONDecoder().decode(Result<[CardInfo]>.self, from: data!)
//                        self.cards = data.data
//                        print("load all..")
//                    }catch{
//                        print("ArticleLoad error")
//                        print(error)
//                    }
//                }
//            }
//        }
//        task.resume()
    }
    
    @MainActor
    func addPageCardsLoad() async {
        page += 1
        let path = "/article/search/get/page?page=\(page)&size=\(size)"
        let url = URL(string: ServerHttp.shared.getPath(path: path))!
        var request = URLRequest(url: url)
        request.addValue(UserManger.shared.currentToken!, forHTTPHeaderField: "token")
        request.httpMethod = "GET"
        do{
            let (data, _) = try await URLSession.shared.data(for: request)
            let dataResponse = try JSONDecoder().decode(Result<[CardInfo]>.self, from: data)
            if dataResponse.data.isEmpty {
                page -= 1
            }else{
                self.cards += dataResponse.data
                print("load all..")
            }
        }catch{
            print("ArticleLoad error")
            print(error)
        }
        //请求主线程异步的请求方式 因为不建议除主线程外的其他线程修改UI
//        let task = URLSession.shared.dataTask(with: request) { data, response, error in
//            DispatchQueue.main.async{
//                if error == nil{
//                    do{
//                        let data = try JSONDecoder().decode(Result<[CardInfo]>.self, from: data!)
//                        self.cards = data.data
//                        print("load all..")
//                    }catch{
//                        print("ArticleLoad error")
//                        print(error)
//                    }
//                }
//            }
//        }
//        task.resume()
    }
    
    @MainActor
    func myCardsLoadByIdNumber(idNumber: String) async {
        myPage += 1
        let path = "/article/search/get/by/idNumber?id_number=\(idNumber)&page=\(myPage)&size=\(mySize)"
        let url = URL(string: ServerHttp.shared.getPath(path: path))!
        var request = URLRequest(url: url)
        request.addValue(UserManger.shared.currentToken!, forHTTPHeaderField: "token")
        request.httpMethod = "GET"
        do{
            let (data, _) = try await URLSession.shared.data(for: request)
            let dataResponse = try JSONDecoder().decode(Result<[CardInfo]>.self, from: data)
            if dataResponse.data.isEmpty {
                myPage -= 1
            }else{
                self.myCards += dataResponse.data
                print("MyArticles load all..")
            }
        }catch{
            print("My Articles　Load error")
            print(error)
        }
        //请求主线程异步的请求方式 因为不建议除主线程外的其他线程修改UI
//        let task = URLSession.shared.dataTask(with: request) { data, response, error in
//            DispatchQueue.main.async{
//                if error == nil{
//
//                }
//            }
//        }
//        task.resume()
    }
    
    func resetMyCardPage(){
        myPage = 0
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




