//
//  SecondaryButton.swift
//  iosApp
//
//  Created by Developer on 29/11/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct SecondaryButton: View {
    var text: String
    var onClick: ()->Void
    
    var body: some View {
        Button(text, action: onClick)
            .padding(12)
            .foregroundColor(.black)
            .background(Color.white)
            .overlay(
                RoundedRectangle(cornerRadius: 10)
                    .stroke(Color.gray, lineWidth: 1)
            )
    }
}

struct SecondaryButton_Previews: PreviewProvider {
    static var previews: some View {
        SecondaryButton(text: "Test", onClick: {})
    }
}
