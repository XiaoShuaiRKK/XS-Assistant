//
//  CardVIew.swift
//  TestApp
//
//  Created by Already on 2023/11/27.
//

import SwiftUI

struct CardView: View {
    var infomation: CardInfo
    var body: some View {
        VStack(alignment: .leading, spacing: 8.0){
            Spacer()
            Image("Logo 2")
                .resizable(resizingMode: .stretch)
                .aspectRatio(contentMode: .fit)
                .frame(width: 26.0,height: 26.0)
                .cornerRadius(10)
                .frame(width: 50,height: 50)
                .background(Color(UIColor.systemBackground).opacity(0.1),in: RoundedRectangle(cornerRadius: 16,style: .continuous))
                .strokeStyle(cornerRadius: 16.0)
            Text(infomation.title)
                .font(.largeTitle)
                .fontWeight(.bold)
                .foregroundStyle(.linearGradient(colors: [.primary,.primary.opacity(0.6)], startPoint: .topLeading, endPoint: .bottomTrailing))
            Text(infomation.description.uppercased())
                .font(.footnote)
                .fontWeight(.semibold)
                .foregroundColor(.secondary)
                .multilineTextAlignment(.leading)
                .frame(maxWidth: .infinity,alignment: .leading)
        }
        .padding(.all,20.0)
        .frame(height: 350.0)
        .background(.ultraThinMaterial)
        .mask(
            RoundedRectangle(cornerRadius: 30,style: .continuous)
        )
        .strokeStyle()
        .padding(.horizontal,20)
    }
    
}

struct CardView_Previews: PreviewProvider {
    static var previews: some View {
        CardView(infomation: courses[0])
    }
}
