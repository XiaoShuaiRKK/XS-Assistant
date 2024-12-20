//
//  SearchView.swift
//  TestApp
//
//  Created by Already on 2023/12/1.
//

import SwiftUI

struct SearchView: View {
    @State var text = ""
    @State var showCourse = false
    @State var selectedIndex = 0
    @State var selectedCourse = courses[0]
    @Namespace var namespace
    @Environment(\.presentationMode) var presentationMode
    @ObservedObject var cardModel = CardInfoModel()
    
    var results: [CardInfo]{
        if text.isEmpty{
            return courses
        }else{
            return courses.filter{
                $0.title.contains(text)
            }
        }
    }
    
    var suggestions: [CardInfo]{
        return cardModel.getSearch(target: text)
    }
    
    var body: some View {
        NavigationView{
            VStack{
                content
                Spacer()
            }
        }
        .searchable(text: $text){
            ForEach(cardModel.searchCards){ suggestion in
                Button{
                    text = suggestion.text
                }label: {
                    Text(suggestion.text)
                }
                .searchCompletion(suggestion.text)
            }
        }
//        NavigationView {
//            ScrollView {
//                VStack {
//                    content
//                }
//                .padding(20)
//                .background(
//                    .ultraThinMaterial,in:
//                        RoundedRectangle(cornerRadius: 30,style: .continuous)
//                )
//                .strokeStyle(cornerRadius: 30)
//                .padding(20)
//                .background(
//                    Rectangle()
//                        .fill(.regularMaterial)
//                        .frame(height: 200)
//                        .frame(maxHeight: .infinity,alignment: .top)
//                        .blur(radius: 20)
//                        .offset(y: -200)
//                )
//                .background(
//                    Image("Blob 1").offset(x: -100,y: -200)
//                )
//            }
//            .searchable(text: $text,placement: .navigationBarDrawer(displayMode: .always),prompt: Text("You Search Text")) {
//                ForEach(suggestions){ suggestion in
//                    Text(suggestion.text)
//                        .searchCompletion(suggestion.text)
//                }
//            }
//            .navigationTitle("Search")
//            .navigationBarTitleDisplayMode(.inline)
//            .navigationBarItems(trailing: Button { presentationMode.wrappedValue.dismiss()
//            } label: {
//                Text("Done").bold()
//            })
//            .sheet(isPresented: $show) {
//                CourseView(namespace: namespace,course: courses[selectedIndex] ,show: $show)
//            }
//        }
    }
    
    var content: some View{
        VStack{
            ForEach(Array(results.enumerated()),id: \.offset){ index, course in
                if index != 0 { Divider() }
                Button{
                    showCourse = true
                    selectedCourse = course
                }label: {
                    ListRow(title: course.title,icon: "magnifyingglass")
                }
                .buttonStyle(.plain)
            }
            if results.isEmpty{
                Text("No results found")
            }
        }
        .padding(20)
        .background(.ultraThinMaterial)
        .backgroundStyle(cornerRadius: 30)
        .padding(20)
        .navigationTitle("Search")
        .background(
            Rectangle()
                .fill(.regularMaterial)
                .frame(height: 200)
                .frame(maxWidth: .infinity, alignment: .top)
                .offset(y: -200)
                .blur(radius: 20)
        )
        .background(
            Image("Blob 1").offset(x: -100,y: -200)
                .accessibility(hidden: true)
        )
        .sheet(isPresented: $showCourse){
            CourseView(namespace: namespace, course: selectedCourse, show: $showCourse)
        }
    }
    
//    var content: some View{
//        ForEach(Array(courses.enumerated()),id: \.offset) {index, item in
//            if item.title.contains(text) || text == "" {
//                if index != 0 {
//                    Divider()
//                }
//                Button{
//                    show = true
//                    selectedIndex = index
//                }label: {
//                    HStack(alignment: .top,spacing: 12) {
//                        Image(item.image)
//                            .resizable()
//                            .aspectRatio(contentMode: .fill)
//                            .frame(width: 44,height: 44)
//                            .background(Color("Background"))
//                            .mask(Circle())
//                        VStack (alignment: .leading, spacing: 4) {
//                            Text(item.title).bold()
//                                .foregroundColor(.primary)
//                            Text(item.description)
//                                .font(.footnote)
//                                .foregroundColor(.secondary)
//                                .frame(maxWidth: .infinity,alignment: .leading)
//                                .multilineTextAlignment(.leading)
//                        }
//                    }
//                    .padding(.vertical,4)
//                    .listRowSeparator(.hidden)
//            }
//            }
//            
//        }
//    }
}

struct SearchView_Previews: PreviewProvider {
    static var previews: some View {
        SearchView()
    }
}
