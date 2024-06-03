//
//  ListRow.swift
//  XS Assistant
//
//  Created by Already on 2024/5/29.
//

import SwiftUI

struct ListRow: View {
    var title = "iOS Development"
    var icon = "iphone"
    var body: some View {
        HStack(spacing: 16){
            Image(systemName: icon)
                .frame(width: 36,height: 36)
                .background(.ultraThinMaterial)
                .mask(Circle())
                .backgroundStyle(cornerRadius: 18)
            Text(title)
                .fontWeight(.semibold)
            Spacer()
        }
    }
}

#Preview {
    ListRow()
}
