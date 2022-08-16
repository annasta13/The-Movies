//
//  MovieScreenContent.swift
//  iosApp
//
//  Created by Annas Surdyanto on 29/11/21.
//  Copyright © 2021 private project. All rights reserved.
//

import SwiftUI
import shared

struct MovieScreenContent: View {
    var movieList: [Movie]
    var errorMessage: String?
    var isLoading: Bool
    var listIsFull: Bool
    var onRefresh:()->Void
    
    
    var body: some View {
        if isLoading && movieList.isEmpty {LoadingScreen()}
        else if movieList.isEmpty && errorMessage != nil{
            ErrorScreen(message: errorMessage!, onRetry: onRefresh)
        }
        LazyVStack{
            ForEach(movieList, id: \.movieId){movie in
                NavigationLink(destination: MovieDetailScreen(movieId: movie.movieId)){
                    MovieItem(movie: movie)
                }.buttonStyle(PlainButtonStyle())
            }
            
            if listIsFull == false {
                HStack{
                    Spacer()
                    ProgressView()
                    Spacer()
                }.onAppear(perform: onRefresh)
            }
        }
    }
}
