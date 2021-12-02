//
//  PopularMoviesScreen.swift
//  iosApp
//
//  Created by Annas Surdyanto on 29/11/21.
//  Copyright © 2021 private project. All rights reserved.
//

import SwiftUI
import shared

struct PopularMoviesScreen: View {
    @ObservedObject private var viewModel = PopularMovieViewModel()
    private let refreshName = "popularMovies"
    
    
    var body: some View {
        let state = viewModel.state
        ScrollView(.vertical){
            RefreshView(coordinateSpaceName: refreshName){viewModel.initState()}
            if(state.error == nil && state.movieResponse == nil){
                VStack{
                    ProgressView()
                }
            }
            if(state.error != nil){
                ErrorScreen(message: state.error!.message!){viewModel.initState()}
            }
            if(state.movieResponse != nil){
                MovieScreenContent(movieList: state.movieResponse!.movieDataList).padding(.top,8)
            }
        }.coordinateSpace(name: refreshName)
    }
}

struct PopularMovies_Previews: PreviewProvider {
    static var previews: some View {
        PopularMoviesScreen()
    }
}
