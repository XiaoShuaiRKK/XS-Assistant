//
//  ModalView.swift
//  TestApp
//
//  Created by Already on 2023/12/5.
//

import SwiftUI

struct ModalView: View {
    @EnvironmentObject var model: Model
    @State var viewState: CGSize = .zero
    @State var appear = false
    @State var appearBackground = false
    @AppStorage("isLogged") var isLogged = false
    
    var body: some View {
        ZStack {
            Color.clear.background(.regularMaterial)
                .onTapGesture {
                    dismissModal()
                }
                .ignoresSafeArea()
            GeometryReader{ proxy in
                Group{
                    switch model.selectedModal{
                    case .signUp: SignUpView()
                    case .signIn: SignInView()
                    }
                }
                .rotationEffect(.degrees(viewState.width / 40))
                .rotation3DEffect(.degrees(viewState.height / 20), axis: (x: 1, y: 0, z: 0), perspective: 1)
                .shadow(color: Color("Shoadow").opacity(0.2), radius: 30, x: 0, y: 30)
                .padding(20)
                .offset(x: viewState.width, y: viewState.height)
                .gesture(drag)
                .frame(maxHeight: .infinity, alignment: .center)
                .offset(y: appear ? 0 : proxy.size.height)
                .background(
                    Image("Blob 1").offset(x: 170, y: -60)
                        .opacity(appearBackground ? 1 : 0)
                        .offset(y: appearBackground ? -10 : 0)
                        .blur(radius: appearBackground ? 0 : 40)
                        .hueRotation(.degrees(viewState.width / 5))
                        .allowsHitTesting(false)
                        .accessibility(hidden: true)
                )
            }
            Button {
                dismissModal()
            } label: {
                Image(systemName: "xmark")
                    .font(.body.weight(.bold))
                    .foregroundColor(.primary)
                    .padding(8)
                .background(.ultraThinMaterial, in: Circle())
            }
            .frame(maxWidth: .infinity,maxHeight: .infinity,alignment: .topTrailing)
            .padding(20)
            .opacity(appear ? 1 : 0)
            .offset(y: appear ? 0 : -200)
        }
        .onAppear{
            withAnimation(.spring){
                appear = true
            }
            withAnimation(.easeOut(duration: 2)){
                appearBackground = true
            }
        }
        .onDisappear{
            withAnimation(.spring()) {
                appear = false
            }
            withAnimation(.easeOut(duration: 1)) {
                appearBackground = true
            }
        }
        .onChange(of: model.dismissModal) { _ in
            dismissModal()
        }
    }
    
    var drag: some Gesture{
        DragGesture()
            .onChanged { value in
                viewState = value.translation
            }
            .onEnded { value in
                if value.translation.height > 200{
                    dismissModal()
                }else{
                    withAnimation(.openCard){
                        viewState = .zero
                    }
                }
            }
    }
    
    func dismissModal(){
        withAnimation {
            appear = false
            appearBackground = false
            model.showModal = false
        }
    }
}

struct ModalView_Previews: PreviewProvider {
    static var previews: some View {
        ModalView()
            .environmentObject(Model())
    }
}
