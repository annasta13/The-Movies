//
//  LoadingScreen.swift
//  iosApp
//
//  Created by Annas Surdyanto on 29/10/21.
//  Copyright © 2021 Vivere. All rights reserved.
//

import SwiftUI

struct LoadingScreen: View {
    var body: some View {
        
        VStack(alignment: .center){
            Spacer()
            HStack{
                Spacer()
                ProgressView("Loading")
                Spacer()
            }
            Spacer()
        }
    }
}

struct LoadingScreen_Previews: PreviewProvider {
    static var previews: some View {
        LoadingScreen()
    }
}
