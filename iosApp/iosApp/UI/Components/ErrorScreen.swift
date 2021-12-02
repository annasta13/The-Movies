//
//  ErrorScreen.swift
//  iosApp
//
//  Created by Developer on 29/11/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct ErrorScreen: View {
    var message: String
    var onRetry: ()->Void
    
    var body: some View {
        VStack{
            Text(message)
            PrimaryButton(text: "Retry", onClick: onRetry)
        }
    }
}

struct ErrorScreen_Previews: PreviewProvider {
    static var previews: some View {
        ErrorScreen(message: "Test", onRetry: {})
    }
}
