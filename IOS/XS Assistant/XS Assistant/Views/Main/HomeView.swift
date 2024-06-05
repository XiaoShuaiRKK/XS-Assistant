//
//  HomeView.swift
//  TestApp
//
//  Created by Already on 2023/11/27.
//

import SwiftUI

struct HomeView: View {
    @State var hasScrolled = false
    @Namespace var namespace
    @State var show = false
    @State var showStatusBar = true
    @State var selectedID = ""
    @State var showCourse = false
    @State var selectedIndex = 0
    @State var selectedCourse: CardInfo = CardInfoModel().emptyCard
    @State var showCourses: [CardInfo] = []
    @EnvironmentObject var model: Model
    @ObservedObject var cardInfoModel = CardInfoModel()
    @AppStorage("isLogged") var isLogged = false
    
    var body: some View {
        ZStack {
            Color("Background").ignoresSafeArea()
            if model.showDetail{
                detail
            }
            ScrollView {
                scrollDetection
                featured
                Text("Courses".uppercased())
                    .font(.footnote.weight(.semibold))
                    .foregroundColor(.secondary)
                    .frame(maxWidth: .infinity,alignment: .leading)
                    .padding(.horizontal,20)
                LazyVGrid(columns: [GridItem(.adaptive(minimum: 300),spacing: 20)],spacing: 20) {
                    if !show{
                        cards
                    }else{
                        ForEach(cardInfoModel.cards) { course in
                            Rectangle()
                                .fill(.white)
                                .frame(height: 300)
                                .cornerRadius(30)
                                .shadow(color: Color("Shadow"), radius: 20,x: 0,y: 10)
                                .opacity(0.3)
                                .padding(.horizontal,30)
                        }
                    }
                }
                .padding(.horizontal,20)
            }
            .task { 
                if isLogged{
                    await cardInfoModel.cardsLoad()
                    showCourses = cardInfoModel.cards
                }
            }
            .refreshable {
                if isLogged{
                    await cardInfoModel.cardsLoad()
                    showCourses = cardInfoModel.cards
                }
            }
            .coordinateSpace(name: "scroll")
            .safeAreaInset(edge: .top, content: {
                Color.clear.frame(height: 70)
            })
            .overlay(
                NavigationBar(title: "Featured",hasScrolled: self.$hasScrolled)
            )
            .background(
                Image("Blob 1").offset(x: 200,y: -100)
            )
            if show{
                detail
            }
        }
        .statusBar(hidden: !showStatusBar)
        .onChange(of: showCourse){ newValue in
            withAnimation(.closeCard) {
                if newValue{
                    showStatusBar = false
                }else{
                    showStatusBar = true
                }
            }
        }
    }
    
    
    
    var scrollDetection: some View{
        GeometryReader { proxy in
            Color.clear.preference(key: ScrollPreferenceKey.self, value: proxy.frame(in: .named("scroll")).minY)
        }
        .frame(height: 0)
        .onPreferenceChange(ScrollPreferenceKey.self,perform: {
            value in
            withAnimation(.easeInOut) {
                hasScrolled = value < 0 ? true : false
            }
            
        })
    }
    
    var featured: some View{
        TabView {
            ForEach(Array(featuredCourses.enumerated()),id: \.offset) {index, item in
                GeometryReader { proxy in
                    let minX = proxy.frame(in: .global).minX
                    
                    CardView(infomation: item)
                        .frame(maxWidth: 600)
                        .frame(maxWidth: .infinity)
                        .padding(.vertical,40)
                        .rotation3DEffect(.degrees(minX / -10), axis: (x: 0, y: 1, z: 0))
                        .blur(radius: abs(minX / 50))
                        .shadow(color: Color("Shoadow").opacity(0.3), radius: 10,x: 0,y: 5)
                        .overlay(
                            Image(item.image)
                                .resizable()
                                .aspectRatio(contentMode: .fit)
                                .frame(height: 230)
                                .offset(x: 32, y: -80)
                                .offset(x: minX / 2)
                        )
                        .onTapGesture {
                            showCourse = true
                            selectedCourse = item
                        }
                }
            }
        }
        .tabViewStyle(.page(indexDisplayMode: .never))
        .frame(height: 430)
        .sheet(isPresented: $showCourse){
            CourseView(namespace: namespace,course: selectedCourse, show: $showCourse)
        }
    }
    
    
    
    var cards: some View{
        ForEach(showCourses) { course in
            CourseItem(namespace: namespace, show: $show,course: course)
                .onTapGesture {
                    withAnimation(.openCard){
                        showCourse.toggle()
                        model.showDetail.toggle()
                        showStatusBar = false
                        selectedCourse = course
                    }
            }
        }
        
    }
    
    var detail: some View{
        ForEach(showCourses) { course in
            if course.id == selectedID {
                CourseView(namespace: namespace, course: course, show: $show)
                    .zIndex(1)
                    .transition(.asymmetric(
                        insertion: .opacity.animation(.easeInOut(duration: 0.1)),
                    removal: .opacity.animation(.easeInOut(duration: 0.3).delay(0.2))))
            }
        }
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
            .environmentObject(Model())
    }
}
