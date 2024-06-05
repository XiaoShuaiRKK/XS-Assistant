//
//  ContentView.swift
//  TestApp
//
//  Created by Already on 2023/11/17.
//

import SwiftUI

struct TitleView: View{
    var body: some View{
        HStack{
                Image(systemName: "person.circle")
                        .font(.system(size: 32))
                    .foregroundColor(.blue)
        }
    }
}


struct ContentView: View{
    @AppStorage("selectedTab") var selectedTab: Tab = .HOME
    @EnvironmentObject var model: Model
    var body: some View{
        ZStack(alignment: .bottom) {
            switch selectedTab {
            case .HOME:
                HomeView()
            case .EXPLORE:
                ExploreView()
            case .CREATE:
                CreateView()
            case .LIBRARY:
                AccountView()
            }
            TabBar()
                .offset(y: model.showDetail ? 200 : 0)
            if model.showModal{
                ModalView()
                    .zIndex(1)
            }
        }
        .safeAreaInset(edge: .bottom) {
            Color.clear.frame(height: 50)
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
            .environmentObject(Model())
    }
}
