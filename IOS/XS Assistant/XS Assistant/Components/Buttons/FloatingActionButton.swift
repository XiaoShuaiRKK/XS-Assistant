//
//  FloatingActionButton.swift
//  XS Assistant
//
//  Created by Xiao Shuai on 2024/12/22.
//

import SwiftUI

struct FloatingActionButton: View {
    @Binding var scrollOffset: CGFloat
    @Binding var showBar: Bool
    @Binding var showCreateView: Bool
    @State var oldScrollOffset: CGFloat = 0.0
    @State var buttonOpacity: Double = 1.0
    
    func transsparency(){
        buttonOpacity = oldScrollOffset < scrollOffset ? 1.0 : 0.4;
        oldScrollOffset = scrollOffset
    }
    
    var body: some View {
        VStack{
            Spacer()
            HStack{
                Spacer()
                Button {
                    withAnimation{
                        showCreateView.toggle()
                        showBar.toggle()
                    }
                } label: {
                    ZStack{
                        Circle()
                            .fill(Color.blue)
                            .frame(width: 60,height: 60)
                            .opacity(buttonOpacity)
                            .onChange(of: scrollOffset) {newValue in
                                transsparency()
                            }
                        Image(systemName: "plus")
                            .foregroundColor(.white)
                            .font(.title)
                    }
                }
                .padding(.trailing, 20)
                .padding(.bottom, 30)
            }
        }
    }
}

#Preview {
    @State var y: CGFloat = 0
    @State var show: Bool = false
    @State var bar: Bool = false
    FloatingActionButton(scrollOffset: $y,showBar: $bar, showCreateView: $show)
}
