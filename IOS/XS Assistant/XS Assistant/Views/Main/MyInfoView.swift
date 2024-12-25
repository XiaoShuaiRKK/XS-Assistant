//
//  MyInfoView.swift
//  XS Assistant
//
//  Created by Xiao Shuai on 2024/12/22.
//

import SwiftUI

struct MyInfoView: View {
    @State var scrollOffset: CGFloat = 0
    @Namespace var namespace
    @State var show = false
    @State var showCreateView = false
    @State var selectedID = ""
    @State var showCourse = false
    @State var showCourses: [CardInfo] = []
    @State var selectedCourse: CardInfo = CardInfoModel().emptyCard
    @EnvironmentObject var model: Model
    @ObservedObject var cardModel: CardInfoModel = CardInfoModel()
    @AppStorage("isLogged") var isLogged = false
    @Binding var showBar: Bool
    
    var body: some View {
        NavigationStack {
            ZStack{
                Color("Background").ignoresSafeArea()
                ScrollView{
                    GeometryReader { geo in
                        Color.clear
                            .onAppear{
                                scrollOffset = geo.frame(in: .global).minY
                            }
                            .onChange(of: geo.frame(in: .global).minY) { newValue in
                                scrollOffset = newValue
                            }
                    }
                    .frame(height: 0)
                    
                    myInfoSection
                    Text("My Articles".uppercased())
                        .titleStyle()
                    if isLogged {
                        myArticles
                    }else{
                        ProgressView("Loading...")
                            .progressViewStyle(CircularProgressViewStyle())
                            .frame(maxWidth: .infinity, maxHeight: .infinity)
                    }
                }
                .safeAreaInset(edge: .top, content: {
                    Color.clear.frame(height: 70)
                })
                .overlay(NavigationBar(title: "My Articles", hasScrolled: .constant(true)))
                .background(Image("Blob 1").offset(x: -100,y: -400))
                .task {
                    showBar = true
                    clearPage()
                    await loadArticles()
                }
                .refreshable {
                    clearPage()
                    await loadArticles()
                }
                FloatingActionButton(scrollOffset: $scrollOffset,showBar: $showBar,showCreateView: $showCreateView)
                    .padding(.bottom,30)
                NavigationLink(destination: CreateView(), isActive: $showCreateView, label: {
                    EmptyView()
                })
            }
        }
        
    }
    
    var myInfoSection: some View{
        VStack {
            ForEach(myInfoTopics) { topic in
                ListTypeRow(topic: topic)
            }
        }
        .padding(20)
        .background(.ultraThinMaterial,in: RoundedRectangle(cornerRadius: 30,style: .continuous))
        .strokeStyle(cornerRadius: 30)
        .padding(.horizontal,20)
    }
    
    var myArticles: some View{
        LazyVGrid(columns: [GridItem(.adaptive(minimum: 300),spacing: 20)],spacing: 20) {
            ForEach(showCourses) { course in
                CourseItem(namespace: namespace, show: $show,course: course)
                    .onTapGesture {
                        withAnimation(.openCard){
                            showCourse.toggle()
                            model.showDetail.toggle()
                            selectedCourse = course
                        }
                }
                    .onAppear{
                        if course.id == showCourses.last?.id{
                            Task{
                                await loadArticles()
                            }
                        }
                    }
            }
        }
        .sheet(isPresented: $showCourse){
            CourseView(namespace: namespace,course: selectedCourse, show: $showCourse)
        }
        .padding(.horizontal,20)
    }
    
    private func loadArticles() async {
        if isLogged {
            await cardModel.myCardsLoadByIdNumber(idNumber: UserManger.shared.currentAccount.idNumber)
            showCourses = cardModel.myCards
        }
    }
    
    private func clearPage(){
        cardModel.resetMyCardPage()
    }
}

#Preview {
    @State var bar: Bool = true
    MyInfoView(showBar: $bar)
}
