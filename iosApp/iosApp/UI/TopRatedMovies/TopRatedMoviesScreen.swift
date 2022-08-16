//
//  TopRatedMoviesScreen.swift
//  iosApp
//
//  Created by Annas Surdyanto on 29/11/21.
//  Copyright © 2021 private project. All rights reserved.
//

import SwiftUI
import shared

struct TopRatedMoviesScreen: View {
    @ObservedObject private var viewModel = TopRatedViewModel()
    private let refreshName = "topRatedMovies"
    
    var body: some View {
        ScrollView(.vertical){
            RefreshView(coordinateSpaceName: refreshName){viewModel.initState()}
            MovieScreenContent(
                movieList: viewModel.data,
                errorMessage: viewModel.errorMessage,
                isLoading: viewModel.loadingPage,
                listIsFull: viewModel.listIsFull,
                onRefresh: {viewModel.initState()}
            )
        }
        .coordinateSpace(name: refreshName)
    }
}

struct TopRatedMovies_Previews: PreviewProvider {
    static var previews: some View {
        TopRatedMoviesScreen()
    }
}
