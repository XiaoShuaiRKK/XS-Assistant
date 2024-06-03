//
//  ListTypeRow.swift
//  TestApp
//
//  Created by Already on 2023/12/6.
//

import SwiftUI

struct ListTypeRow: View {
    var topic: Topic = topics[0]
    var body: some View {
        HStack {
            Image(systemName: topic.icon)
                .frame(width: 36,height: 36)
                .background(.ultraThinMaterial)
                .mask(Circle())
            Text(topic.title)
                .fontWeight(.semibold)
            Spacer()
        }
    }
}

struct ListTypeRow_Previews: PreviewProvider {
    static var previews: some View {
        ListTypeRow()
    }
}
