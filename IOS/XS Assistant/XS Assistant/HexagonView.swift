//
//  HexagonView.swift
//  TestApp
//
//  Created by Already on 2023/11/24.
//

import SwiftUI

struct HexagonView: View {
    var body: some View {
        Canvas { context, size in
            context.draw(Text("DesignCode"), at: CGPoint(x: 50, y: 20))
            context.fill(Path(ellipseIn: CGRect(x:0,y: 0,width: 100,height: 100)), with: .color(.pink))
        }

    }
}

struct HexagonView_Previews: PreviewProvider {
    static var previews: some View {
        HexagonView()
    }
}
