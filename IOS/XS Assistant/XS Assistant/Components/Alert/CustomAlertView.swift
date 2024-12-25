//
//  CustomAlertView.swift
//  XS Assistant
//
//  Created by Xiao Shuai on 2024/12/24.
//

import SwiftUI

enum AlertType{
    case SUCCESS
    case WARNING
    case ERROR
    case INFO
}

struct CustomAlertView: View {
    @Binding var showAlert: Bool
    @State var alertType: AlertType = .WARNING
    var message: String = "This is a custom alert message"
    var iconName: String {
        switch alertType{
        case .WARNING:
            return "exclamationmark.triangle.fill"
        case .SUCCESS:
            return "checkmark.circle.fill"
        case .ERROR:
            return "xmark.circle.fill"
        case .INFO:
            return "info.circle.fill"
        }
    }
    
    var gradientBackground: LinearGradient {
        switch alertType {
        case .SUCCESS:
            return LinearGradient(gradient: Gradient(colors: [.green, .blue]), startPoint: .topLeading, endPoint: .bottomTrailing)
        case .WARNING:
            return LinearGradient(gradient: Gradient(colors: [.yellow, .orange]), startPoint: .topLeading, endPoint: .bottomTrailing)
        case .ERROR:
            return LinearGradient(gradient: Gradient(colors: [.red, .black]), startPoint: .topLeading, endPoint: .bottomTrailing)
        case .INFO:
            return LinearGradient(gradient: Gradient(colors: [.blue, .purple]), startPoint: .topLeading, endPoint: .bottomTrailing)
        }
    }
    
    var body: some View {
        HStack(spacing: 20) {
            Image(systemName: iconName)
                .resizable()
                .frame(width: 30, height: 30)
                .foregroundColor(.yellow)
            Text(message)
                .font(.body)
                .fontWeight(.medium)
                .multilineTextAlignment(.center)
                .foregroundColor(.white)
        }
        .padding()
        .background(gradientBackground)
        .mask(
            RoundedRectangle(cornerRadius: 30,style: .continuous)
        )
        .frame(maxWidth: .infinity)
        .opacity(0.8)
        .shadow(radius: 10)
        .zIndex(1)
        .animation(.easeInOut,value: showAlert)
    }
}

#Preview {
    @State var show: Bool = true
    CustomAlertView(showAlert: $show)
}
