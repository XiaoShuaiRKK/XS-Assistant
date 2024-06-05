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
    @State var address: Address = Address(id: 0, areaName: "", areaNameChinese: "")
    var account: Account = UserManger.shared.currentAccount ?? UserManger.shared.emptyAccount
    @Environment(\.dismiss) var dismiss
    @AppStorage("isLogged") var isLogged = false
    @ObservedObject var coinModael = CoinModel()
    
    func fetchAddress() async{
        address = AddressManger.shared.getAddress(id: account.areaId!)
    }
    
    var body: some View {
        NavigationView {
            List {
                AccountInfo
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
            Text(account.firstName + account.lastName)
                .font(.title.weight(.semibold))
            HStack {
                Image(systemName: "location.circle.fill")
                    .foregroundColor(.blue)
                Text(address.areaName)
                    .foregroundColor(.secondary)
            }
        }
        .frame(maxWidth: .infinity)
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
}

struct AccountView_Previews: PreviewProvider {
    static var previews: some View {
        AccountView()
    }
}
