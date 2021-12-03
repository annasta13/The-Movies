//
//  TabBarButton.swift
//  iosApp
//
//  Created by Annas Surdyanto on 29/11/21.
//  Copyright © 2021 private project. All rights reserved.
//

import SwiftUI
import shared

struct MainScreen: View {
    @State private var selectedTab: Int = 0
    
    let tabs: [Tab] = [
        .init(title: "Popular"),
        .init(title: "Playing"),
        .init(title: "Upcoming"),
        .init(title: "Top Rated")
    ]
    
    
    init() {
        UINavigationBar.appearance().backgroundColor = UIColor(Color.black)
        UINavigationBar.appearance().titleTextAttributes = [.foregroundColor: UIColor.white]
        UINavigationBar.appearance().isTranslucent = false
    }
    
    var body: some View {
        NavigationView {
            GeometryReader { geo in
                VStack(spacing: 0) {
                    // Tabs
                    TabBarButton(tabs: tabs, geoWidth: geo.size.width, selectedTab: $selectedTab)
                    
                    // Views
                    TabView(selection: $selectedTab,
                            content: {
                        PopularMoviesScreen()
                            .tag(0)
                        NowPlayingMoviesScreen()
                            .tag(1)
                        UpcomingMoviesScreen()
                            .tag(2)
                        TopRatedMoviesScreen()
                            .tag(3)
                    })
                        .tabViewStyle(PageTabViewStyle(indexDisplayMode: .never))
                }
            }
            .navigationBarTitleDisplayMode(.inline)
            .navigationBarTitle("The Movies")
            .navigationBarItems(trailing:
                NavigationLink(destination: FavoriteScreen()){
                    Image("favorited")
                        .resizable()
                        .renderingMode(.template)
                        .frame(width: 16, height: 16)
                        .foregroundColor(.white)
                }.buttonStyle(PlainButtonStyle()))
        }.accentColor(.yellow)
    }
}

struct MainScreen_Previews: PreviewProvider {
    static var previews: some View {
        MainScreen()
    }
}
