//
//  Styles.swift
//  TestApp
//
//  Created by Already on 2023/11/23.
//

import SwiftUI

//modifier style
struct StrokeModifier: ViewModifier {
    var cornerRadius: CGFloat
    @Environment(\.colorScheme) var colorScheme
    func body(content: Content) -> some View {
        content.overlay(
            RoundedRectangle(cornerRadius: cornerRadius,style: .continuous)
                .stroke(
                    .linearGradient(
                        colors: [
                            .white.opacity(colorScheme == .dark ? 0.6 : 0.3),
                            .black.opacity(colorScheme == .dark ? 0.3 : 0.1)
                        ],startPoint: .top, endPoint: .bottom
                    )
                )
                .blendMode(.overlay)
        )
    }
}

struct TitleModifier: ViewModifier{
    func body(content: Content) -> some View {
        content
            .font(.footnote.weight(.semibold))
            .foregroundColor(.secondary)
            .frame(maxWidth: .infinity,alignment: .leading)
            .padding(20)
    }
}

struct OutlineOverlay: ViewModifier {
    @Environment(\.colorScheme) var colorScheme
    var cornerRadius: CGFloat = 20
    
    func body(content: Content) -> some View {
        content.overlay(
            RoundedRectangle(cornerRadius: cornerRadius)
                .stroke(
                    .linearGradient(
                        colors: [
                            .white.opacity(colorScheme == .dark ? 0.6 : 0.3),
                            .black.opacity(colorScheme == .dark ? 0.3 : 0.1)
                        ],
                        startPoint: .top,
                        endPoint: .bottom)
                )
                .blendMode(.overlay)
        )
    }
}

struct BackgroundColor: ViewModifier{
    var opacity: Double = 0.6
    var cornerRadius: CGFloat = 20
    @Environment(\.colorScheme) var colorScheme
    func body(content: Content) -> some View {
        content
            .overlay(
                Color("Background")
                    .opacity(colorScheme == .dark ? opacity : 0)
                    .mask(RoundedRectangle(cornerRadius: cornerRadius,style: .continuous))
                    .blendMode(.overlay)
                    .allowsHitTesting(false)
            )
    }
}

struct BackgroundStyle: ViewModifier{
    var cornerRadius: CGFloat = 20
    var opacity: Double = 0.6
    @AppStorage("isLiteMode") var isLiteMode = true
    func body(content: Content) -> some View {
        content
            .backgroundColor(opacity: opacity)
            .cornerRadius(cornerRadius)
            .shadow(color: Color("Shadow").opacity(isLiteMode ? 0 : 0.3), radius: 20, x: 0, y: 10)
            .modifier(OutlineOverlay(cornerRadius: cornerRadius))
    }
}

extension View{
    func titleStyle() -> some View{
        modifier(TitleModifier())
    }
}

//is .modifer(StorkeStyle())
extension View{
    func strokeStyle(cornerRadius: CGFloat = 30.0) -> some View {
        modifier(StrokeModifier(cornerRadius: cornerRadius))
    }
}

extension View {
    func backgroundColor(opacity: Double = 0.6) -> some View {
        self.modifier(BackgroundColor(opacity: opacity))
    }
}

extension View{
    func backgroundStyle(cornerRadius: CGFloat = 20,opacity: Double = 0.6) -> some View{
        self.modifier(BackgroundStyle())
    }
}
