//
//  UpcomingMoviesScreen.swift
//  iosApp
//
//  Created by Annas Surdyanto on 29/11/21.
//  Copyright © 2021 private project. All rights reserved.
//

import SwiftUI
import shared

struct UpcomingMoviesScreen: View {
    @ObservedObject private var viewModel = UpcomingViewModel()
    private let refreshName = "upcomingMovies"
    
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
        }.coordinateSpace(name: refreshName)
    }
}

struct UpcomingMoviesScreen_Previews: PreviewProvider {
    static var previews: some View {
        UpcomingMoviesScreen()
    }
}
