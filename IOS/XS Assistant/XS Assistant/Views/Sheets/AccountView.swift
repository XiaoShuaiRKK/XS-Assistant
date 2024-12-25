//
//  AccountView.swift
//  TestApp
//
//  Created by Already on 2023/11/23.
//

import SwiftUI

struct AccountView: View {
    @State var isDeleted = false
    @State var isPinned = false
    @State var signInStatus: [Bool] = [false,false,false,false,false,false,false]
    var account: Account = UserManger.shared.currentAccount ?? UserManger.shared.emptyAccount
    @Environment(\.dismiss) var dismiss
    @AppStorage("isLogged") var isLogged = false
    @AppStorage("weekSignInStatus") var weekSignInStatusString: String = "0000000"
    @ObservedObject var coinModael = CoinModel()
    @ObservedObject var addressManger = AddressManger.shared
    //用户经验值百分比
    var pointProgress: CGFloat{
        CGFloat((account.pointsLevel?.points)!) / CGFloat((account.pointsLevel?.nextPoints)!)
    }
    
    func fetchAddress() async{
        AddressManger.shared.getAddress(id: account.areaId!)
    }
    
    var body: some View {
        NavigationView {
            List {
                AccountInfo
                AccountPointLevel
                    .accentColor(.primary)
                AccountSignInStatus
                AccountMenu
                    .accentColor(.primary)
                AccountLinks
                
                AccountCoins
                
                Button{
                    isLogged = false
                    dismiss()
                }label: {
                    Text("Sign out")
                        .tint(.red)
                }
            }
            .task {
                await fetchAddress()
                await coinModael.fetchCoins()
                //获取这个星期的登陆情况
                loadSignInStatus()
            }
            .refreshable {
                await fetchAddress()
                await coinModael.fetchCoins()
            }
            .listStyle(.insetGrouped)
            .navigationTitle("Account")
            .navigationBarItems(trailing: Button { dismiss() } label: {
                Text("Done").bold()
            })
        }
    }
    
    var AccountInfo: some View{
        VStack(spacing: 5) {
            if (account.iconPath != nil) {
                AsyncImage(url: URL(string: account.iconPath!)) { phase in
                    switch phase {
                    case .empty:
                        ProgressView()
                            .progressViewStyle(CircularProgressViewStyle())
                            .foregroundColor(.blue)
                    case .success(let image):
                        image
                            .resizable()
                            .scaledToFill()
                            .clipShape(Circle())
                            .overlay {
                                Circle().stroke(Color.white,lineWidth: 2)
                            }
                            .padding(10)
                    case .failure(_):
                        Image(systemName: "exclamationmark.triangle.fill")
                            .foregroundColor(.red)
                            .padding()
                    @unknown default:
                        EmptyView()
                    }
                }
                .frame(width: 100,height: 100)
            }else{
                Image(systemName: "person.crop.circle.fill.badge.checkmark")
                    .symbolVariant(.circle.fill)
                    .font(.system(size: 24))
                    .foregroundStyle(.blue,.blue.opacity(0.3))
                    .padding()
                    .background(Circle().fill(.ultraThinMaterial))
                    .background(
                        BlobView()
                            .scaleEffect(0.7)
                    )
            }
            
            Text(account.firstName  + " " + account.lastName)
                .font(.title.weight(.semibold))
            HStack {
                Image(systemName: "location.circle.fill")
                    .foregroundColor(.blue)
                Text(addressManger.addressInfo[account.areaId! - 1].areaName)
                    .foregroundColor(.secondary)
            }
        }
        .frame(maxWidth: .infinity)
    }
    
    var AccountPointLevel: some View{
        Section{
            //经验条
            ZStack{
                Circle()
                    .stroke(lineWidth: 20)
                    .foregroundColor(.gray.opacity(0.3))
                Circle()
                    .trim(from: 0,to: pointProgress)
                    .stroke(style: StrokeStyle(lineWidth: 20,lineCap: .round))
                    .foregroundColor(.blue)
                    .rotationEffect(.degrees(-90))
                Text("\(account.pointsLevel?.points ?? 0) / \(account.pointsLevel?.nextPoints ?? 0)")
                    .font(.body)
                    .fontWeight(.bold)
                    .foregroundColor(.black)
            }
            .frame(width: 120,height: 120)
            Text("Level.\(account.pointsLevel?.pointsLevel ?? 0) \(account.pointsLevel?.pointsLevelName ?? "")")
                .fontWeight(.bold)
        }
        .listRowSeparator(.hidden)
        .padding()
        .frame(maxWidth: .infinity)
    }
    
