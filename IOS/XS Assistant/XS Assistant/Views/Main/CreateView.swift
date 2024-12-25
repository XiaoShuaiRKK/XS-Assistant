//
//  CreateView.swift
//  XS Assistant
//
//  Created by Already on 2024/6/5.
//

import SwiftUI

struct CreateView: View {
    @State var title = ""
    @State var subTitle = ""
    @State var description = ""
    @State var context = ""
    @State var isSuccess = false
    //是否显示提示框
    @State var showAlert = false
    @State var showAlertType = AlertType.WARNING
    @State var showAlertMessage = ""
    @AppStorage("isLogged") var isLogged = false
    @ObservedObject var articleManger = ArticleManger.shared
    //用户返回上一级页面
    @Environment(\.dismiss) var dismiss
    
    var body: some View {
        ZStack{
            Color("Background").ignoresSafeArea()
            if isLogged{
                ScrollView {
                    titleBox
                    subTitleBox
                    descriptionBox
                    contextBox
                    createButton
                }
                .safeAreaInset(edge: .top, content: {
                    Color.clear.frame(height: 70)
                })
                .overlay {
                    NavigationBar(title: "Create", hasScrolled: .constant(true))
                }
                .background(Image("Blob 1").offset(x: 200,y: -500))
            }else{
                Text("Plase Sign Up")
                    .font(.title)
                    .bold()
            }
            if showAlert {
                VStack{
                    CustomAlertView(showAlert: $showAlert, alertType: showAlertType,message: showAlertMessage)
                        .transition(.scale)
                        .padding(.top, 20)
                    Spacer()
                }
                
            }
        }
    }
    
    var titleBox: some View{
        VStack(alignment: .leading,spacing: 16){
            Text("Title".uppercased())
                .titleStyle()
            Group{
                TextField("Title", text: $title)
                    .textContentType(.jobTitle)
                    .autocapitalization(.none)
                    .disableAutocorrection(true)
                    .padding(10)
                    .background(.primary.opacity(0.2))
                    .shadow(color: .primary.opacity(0.3), radius: 10, x: 0, y: 3)
                    .overlay(geometry)
            }
            .padding(8)
        }
        .background(.ultraThinMaterial,in: RoundedRectangle(cornerRadius: 20,style: .continuous))
        .background(
            Circle().fill(.red)
                .frame(width: 40, height: 40)
                .frame(maxWidth: .infinity,maxHeight: .infinity,alignment: .topLeading)
                .offset(y: 20)
        )
        .padding(10)
        .coordinateSpace(name: "container")
    }
    
    var subTitleBox: some View{
        VStack(alignment: .leading,spacing: 0){
            Text("SubTitle".uppercased())
                .titleStyle()
            Group{
                TextField("SubTitle", text: $subTitle)
                    .textContentType(.jobTitle)
                    .autocapitalization(.none)
                    .disableAutocorrection(true)
                    .padding(10)
                    .background(.primary.opacity(0.2))
                    .shadow(color: .primary.opacity(0.3), radius: 10, x: 0, y: 3)
                    .overlay(geometry)
            }
            .padding(8)
        }
        .background(.ultraThinMaterial,in: RoundedRectangle(cornerRadius: 20,style: .continuous))
        .background(
            Circle().fill(.blue)
                .frame(width: 40, height: 40)
                .frame(maxWidth: .infinity,maxHeight: .infinity,alignment: .topLeading)
                .offset(y: 20)
        )
        .padding(10)
        .coordinateSpace(name: "container")
    }
    
    var descriptionBox: some View{
        VStack(alignment: .leading,spacing: 0){
            Text("Description".uppercased())
                .titleStyle()
            Group{
                TextField("Description", text: $description)
                    .textContentType(.jobTitle)
                    .autocapitalization(.none)
                    .disableAutocorrection(true)
                    .padding(10)
                    .background(.primary.opacity(0.2))
                    .shadow(color: .primary.opacity(0.3), radius: 10, x: 0, y: 3)
                    .overlay(geometry)
            }
            .padding(8)
        }
        .background(.ultraThinMaterial,in: RoundedRectangle(cornerRadius: 20,style: .continuous))
        .background(
            Circle().fill(.purple)
                .frame(width: 40, height: 40)
                .frame(maxWidth: .infinity,maxHeight: .infinity,alignment: .topLeading)
                .offset(y: 20)
        )
        .padding(10)
        .coordinateSpace(name: "container")
    }
    
    var contextBox: some View{
        VStack(alignment: .leading,spacing: 0){
            Text("Context".uppercased())
                .titleStyle()
            Group{
                TextEditor(text: $context)
                    .font(.body)
                    .frame(minHeight: 100)
                    .disableAutocorrection(true)
                    .padding(10)
                    .scrollContentBackground(.hidden)
                    .background(.primary.opacity(0.2))
            }
            .padding(8)
        }
        .background(.ultraThinMaterial,in: RoundedRectangle(cornerRadius: 20,style: .continuous))
        .background(
            Circle().fill(.orange)
                .frame(width: 40, height: 40)
                .frame(maxWidth: .infinity,maxHeight: .infinity,alignment: .topLeading)
                .offset(y: 20)
        )
        .padding(10)
        .coordinateSpace(name: "container")
    }
    
    
    /**
     创建按钮
     */
    var createButton: some View{
        Button{
            Task{
                ArticleManger.shared.createArticle(title: title, subTitle: subTitle, description: description, context: context)
                showAlert = true
                title = ""
                subTitle = ""
                description = ""
                context = ""
                createArticleResult()
                createShowAlert()
            }
        }label: {
            Text("Create")
                .frame(maxWidth: .infinity)
        }
        .font(.headline)
        .blendMode(.overlay)
        .buttonStyle(.angular)
        .tint(.accentColor)
        .controlSize(.large)
        .shadow(color: Color("Shoadow").opacity(0.4), radius: 30,x: 0,y: 30)
        .padding(20)
    }
    
    var geometry: some View{
        GeometryReader{ proxy in
            Color.clear.preference(key: CirclePreferenceKey.self,value: proxy.frame(in: .named("container")).minY)
        }
    }
    
    func createShowAlert() {
        showAlert = true
        DispatchQueue.main.asyncAfter(deadline: .now() + 2){
            withAnimation {
                showAlert = false
                if showAlertType == AlertType.SUCCESS{
                    dismiss()
                }
            }
        }
    }
    
    func createArticleResult(){
        switch ArticleManger.shared.result.status{
        case "200":
            showAlertType = AlertType.SUCCESS
        case "500":
            showAlertType = AlertType.ERROR
        default:
            showAlertType = AlertType.WARNING
        }
        showAlertMessage = ArticleManger.shared.result.message
    }
}

#Preview {
    CreateView()
}
