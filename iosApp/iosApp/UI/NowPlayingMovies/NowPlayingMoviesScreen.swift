//
//  NowPlayingMoviesScreen.swift
//  iosApp
//
//  Created by Annas Surdyanto on 29/11/21.
//  Copyright © 2021 private project. All rights reserved.
//

import SwiftUI
import shared

struct NowPlayingMoviesScreen: View {
    @ObservedObject private var viewModel = NowPlayingViewModel()
    private let refreshName = "nowPlayingMovies"
    
    var body: some View {
        let state = viewModel.state
        ScrollView(.vertical){
                RefreshView(coordinateSpaceName: refreshName){viewModel.initState()}
                if(state.error != nil){
                    ErrorScreen(message: state.error!.message!){viewModel.initState()}
                }
                if(state.movieResponse != nil){
                    MovieScreenContent(movieList: state.movieResponse!.movieDataList).padding(.top,8)
                }
            }.coordinateSpace(name: refreshName)
            .onAppear(perform: {
                UIScrollView.appearance().backgroundColor = UIColor(.white)
            })
    }
}

struct NowPlayingMoviesScreen_Previews: PreviewProvider {
    static var previews: some View {
        NowPlayingMoviesScreen()
    }
}