    var AccountSignInStatus: some View {
        Section(header: Text("Sign In This Week")) {
            HStack{
                ForEach(0..<7){ index in
                    VStack{
                        Image(systemName: signInStatus[index] ? "checkmark.circle.fill" : "circle")
                            .resizable()
                            .scaledToFit()
                            .frame(width: 30, height: 30)
                            .foregroundColor(signInStatus[index] ? .green : .gray)
                        Text(dayOfWeek(index))
                            .font(.caption)
                    }
                    .frame(maxWidth: .infinity)
                }
            }
            .padding()
            AccountSignInButton
        }
    }
    
    var AccountSignInButton: some View {
        Button {
            signInToday()
        }label: {
            Text(signInStatus[getTodayIndex()] ? "已签到" : "签到")
                .fontWeight(.bold)
                .padding()
                .foregroundColor(.white)
                .background(signInStatus[getTodayIndex()] ? Color.green : Color.blue)
                .cornerRadius(10)
        }
        .disabled(signInStatus[getTodayIndex()])
        .frame(maxWidth: .infinity, alignment: .center)
    }
    
    var AccountMenu: some View{
        Section {
            NavigationLink {} label:{
                Label("Settings",systemImage: "gearshape.fill")
            }
            NavigationLink {} label: {
                Label("Billing",systemImage: "creditcard")
            }
            NavigationLink {} label: {
                Label("Help",systemImage: "questionmark.circle")
            }
        }
        .foregroundColor(.primary)
        .listRowSeparatorTint(.blue)
        .listRowSeparator(.hidden)
    }
    
    var AccountLinks: some View{
        Section {
            if !isDeleted {
                Link(destination: URL(string: "https://apple.com")!){
                    HStack {
                        Label("Apple Home",systemImage: "apple.logo")
                        Spacer()
                        Image(systemName: "link")
                            .foregroundColor(.secondary)
                    }
                }
                .swipeActions(edge: .trailing,allowsFullSwipe: false) {
                    Button(action: { isDeleted = true }) {
                        Label("Delete",systemImage: "trash")
                    }
                    .tint(.red)
                    pinButton
                }
            }
            Link(destination: URL(string: "https://youtube.com")!){
                HStack {
                    Label("YouTube",systemImage: "tv")
                    Spacer()
                    Image(systemName: "link")
                        .foregroundColor(.secondary)
                }
            }
            .swipeActions {
                pinButton
            }
        }
        .foregroundColor(.primary)
        .listRowSeparator(.hidden)
    }
    
    var AccountCoins: some View{
        Section(header: Text("Coins")) {
            ForEach(coinModael.coins){ coin in
                HStack {
                    AsyncImage(url: URL(string: coin.logo)){ image in
                        image.resizable()
                            .aspectRatio(contentMode: .fit)
                    }placeholder: {
                        ProgressView()
                    }
                    .frame(width: 32,height: 32)
                    VStack(alignment: .leading, spacing: 4) {
                        Text(coin.coin_name)
                        Text(coin.acronym)
                            .font(.caption)
                            .foregroundColor(.secondary)
                    }
                }
            }
        }
    }
    
    var pinButton: some View{
        Button { isPinned.toggle() } label: {
            if isPinned{
                Label("Unpin",systemImage: "pin.slash")
            }else{
                Label("Pin",systemImage: "pin")
            }
        }
        .tint(isPinned ? .gray : .yellow)
    }
    
     func signInToday(){
        withAnimation {
            signInStatus[getTodayIndex()] = true
            saveSignInStatus()
        }
    }
    
    func loadSignInStatus(){
        signInStatus = weekSignInStatusString.map{$0 == "1"}
    }
    
    func saveSignInStatus(){
        weekSignInStatusString = signInStatus.map{$0 ? "1" : "0"}.joined()
    }
    
    func getTodayIndex() -> Int{
        let weekday = Calendar.current.component(.weekday, from: Date())
        return (weekday - 1) % 7
        
    }
    
    func dayOfWeek(_ index: Int) -> String {
        var result = ""
        switch index {
        case 0:
            result = "日"
        case 1:
            result = "一"
        case 2:
            result = "二"
        case 3:
            result = "三"
        case 4:
            result = "四"
        case 5:
            result = "五"
        case 6:
            result = "六"
        default:
            result = ""
        }
        return result
    }
}

struct AccountView_Previews: PreviewProvider {
    static var previews: some View {
        AccountView()
    }
}
