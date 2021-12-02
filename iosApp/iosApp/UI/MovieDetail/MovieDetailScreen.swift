//
//  MovieDetailScreen.swift
//  iosApp
//
//  Created by Developer on 29/11/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MovieDetailScreen: View {
    var movieId: Int64
    @ObservedObject var viewModel = MovieDetailViewModel()
    
    init(movieId: Int64){
        self.movieId = movieId
        viewModel.initState(movieId: movieId)
    }
    
    var body: some View {
        let state = viewModel.state
        
        //NavigationView{
            
                if (state.isLoading){ ProgressView() }
                
                if (state.movieResponse != nil) {
                    MovieDetailScreenContent(data: state.movieResponse!, onRefresh: {viewModel.initState(movieId: movieId)})
                }
                
                if (state.error != nil){
                    ErrorScreen(message: state.error!.message!){ viewModel.initState(movieId: movieId) }
                }
            
            //.navigationBarTitleDisplayMode(.inline)
        //}
    }
}

struct MovieDetailScreen_Previews: PreviewProvider {
    static var previews: some View {
        MovieDetailScreen(movieId: 24332)
    }
}
